package com.DataProcess.springboot.controller.datadeal;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.util.*;




@RestController
@RequestMapping("/PDFtoTxt")
public class PdfToTxtCNController {
  @PostMapping("PDFtoTxtCn")
  @ResponseBody
    public ResponseEntity<Resource> pdfToTxtCn(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String  uploadDir= "D:\\IdeaProjects\\DataProcess\\DataProcess-springboot\\PDF\\orgPDF";
        String pdfFilePath = uploadDir + File.separator + fileName;
        uploadFile(file,pdfFilePath);

        // 设置百度AI的API Key和Secret Key
        String apiKey = "kC8YwzXHlUlrfNe7uW0P3H33";
        String secretKey = "Xk0gGGQ9rZmQ9BVORsNuXPZup92N44LW";

        // 本地PDF文件路径
//        pdfFilePath = "C:\\Users\\15713\\Desktop\\老师想法.pdf";
        // 存储识别结果
        List<String> results = new ArrayList<>();

        // 读取PDF文件并分页
        List<BufferedImage> pages = splitPDFToPages(pdfFilePath);


        // 初始化HttpClient
        HttpClient httpClient = HttpClients.createDefault();

        // 百度OCR接口URL
        String ocrUrl = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";

        String accessToken = getAccessToken(apiKey, secretKey);
        // 存储识别结果

//        for (page : pages)
        for (int j = 0; j < pages.size(); j++){
            try {
                BufferedImage page = pages.get(j);
                String pageData=encodeImageToBase64(page);
                // 构建HTTP POST请求
                HttpPost post = new HttpPost(ocrUrl);


                // 构建请求参数
                String requestBody = "image=" +pageData+ "&access_token=" + accessToken;

                post.setEntity(new StringEntity(requestBody));

                // 设置请求头
                post.setHeader("Content-Type", "application/x-www-form-urlencoded");

                // 发送请求
                HttpResponse response = httpClient.execute(post);

                // 解析响应
                if (response.getStatusLine().getStatusCode() == 200) {
                    HttpEntity entity = response.getEntity();
                    String responseString = EntityUtils.toString(entity);

                    // 解析JSON响应
                    JSONObject jsonResponse = new JSONObject(responseString);
//                    System.out.println(jsonResponse);


                    JSONArray wordsResult = jsonResponse.getJSONArray("words_result");
                    BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\IdeaProjects\\DataProcess\\DataProcess-springboot\\PDF\\transPDF\\"+fileName.replace("pdf","txt")));
                    // 提取识别结果
                    StringBuilder pageText = new StringBuilder();
                    for (int i = 0; i < wordsResult.length(); i++) {
                        JSONObject word = wordsResult.getJSONObject(i);
                        String text = word.getString("words");
                        pageText.append(text);
//                        pageText.append(text).append("\n");
                    }
                            writer.write(pageText.toString());
                            writer.close();
                    results.add(pageText.toString());
                } else {
                    System.err.println("HTTP请求失败：" + response.getStatusLine().getStatusCode());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

      //封装返回给后端
        // 从文件系统或其他位置获取文件
      File file1 = new File("D:\\IdeaProjects\\DataProcess\\DataProcess-springboot\\PDF\\transPDF\\"+fileName.replace("pdf","txt"));
      // 将文件内容封装为 InputStreamResource
      InputStreamResource resource = new InputStreamResource(new FileInputStream(file1));

      // 设置响应头部信息
      HttpHeaders headers = new HttpHeaders();
      headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=file.pdf");
      headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);

      // 创建 ResponseEntity 并设置响应状态码和内容
      return ResponseEntity.ok()
              .headers(headers)
              .body(resource);
  }



    public static String getAccessToken(String apiKey, String apiSecret) {

        try {
            // 构建请求URL
            String url = "https://aip.baidubce.com/oauth/2.0/token";
            Map<String, String> params = new HashMap<>();
            params.put("grant_type", "client_credentials");
            params.put("client_id", apiKey);
            params.put("client_secret", apiSecret);

            // 拼接URL
            StringBuilder urlString = new StringBuilder(url);
            urlString.append("?");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                urlString.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            urlString.deleteCharAt(urlString.length() - 1);

            // 发送HTTP请求
            URL tokenUrl = new URL(urlString.toString());
            HttpURLConnection connection = (HttpURLConnection) tokenUrl.openConnection();
            connection.setRequestMethod("GET");

            // 读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            reader.close();

            // 解析JSON响应并获取Access Token
            String response = responseBuilder.toString();
            String accessToken = response.split("\"access_token\":\"")[1].split("\",")[0];

            return accessToken;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static List<BufferedImage> splitPDFToPages(String pdfFilePath) throws IOException {
        PDDocument document = PDDocument.load(new File(pdfFilePath));
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        List<BufferedImage> images=new ArrayList<>();

        for (int pageIndex = 0; pageIndex < document.getNumberOfPages(); pageIndex++) {
            BufferedImage image = pdfRenderer.renderImageWithDPI(pageIndex, 300, ImageType.RGB);
            images.add(image);
        }

        document.close();
        return images;

    }
    public static String encodeImageToBase64(BufferedImage image) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", byteArrayOutputStream);
        byte[] imageData = byteArrayOutputStream.toByteArray();
        String data= Base64.getEncoder().encodeToString(imageData);
        return URLEncoder.encode(data, "UTF-8");
    }


    private void uploadFile(MultipartFile file,String uploadDir) throws IOException {
        // 指定文件保存路径，这里使用了绝对路径

        String fileName = file.getOriginalFilename();
        String filePath = uploadDir;

        // 创建目录（如果不存在）
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 保存文件
        file.transferTo(new File(filePath));
    }
}
