from pygoogletranslation import Translator
import json
import sys
def trans_single(i,text,translator):
    lang="zh-cn"
    try:
        t = translator.translate(text, dest=lang)
        translation = t.text
    except:
        print("whyyyyyyyyyy")
        with open('D:\IdeaProjects\DataProcess\DataProcess-springboot\pythonAPI\google_trans_break.json', "a", encoding="utf-8") as md:
            json.dump({"id":i }, md, ensure_ascii=False)
            md.write("\n")
        return 'nonono'

    # print(text)
    print(translation)
    return translation

translator = Translator(retry_messgae=True)
i=0
with open(sys.argv[4]+ sys.argv[1], 'r', encoding="utf-8") as f:
    for line in f:
        i += 1
        # if i <= 239980:
        #     continue
        data = json.loads(line.strip())
        data = str(data["sen"])

        print(data)
        trans = trans_single(i,data,translator)
        if trans is not None:
            pass
        else:
            trans = 'nonono'

        if sys.argv[2] == "last":
            out_file_name =sys.argv[5]+'processed_'+sys.argv[1]
        else:out_file_name=sys.argv[4]+sys.argv[1]
        with open(out_file_name, "a", encoding="utf-8") as md:
            md.write(json.dumps({i:trans}, ensure_ascii=False) + '\n')

        # if i >= 240000:
        #     break
