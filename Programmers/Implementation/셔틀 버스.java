/**
 * title : 프로그래머스 - 셔틀 버스
 * date : 2025-07-11
 */

import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";

        // 시각 정수로 바꿔서 정렬
        int[] table = new int[timetable.length];
        for (int i = 0; i < timetable.length; i++) {
            table[i] = convertTime(timetable[i]);
        }
        Arrays.sort(table);

        // n회 반복 > t씩 더하면서 크루 채우기 (m이하)
        int idx = 0;
        int curBus = 540;
        int[] busVolume = new int[n];
        int lastCrew = 0;

        for (int i = 0; i < n; i++) {
            // *** 셔틀 시간 갱신 코드 틀림
            // curBus += t * i;
            curBus = 540 + t * i;
            int volume = 0;
            while (volume < m && idx < table.length && table[idx] <= curBus) {
                lastCrew = table[idx];
                volume++;
                idx++;
            }
            busVolume[i] = volume;
        }

        // *** 마지막 버스의 빈자리가 있으면 타고, 없으면 마지막 크루보다 일찍가야함
        int ans = 0;
        if (busVolume[n - 1] < m) {
            ans = 540 + t * (n - 1);
        } else {
            ans = lastCrew - 1;
        }

        return revertTime(ans);
    }

    private int convertTime(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    private String revertTime(int time) {
        String timeStr = "";
        int hour = time / 60;
        if (hour < 10) {
            timeStr = "0" + Integer.toString(hour);
        } else {
            timeStr = Integer.toString(hour);
        }

        int min = time % 60;
        if (min < 10) {
            timeStr += ":0" + Integer.toString(min);
        } else {
            timeStr += ":" + Integer.toString(min);
        }

        return timeStr;
    }
}