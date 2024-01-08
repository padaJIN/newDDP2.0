##去除短句

import re
import json

def delete_mess(file_name, out_file_name, num):

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

                if len(sen) < num:
                    flag+=1

            if flag != 0:
                continue
            else:
                with open(out_file_name, 'a+', encoding='utf-8') as out_file:
                    out_file.write(json.dumps({i: sen}, ensure_ascii=False) + '\n')
                    i += 1

file_name = './temp13.json'

out_file = './temp14.json'
num = 3

delete_mess(file_name,out_file,num)