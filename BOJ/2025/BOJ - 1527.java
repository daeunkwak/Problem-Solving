package just.p_0814;

import java.util.*;

/**
 * title : 금민수의 개수
 * date : 2025-08-14
 */
public class boj1527 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int depthA = Integer.toString(A).length();
        int depthB = Integer.toString(B).length();

        // 자릿수만큼 금민수를 만들고 -> 정렬해서 이분탐색
        List<Long> nums = new ArrayList<>();
        for (int i = depthA; i <= depthB; i++) {
            dfs(i, 0, 0, nums);
        }

        Collections.sort(nums);

        // ****** 입력값 범위만 보고 무지성 이분탐색을 해버림..
        int[] searchA = binarySearch(A, nums);
        int[] searchB = binarySearch(B, nums);

        int cnt = 0;
        if (searchB[1] == 1) {
            cnt = searchB[0] - searchA[0] + 1;
        } else {
            cnt = searchB[0] - searchA[0];
        }
        System.out.println(cnt);
    }

    private static int[] binarySearch(int target, List<Long> nums) {
        int start = 0;
        int end = nums.size() - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums.get(mid) < target) {
                start = mid + 1;
            } else if (nums.get(mid) > target) {
                end = mid - 1;
            } else {
                return new int[]{mid, 1};
            }
        }
        return new int[]{start, 0};
    }

    private static void dfs(int targetDepth, int curDepth, long curNum, List<Long> nums) {
        if (curDepth == targetDepth) {
            // 이분탐색 안하고 그냥 여기서 cnt해도됨
            nums.add(curNum);
            return;
        }

        long add4 = curNum * 10 + 4;
        long add7 = curNum * 10 + 7;
        dfs(targetDepth, curDepth + 1, add4, nums);
        dfs(targetDepth, curDepth + 1, add7, nums);
    }
}
