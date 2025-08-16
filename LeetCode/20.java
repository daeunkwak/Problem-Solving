
/**
 * title : Valid Parentheses
 * date : 2025-08-16
 */
class Solution {
    public boolean isValid(String s) {
        // [) () ]] ]) {]}
        // [], (), {} 를 담을 자료형으로 뭐가 좋을까
        // char[]에 담으면 매번 if문으로 비교해야함 -> HashMap에 저장해서 key값으로 바로 비교 가능하도록
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        // stack으로 (), (] 짝이 맞는지 확인
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);

            if (map.containsKey(cur)) {          // 여는 괄호면 push
                stack.push(cur);
            } else {                              // 닫는 괄호면 매칭 확인
                if (stack.isEmpty()) {
                    return false;
                }
                char open = stack.pop();
                if (map.get(open) != cur) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}