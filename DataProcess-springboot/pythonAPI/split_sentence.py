##分句


import glob
import os
import re
import json
import sys

#file_name 要处理的文件的路径  eg: './file1.txt'
#out_file_name 要保存到的文件路径  eg:  './combin_chinese.json'

def split_sen(file_name,out_file_name):
    count = 0
    i = 0
    with open(file_name,'r',encoding='utf-8') as ff:
        text = ff.read()

    sentences = re.split('(?<=。)', text)

    # 去除空白句子
    sentences = [sentence.strip() for sentence in sentences if sentence.strip()]
    if sys.argv[2] == "last":
        out_file_name ='D:\IdeaProjects\DataProcess\DataProcess-springboot\\uploadFiles\\processed_'+sys.argv[1]
    with open(out_file_name,'a+',encoding='utf-8') as md:
        for sen in sentences:
            # print(sen)
            md.write(json.dumps({i:sen}, ensure_ascii=False) + '\n')
            i+=1

    count +=1


file_name ='D:\IdeaProjects\DataProcess\DataProcess-springboot\\uploadFiles\\'+sys.argv[1]

out_file_name ='D:\IdeaProjects\DataProcess\DataProcess-springboot\\uploadFiles\\'+sys.argv[1]

split_sen(file_name,out_file_name)
