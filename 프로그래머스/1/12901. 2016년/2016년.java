class Solution {
    public String solution(int a, int b) {
        String[] week = {"THU", "FRI", "SAT", "SUN", "MON", "TUE", "WED"};
        int[] mon = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30};
        // 1 금 2 토 3 일 4 월 5 화 6 수 0 목
        int day = 0;
        day += b;
        for (int i = 0; i < a; i++) {
            day += mon[i];
        }
        return week[day % 7];
    }
}