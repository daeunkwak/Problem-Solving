
/**
 * title : Evaluate Reverse Polish Notation
 * date : 2025-09-19
 */
class Solution {
    public int evalRPN(String[] tokens) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (String st : tokens) {
            switch (st) {
                case "+" :
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-" :
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(b - a);
                    break;
                case "*" :
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/" :
                    int c = stack.pop();
                    int d = stack.pop();
                    stack.push(d / c);
                    break;
                default :
                    stack.push(Integer.parseInt(st));
                    break;
            }
        }

        return stack.pop();
    }
}