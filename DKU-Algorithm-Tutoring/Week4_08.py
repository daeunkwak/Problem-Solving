"""
author : Kwak Daeun
github : https://github.com/daeunkwak

title : 무지의 먹방 라이브
평소 식욕이 왕성한 무지는 자신의 재능을 뽐내고 싶어졌고 고민 끝에 카카오ㅓ TV 라이브 방송을 하기로 마음 먹었습니다.
그냥 먹방을 하면 다른 방송과 차별성이 없기 때문에 무지는 다음과 같이 독특한 방식을 생각해냈습니다.
회전판에 먹어야 할 N개의 음식이 있습니다. 각 음식에는 1부터 N까지 번호가 붙어 있으며, 각 음식을 섭취하는데 일정 시간이 소요됩니다.
무지는 다음과 같은 방법으로 음식을 섭취합니다.
    1. 무지는 1번 음식부터 먹기 시작하며, 회전판은 번호가 증가하는 순서대로 음식을 무지 앞으로 가져다 놓습니다.
    2. 마지막 번호의 음식을 섭취한 후에는 회전판에 의해 다시 1번 음식이 무지 앞으로 옵니다.
    3. 무지는 음식 하나를 1초 동안 섭취한 후 남은 음식은 그대로 두고, 다음 음식을 섭취합니다.
    다음 음식이란, 아직 남은 음식 중 다음으로 섭취해야 할 가장 가까운 번호의 음식을 말합니다.
    4. 회전판이 다음 음식을 무지 앞으로 가져오는 데 걸리는 시간은 없다고 가정합니다.
무지가 먹방을 시작한 지 K초 후에 네트워크 장애로 인해 방송이 잠시 중단 되었습니다. 무지는 네트워크 정상화 후 다시 방송을 이어갈 때,
몇 번 음식부터 섭취해야 하는지를 알고자 합니다. 각 음식을 모두 먹는데 필요한 시간이 담겨 있는 배열 food_times, 네트워크 장애가 발생한 시간
K초가 매개변수로 주어질 때 몇 번 음식부터 다시 섭취하면 되는지 return 하도록 solution 함수를 완성하세요.
description : 그리디 알고리즘
"""

import heapq


def solution(food_times, k):
    if sum(food_times) <= k:
        return -1

    q = []
    # food_times원소들과 음식번호(N)을 묶어서 heap에 push -> index번호 안헷갈리게
    for i in range(len(food_times)):
        heapq.heappush(q, (food_times[i], i + 1))
    print(q)

    eat, previous = 0, 0
    length = len(food_times)

    while (True):
        # food_times에 남아있는 음식들 중 가장 적게걸리는 음식 기준으로 한바퀴씩 돌림
        if eat + ((q[0][0] - previous) * length) > k:
            # previous를 빼는 이유는 가장 적게걸리는 음식을 먹는 동안,
            # 남은 다른 음식들도 1초씩 먹었기 때문
            break

        # 가장 적게 걸리는 음식을 먹는동안, 다른 음식들은 안없어짐
        now = heapq.heappop(q)[0]
        eat += (now - previous) * length
        length -= 1
        previous = now
        print("eat = ", eat)
        print("length = ", length)
        print("q = ", q)

    print(q)
    # heap은 push한 순서대로 저장되지 않기 때문에 N순서대로 정렬해준다
    result = sorted(q, key=lambda x: x[1])
    print(result)

    # k - eat번 먹을 동안, 다먹는 음색은 없으므로 나머지만 리턴하면 된다
    return result[(k - eat) % length][1]


food_times = [3, 1, 2]
k = 5

print(solution(food_times, k))