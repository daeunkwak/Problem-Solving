import java.util.*;

/**
 * title : 가장 많이 받은 선물
 * date : 2025-10-01
 */
class Solution {
    public int solution(String[] friends, String[] gifts) {

        // 두 사람이 주고받은 기록 있음 > 이번달까지 더 많이 준 사람이 다음 달에 선물 받음
        // 기록이 없거나 개수가 같음 > 선물지수가 더 큰 사람이 선물 받음
        // 선물 지수 : (이번달까지 자신이 친구들에게 준 선물의 수 - 받은 선물의 수)
        // 선물지수까지 같음 > 주고받지 않음
        // 가장 많은 선물을 받을 친구의 선물 수 구하기

        // gifts 준사람 > 받은사람

        // 준 사람 기준으로 저장
        Map<String, Map<String, Integer>> give = new HashMap<>();
        // 총 개수 저장 (준 횟수, 받은 횟수)
        Map<String, int[]> count = new HashMap<>();
        for (int i = 0; i < friends.length; i++) {
            give.put(friends[i], new HashMap<>());
            count.put(friends[i], new int[]{0, 0});
        }


        for (int i = 0; i < gifts.length; i++) {
            String[] names = gifts[i].split(" ");
            String giver = names[0];
            String receiver = names[1];

            // 선물 준 사람 기준으로
            give.get(giver).put(receiver, give.get(giver).getOrDefault(receiver, 0) + 1);
            count.get(giver)[0]++;
            count.get(receiver)[1]++;
        }
        for (int i = 0; i < friends.length; i ++) {
            // System.out.println(friends[i] + "은 선물을 누구에게 주었나");
            for (String name : give.get(friends[i]).keySet()) {
                // System.out.println(name + "에게 " + give.get(friends[i]).get(name));
            }
            // System.out.println(friends[i] + "준 횟수 : " + count.get(friends[i])[0] + " 받은 횟수 : " + count.get(friends[i])[1]);
        }

        int[] nextCnt = new int[friends.length];
        for (int i = 0; i < friends.length - 1; i++) {
            for (int j = i + 1; j < friends.length; j++) {
                String f1 = friends[i];
                String f2 = friends[j];
                // System.out.println("f1 : " + f1 +  " cnt : " + nextCnt[i] + " f2 : " + f2 + " cnt : " + nextCnt[j]);

                // 주고받은 이력이 있다면 더 많이 준 사람이 받음
                int f1tof2 = 0;
                int f2tof1 = 0;

                boolean isExists = false;
                if (give.get(f1).containsKey(f2)) {
                    f1tof2 = give.get(f1).get(f2);
                    isExists = true;
                }
                if (give.get(f2).containsKey(f1)) {
                    f2tof1 = give.get(f2).get(f1);
                    isExists = true;
                }

                if (f1tof2 > f2tof1) nextCnt[i]++;
                else if (f2tof1 > f1tof2) nextCnt[j]++;
                else if (isExists) {    // 2. isExists flag 활용안해서 count가 두번씩 더해짐
                    // 선물지수로 비교
                    int f1Cnt = count.get(f1)[0] - count.get(f1)[1];
                    int f2Cnt = count.get(f2)[0] - count.get(f2)[1];

                    // 1. 변수명 실수 (f1Cnt, f2Cnt를 써야하는데 f1tof2, f2tof1 그대로씀)
                    if (f1Cnt > f2Cnt) nextCnt[i]++;
                    else if (f2Cnt > f1Cnt) nextCnt[j]++;
                    // 같으면 주고받지 않음
                }

                // 주고받은 이력이 없는 경우
                if (!isExists) {
                    // 선물지수로 비교
                    int f1Cnt = count.get(f1)[0] - count.get(f1)[1];
                    int f2Cnt = count.get(f2)[0] - count.get(f2)[1];

                    if (f1Cnt > f2Cnt) nextCnt[i]++;
                    else if (f2Cnt > f1Cnt) nextCnt[j]++;

                }
            }
        }

        // 선물 받는 개수 찾기
        int maxCnt = 0;
        for (int i = 0; i < friends.length; i++) {
            maxCnt = Math.max(maxCnt, nextCnt[i]);
        }

        return maxCnt;
    }
}