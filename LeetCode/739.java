
/**
 * title : Daily Temperatures
 * date : 2025-08-18
 */

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        // 며칠뒤에 따뜻해지는지 answer[]
        // 73 74 75 71 69 72 76 73

        // stack에 idx 저장
        // idx 순회
        // [0] -> 73 < 74 -> answer[0] = 1, ... answer[1] = 1
        // [2] -> 75 > 71 -> [2, 3] -> 71 > 69 -> [2, 3, 4] -> 69 < 72
        // 그래서 answer[4] = 1 -> stack[2, 3], 71 < 72 -> answer[3] = 2
        // stack[2], 75 > 72 -> stack[2, 5] -> ... 채우기

        int[] answer = new int[temperatures.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(0);

        for (int idx = 1; idx < temperatures.length; idx++) {
            while (!stack.isEmpty() && temperatures[idx] > temperatures[stack.peek()]) {
                int lastIdx = stack.pop();
                answer[lastIdx] = idx - lastIdx;
            }
            stack.push(idx);
        }

        return answer;
    }
}