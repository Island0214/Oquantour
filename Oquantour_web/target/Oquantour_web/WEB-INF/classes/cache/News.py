import tushare as ts
import sys

df = ts.get_latest_news(top=80,show_content=True)
path = sys.argv[1]
df.to_csv(path,encoding="utf8")