##去除长句

import re
import json
import sys
file_name ='D:\IdeaProjects\DataProcess\DataProcess-springboot\\uploadFiles\\'+sys.argv[1]
out_file_name ='D:\IdeaProjects\DataProcess\DataProcess-springboot\\uploadFiles\\'+sys.argv[1]
num = int(sys.argv[3])
i = 1

with open(file_name, 'r', encoding='utf-8') as open_file:
        for line in open_file:

            flag = 0
            # 解析JSON数据
            data = json.loads(line.strip())

            # 获取字典中的键和值
            sen = ''
            for key, value in data.items():
                sen = value

                if len(sen) > num:
                    flag+=1

            if flag != 0:
                continue
            else:
                if sys.argv[2] == "last":
                    out_file_name ='D:\IdeaProjects\DataProcess\DataProcess-springboot\\uploadFiles\\processed_'+sys.argv[1]
                with open(out_file_name, 'a+', encoding='utf-8') as out_file:
                    out_file.write(json.dumps({i: sen}, ensure_ascii=False) + '\n')
                    i += 1



