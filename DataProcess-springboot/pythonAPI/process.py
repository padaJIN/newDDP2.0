import re
import json
import sys

i = 1

with open('D:\IdeaProjects\\ruoyi-vue-main\\ruoyi-vue-main\\ruoyi-admin\pythonAPI\orgData_txt\\'+sys.argv[1],'r',encoding='utf-8') as open_file:
    for line in open_file:

        flag = 0
        # 解析JSON数据
        data = json.loads(line.strip())

        # 获取字典中的键和值
        sen = ''
        for key, value in data.items():
            sen = value

            #7个及以上连续的数字
            pattern = r'\d{7,}'
            matches = re.findall(pattern, value)
            if matches:
                flag=1
            #某字符重复4遍以上
            pattern = re.compile(r'(.)\1{4,}')
            matches = pattern.search(value)
            if matches:
                flag=1
            #http .net 网址删除
            url_pattern = r'http\S+\.net'
            sen = re.sub(url_pattern, '', value)
            #连续出现5个及以上英文单词，删掉
            pattern = re.compile(r'[a-zA-Z]+\b\s+[a-zA-Z]+\b\s+[a-zA-Z]+\b\s+[a-zA-Z]+\b\s+[a-zA-Z]')
            matches = pattern.search(value)
            if matches:
                flag=1

        if flag != 0:
            continue
        else:
            with open('D:\IdeaProjects\\ruoyi-vue-main\\ruoyi-vue-main\\ruoyi-admin\pythonAPI\\finishData_txt\\'+sys.argv[1],'a+',encoding='utf-8') as out_file:
                out_file.write(json.dumps({i:sen},ensure_ascii=False)+'\n')
                i+=1

