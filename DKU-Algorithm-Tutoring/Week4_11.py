"""
author : Kwak Daeun
github : https://github.com/daeunkwak

title : 문자열 압축
데이터 처리 전문가가 되고 싶은 '어피치'는 문자열을 압축하는 방법에 대해 공부를 하고 있습니다. 최근에 대량의 데이터 처리를 위한 간단한 비손실 압축 방법에 대해
공부를 하고 있는데, 문자열에서 같은 값이 연속해서 나타나는 것을 그 문자의 개수와 반복되는 값으로 표현하여 더 짧은 문자열로 줄여서 표현하는 알고리즘을 공부하고 있습니다.
간단한 예로 'aabbaccc'의 경우 '2a2ba3c' (문자가 반복되지 않아 한 번만 나타난 경우 1은 생략함)와 같이 표현할 수 있는데, 이러한 방식은 반복되는 문자가 적은
경우 압축률이 낮다는 단점이 있습니다. 예를들면 'abcabcdede'와 같은 문자열은 전혀 압축되지 않습니다. 어피치는 이러한 단점을 해결하기 위해 문자열을 1개 이상의
단위로 잘라서 압축하여 더 짧은 문자열로 표현할 수 있는지 방법을 찾아보려고 합니다. 예를 들어, 'ababcdcdababcdcd'의 경우 문자를 1개 단위로 전혀 압축되지
않지만, 2개 단위로 잘라서 압축한다면 '2ab2cd2ab2cd'로 표현할 수 있습니다. 다른 방법으로 8개 단위로 잘라서 압축한다면 '2ababcdcd'로 표현할 수 있으며,
이때가 가장 짧게 압축하여 표현할 수 있는 방법입니다. 다른 예로, 'abcabcdede'와 같은 경우, 문자를 2개 단위로 잘라서 압축하면 'abcabc2de'가 되지만,
3개 단위로 자른다면 '2abcdede'가 되어 3개 단위가 가장 짧은 압축 방법이 됩니다. 이때 3개 단위로 자르고 마지막에 남는 문자열은 그대로 붙여주면 됩니다.
압축할 문자열 s가 매개변수로 주어질 대, 위에 설명한 방법으로 1개 이상 단위로 문자열을 잘라 압축하여 표현한 문자열 중 가장 짧은 것의 길이를 return 하도록
solution 함수를 완성해주세요.
description : 구현
"""

def solution(s):
    length = []
    result = ""

    if len(s) == 1:
        return 1
    for cut in range(1, len(s) // 2 + 1):  # 2개이상의 단위로 압축하므로
        count = 1
        tempStr = s[:cut]
        # cut의 길이를 1씩 늘려가면서 슬라이싱
        print(tempStr)

        for i in range (cut, len(s), cut) :
        # for문에서 step을 cut으로 주기
            if s[i : i+cut] == tempStr :
            # 그 다음 뭉텅이도 같다면
                count += 1
            else:
                if count == 1:
                    count = ""
                result += str(count) + tempStr
                # abcabcabc -> 3abc로 바꾸는 과정
                tempStr = s[i : i+cut]
                # 압축하는 단위의 수는 계속 같으므로 다음 tempStr 저장
                print(tempStr)
                count = 1
                # count 초기화
        if count == 1 :
            count = ""
        result += str(count) + tempStr
        print("result = ", result)
        length.append(len(result))
        print("length = ", length)
        result = ""
    return min(length)

s = "abcabcdede"
print(solution(s))