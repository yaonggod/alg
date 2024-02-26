import java.util.*;

class Solution {
    static int[] f;
    public int[] solution(int[] fees, String[] records) {
        f = fees;
        
        // 차 시작 시간
        Map<Integer, Integer> car = new HashMap<>();
        // 차 누적 시간 
        Map<Integer, Integer> timeTotal = new HashMap<>();
        // 차 번호 작은 순으로 나오게
        PriorityQueue<Integer[]> pq = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] a, Integer[] b) {
                return a[0] - b[0];
            }
        });
        
        for (int i = 0; i < records.length; i++) {
            String[] r = records[i].split(" ");
            // 시
            int time = Integer.parseInt(r[0].split(":")[0]) * 60;
            // 분
            time += Integer.parseInt(r[0].split(":")[1]);
            // 차번호
            int carNum = Integer.parseInt(r[1]);
            // IN
            if (!car.containsKey(carNum)) {
                car.put(carNum, time);
            // OUT
            } else {
                int start = car.get(carNum);
                // 누적 내역에 있다면 누적하고 아니면 새로 넣고 
                if (timeTotal.containsKey(carNum)) {
                    timeTotal.put(carNum, timeTotal.get(carNum) + time - start);
                } else {
                    timeTotal.put(carNum, time - start);
                }
                // 기존 IN은 지움
                car.remove(carNum);
            }
        }
        
        // 못 나간 차들이 있다
        for (int c : car.keySet()) {
            // 23시 59분 출차
            int end = 23 * 60 + 59;
            int start = car.get(c);
            if (timeTotal.containsKey(c)) {
                timeTotal.put(c, timeTotal.get(c) + end - start);
            } else {
                timeTotal.put(c, end - start);
            }
        }
        // 요금계산
        for (int c : timeTotal.keySet()) {
            pq.offer(new Integer[] {c, calcFee(timeTotal.get(c))});
        }
        
        int[] answer = new int[pq.size()];
        int index = 0;
        while (!pq.isEmpty()) {
            Integer[] pqcar = pq.poll();
            answer[index] = pqcar[1];
            index++;
        }
        
        return answer;
    }
    
    static int calcFee(int time) {
        int fee = 0;
        // 기본시간 내
        if (time <= f[0]) {
            fee = f[1];
        } else {
            fee = f[1];
            time -= f[0];
            if (time % f[2] == 0) {
                fee += (time / f[2]) * f[3];
            } else {
                fee += (time / f[2] + 1) * f[3];
            }
        }
        return fee;
    }
}