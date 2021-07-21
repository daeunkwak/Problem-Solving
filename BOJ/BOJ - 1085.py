"""
author : Kwak Daeun
github : https://github.com/daeunkwak

"""

x, y, w, h = map(int, input().split())
dis_list = []
dis_list.append(w - x)
dis_list.append(x)
dis_list.append(h - y)
dis_list.append(y)
print(min(dis_list))
