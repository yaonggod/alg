import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        int[] day = new int[speeds.length];
        for (int i = 0; i < day.length; i++) {
            // 남은 작업
            int leftover = 100 - progresses[i];
            // 남은날
            if (leftover % speeds[i] == 0) {
                day[i] = leftover / speeds[i];
            } else {
                day[i] = leftover / speeds[i] + 1;
            }
        }
        
        int d = day[0];
        int count = 1;
        for (int i = 1; i < day.length; i++) {
            // 이전것들 다 올리고 다음 day로 바꾸기
            if (day[i] > d) {
                answer.add(count);
                count = 1;
                d = day[i];
            } else {
                count++;
            }
        }
        answer.add(count);
        return answer.stream().mapToInt(i -> i).toArray();
    }
}