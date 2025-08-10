import java.util.*;
기
/**
 * title : n + 1 카드게임
 * date : 2025-03-07
 */

class Solution {
    Set<Integer> original, additional;
    public int solution(int coin, int[] cards) {
        int answer = 0;
        int len = cards.length;

        // cads의 원소는 중복되지 않으므로 HashSet() 활용 ㅠㅠ
        original = new HashSet();
        additional = new HashSet();

        int idx = len / 3;
        for (int i = 0 ; i < idx; ++i) {
            original.add(cards[i]);
        }

        int target = len + 1;
        while (true) {
            answer++;
            if(idx >= len){
                break;
            }

            additional.add(cards[idx]);
            additional.add(cards[idx+1]);
            idx += 2;

            // 해결 플래그 두기 낫밷
            boolean flag = false;

            // Step1. 최초 카드에서 해결할 수 있는지 확인.
            // 변수를 i랑 target - i로 놓는것이 포인트
            for (int i : original) {
                if(original.contains(target - i)){
                    original.remove(i);
                    original.remove(target - i);
                    flag = true;
                    break;
                }
            }

            // Step2. 최초 카드에서 해결이 안되었다면
            if (!flag) {
                //  추가 카드 1장을 이용해서 해결 할 수 있는지 확인
                if (coin > 0) {
                    for (int i : original) {
                        // target - i 카드가 추가 2장에 있는지 확인!!
                        if(additional.contains(target - i)){
                            original.remove(i);
                            additional.remove(target - i);
                            --coin;
                            flag = true;
                            break;
                        }
                    }
                }
            }

            // Step3. 그래도 해결이 안되었다면, 추가 카드들끼리 되는지 확인
            if (!flag) {
                if(coin > 1){
                    for(int i : additional){
                        if(additional.contains(target - i)){
                            additional.remove(i);
                            additional.remove(target - i);
                            coin -= 2;
                            flag = true;
                            break;
                        }
                    }
                }
            }

            if (!flag)
                break;
        }
        return answer;
    }
}