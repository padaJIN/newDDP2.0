import re
import json
import sys

data_process=[]
data=[]
with open('D:\IdeaProjects\\ruoyi-vue-main\\ruoyi-vue-main\\ruoyi-admin\pythonAPI\orgData_txt\\'+sys.argv[1],'r',encoding='utf-8') as open_file:
    for line in open_file:
        data.append(json.loads(line.strip()))
open_file.close()
for data in data:

    flag = 0

    sen = ''
    for key, value in data.items():
        sen = value
        if len(sen)<30:
            flag=1

        #7个及以上连续的数字
        pattern = r'脚注'
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
        data_process.append(sen)

with open('D:\IdeaProjects\\ruoyi-vue-main\\ruoyi-vue-main\\ruoyi-admin\pythonAPI\\finishData_txt\\'+sys.argv[1], 'w', encoding='utf-8') as out_file:
    for i,sen in enumerate(data_process):
        out_file.write(json.dumps({i: sen}, ensure_ascii=False) + '\n')
out_file.close()

