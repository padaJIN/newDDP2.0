##删除网址

import re
import json
import sys

file_name ='D:\IdeaProjects\DataProcess\DataProcess-springboot\\uploadFiles\\'+sys.argv[1]
out_file_name ='D:\IdeaProjects\DataProcess\DataProcess-springboot\\uploadFiles\\'+sys.argv[1]

i = 1

with open(file_name,'r',encoding='utf-8') as open_file:
        for line in open_file:

            flag = 0
            # 解析JSON数据
            data = json.loads(line.strip())

            # 获取字典中的键和值
            sen = ''
            for key, value in data.items():
                sen = value


                #http .net 网址删除
                url_pattern = r'www\S+\.net'
                sen = re.sub(url_pattern, '', sen)

                # http .com 网址删除
                url_pattern = r'www\S+\.com'
                sen = re.sub(url_pattern, '', sen)

                # http .cn 网址删除
                url_pattern = r'www\S+\.cn'
                sen = re.sub(url_pattern, '', sen)

                url_pattern = r'http\S+\.com'
                sen = re.sub(url_pattern, '', sen)


            if flag != 0:
                continue

            else:
                if sys.argv[2] == "last":
                    out_file_name ='D:\IdeaProjects\DataProcess\DataProcess-springboot\\uploadFiles\\processed_'+sys.argv[1]
                with open(out_file_name,'a+',encoding='utf-8') as out_file:
                    out_file.write(json.dumps({i:sen},ensure_ascii=False)+'\n')
                    i+=1

