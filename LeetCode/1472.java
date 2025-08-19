
/**
 * title : Design Browser History
 * date : 2025-08-18
 */

class BrowserHistory {
    // hompage -> visit another url -> get back / forword history (steps)
    private ArrayDeque<String> back;
    private ArrayDeque<String> forward;
    private String currentPage;

    public BrowserHistory(String homepage) {
        // initialize
        back = new ArrayDeque<>();
        forward = new ArrayDeque<>();
        this.currentPage = homepage;
    }

    public void visit(String url) {
        // virit url -> clear forward history
        back.push(currentPage);
        currentPage = url;

        forward.clear();
    }

    public String back(int steps) {
        // steps back
        for (int i = 0; i < steps; i++) {
            if (!back.isEmpty()) {
                forward.push(currentPage);
                currentPage = back.pop();
            }
        }

        return currentPage;
    }

    public String forward(int steps) {
        // steps forward
        for (int i = 0; i < steps; i++) {
            if (!forward.isEmpty()) {
                back.push(currentPage);
                currentPage = forward.pop();
            }
        }

        return currentPage;
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */