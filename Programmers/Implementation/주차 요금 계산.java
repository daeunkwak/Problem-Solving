import java.util.*;

/**
 * title : 주차 요금 계산
 * date : 2025-10-03
 */
class Solution {
    static int defaultTime;
    static int defaultFee;
    static int addTime;
    static int addFee;
    public int[] solution(int[] fees, String[] records) {

        // 입차 <-> 출차. 차량별 주차 요금 계산하기
        // 기본 180분 - 5000, +10분당 600원
        defaultTime = fees[0];
        defaultFee = fees[1];
        addTime = fees[2];
        addFee = fees[3];

        Map<Integer, List<Integer>> cars = new HashMap<>();
        List<Integer> carNums = new ArrayList<>();  // 정렬용

        // 기록
        for (int i = 0; i < records.length; i++) {
            String[] splitted = records[i].split(" ");
            int carNum = Integer.parseInt(splitted[1]);
            int converted = convertTime(splitted[0]);

            cars.computeIfAbsent(carNum, k -> new ArrayList<>()).add(converted);
            if (!carNums.contains(carNum)) carNums.add(carNum);
        }

        // 요금 계산
        Collections.sort(carNums);
        int[] calculated = new int[carNums.size()];

        for (int i = 0; i < carNums.size(); i++) {
            List<Integer> carRecord = cars.get(carNums.get(i));

            int timeSum = 0;

            while (carRecord.size() > 0) {
                int in = carRecord.get(0);
                carRecord.remove(0);

                int out = 0;
                if (carRecord.size() > 0) {
                    out = carRecord.get(0);
                    carRecord.remove(0);
                } else out = convertTime("23:59");

                timeSum += (out - in);
            }

            calculated[i] += calculateFee(timeSum);
        }

        return calculated;
    }

    private int calculateFee(int time) {
        int fee = 0;
        if (time > defaultTime) {
            fee = defaultFee + ((time - defaultTime) / addTime) * addFee;
            if ((time - defaultTime) % addTime != 0) fee += addFee;
        } else {
            fee = defaultFee;
        }
        // System.out.println("time : " + time + " fee : " + fee);
        return fee;
    }

    private static int convertTime(String timeStr) {
        // 05:34
        String[] splitted = timeStr.split(":");
        int time = Integer.parseInt(splitted[0]);
        int min = Integer.parseInt(splitted[1]);

        return (time * 60 + min);
    }
}