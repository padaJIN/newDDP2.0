import requests
from bs4 import BeautifulSoup
from datetime import datetime
import json
import sys

# 接收用户输入的URL
url = sys.argv[1]

# 发送HTTP请求并获取HTML内容，指定编码为UTF-8
response = requests.get(url)
response.encoding = 'utf-8'  # 指定编码为UTF-8
html = response.text

# 使用Beautiful Soup解析HTML内容
soup = BeautifulSoup(html, 'html.parser')


text_elements = soup.find_all('p')

# 使用当前日期和时间生成文件名
current_datetime = datetime.now().strftime("%Y%m%d%H%M%S")
output_filename = f"result_{current_datetime}.json"

# 以追加模式打开文件，将每一条记录逐个写入
with open(output_filename, 'a', encoding='utf-8') as json_file:
    for i, element in enumerate(text_elements, start=1):
        record = {str(i): element.get_text().strip()}
        json.dump(record, json_file, ensure_ascii=False, indent=None, separators=(',', ':'))
        json_file.write('\n')  # 写入换行符

print(f"内容爬取完成并保存到{output_filename}文件。")
