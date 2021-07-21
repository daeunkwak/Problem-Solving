"""
author : Kwak Daeun
github : https://github.com/daeunkwak

"""
N = int(input())
word_list = []
for i in range (N) :
    word = str(input())
    if word not in word_list :
        word_list.append(word)

word_list.sort(key = lambda x : (len(x), x))

print("\n".join(word_list))