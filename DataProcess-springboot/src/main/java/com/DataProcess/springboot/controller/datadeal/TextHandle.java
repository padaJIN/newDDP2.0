package com.DataProcess.springboot.controller.datadeal;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.io.BufferedReader;
import java.io.FileReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class TextHandle {
    private static final String APP_ID = "20240126001951843"; // 替换为您的百度翻译API App ID
    private static final String SECRET_KEY = "e0vysAIcO3nhzitno6am"; // 替换为您的百度翻译API密钥

    public static void translateFile(String inputFile, String outputFile) {
        try {
            // 确定文件格式
            String extension = getFileExtension(inputFile);

            // 读取输入文件内容
            StringBuilder inputContent = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    inputContent.append(line);
                }
            }

            // 获取请求参数
            String q = inputContent.toString();
            String from = "auto"; // 源语言自动检测
            String to = "zh"; // 目标语言为中文

            // 生成随机数和签名
            Random random = new Random();
            String salt = Integer.toString(random.nextInt());
            String sign = MD5(APP_ID + q + salt + SECRET_KEY);

            // 封装请求参数
            MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
            paramMap.add("q", q);
            paramMap.add("from", from);
            paramMap.add("to", to);
            paramMap.add("appid", APP_ID);
            paramMap.add("salt", salt);
            paramMap.add("sign", sign);

            String url = "http://api.fanyi.baidu.com/api/trans/vip/translate";

            // 封装请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(paramMap, headers);

            // 发送请求，获取翻译结果
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Object> response = restTemplate.postForEntity(url, httpEntity, Object.class);

            // 将翻译结果写入输出文件
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                writer.write(response.getBody().toString());
            }

            System.out.println("Translation completed successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 计算MD5哈希值
    private static String MD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            BigInteger num = new BigInteger(1, digest);
            String hash = num.toString(16);
            while (hash.length() < 32) {
                hash = "0" + hash;
            }
            return hash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    // 获取文件扩展名
    private static String getFileExtension(String filePath) {
        int dotIndex = filePath.lastIndexOf(".");
        if (dotIndex > 0 && dotIndex < filePath.length() - 1) { // 确保"."不在文件名的开头或结尾
            return filePath.substring(dotIndex + 1).toLowerCase();
        }
        return "";
    }




    public static void filterByLength(String inputFileName, String outputFileName, int minLength, int maxLength) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                JSONObject data = new JSONObject(line.trim());
                String sentence = data.getString("0");

                int sentenceLength = sentence.length();
                if (sentenceLength >= minLength && sentenceLength <= maxLength) {
                    writer.write(new JSONObject().put("0", sentence).toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void removeURLs(String inputFilePath, String outputFilePath) {
//        String regex = "(http|https)://[\\w-]+(\\.[\\w-]+)+([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?";
          String regex = "(http|https)://[\\w-]+(\\.[\\w-]+)+([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?|(www\\.[\\w-]+(\\.[\\w-]+)+([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?)";
        Pattern pattern = Pattern.compile(regex);

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String cleanedLine = removeURLsInLine(line, pattern);
                writer.write(cleanedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String removeURLsInLine(String line, Pattern pattern) {
        Matcher matcher = pattern.matcher(line);
        return matcher.replaceAll("");
    }



    public static void removeGarbageCharacters(String inputFilePath, String outputFilePath) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFilePath), "UTF-8"));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFilePath), "UTF-8"))) {
            String line;
            int i = 1;

            while ((line = reader.readLine()) != null) {
                String sen = "";

                // 解析 JSON 数据
                try {
                    JSONObject data = new JSONObject(line.trim());
                    sen = data.getString("sen");

                    // 7个及以上连续的数字
                    Pattern pattern = Pattern.compile("\\d{7,}");
                    Matcher matcher = pattern.matcher(sen);
                    sen = matcher.replaceAll("");

                    // 某字符重复4遍以上
                    pattern = Pattern.compile("(.)\\1{4,}");
                    matcher = pattern.matcher(sen);
                    sen = matcher.replaceAll("");

                    // 连续出现5个及以上英文单词，删掉
                    pattern = Pattern.compile("[a-zA-Z]+\\b\\s+[a-zA-Z]+\\b\\s+[a-zA-Z]+\\b\\s+[a-zA-Z]+\\b\\s+[a-zA-Z]");
                    matcher = pattern.matcher(sen);
                    sen = matcher.replaceAll("");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                writer.write("{\""+ i + "\": \"" + sen + "\"}");
                writer.newLine();
                i += 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}