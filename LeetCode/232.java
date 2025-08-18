
/**
 * title : Implement Queue using Stacks
 * date : 2025-08-18
 */
class MyQueue {
    private ArrayDeque<Integer> input;
    private ArrayDeque<Integer> output;

    // stack 2개로 queue 구현하기
    public MyQueue() {
        input = new ArrayDeque<>();
        output = new ArrayDeque<>();
    }

    public void push(int x) {
        input.push(x);
    }

    public int pop() {
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
            return output.pop();
        } else {
            return output.pop();
        }
    }

    public int peek() {
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
            return output.peek();
        } else {
            return output.peek();
        }
    }

    public boolean empty() {
        return (input.isEmpty() && output.isEmpty());
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */