class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        boolean[] lotto = new boolean[46];
        // 확인해봐야 할 번호 개수
        int count = 0;
        for (int l : lottos) {
            if (l != 0) {
                count++;
                lotto[l] = true;
            }
        }
        // 맞을수도 아닐수도
        int unknown = 6 - count;
        // 맞은거
        int match = 0;
        for (int i = 0; i < win_nums.length; i++) {
            if (lotto[win_nums[i]]) match++;
        }
        int[] answer = new int[2];
        // 모르는거 다 맞출 경우
        answer[0] = match + unknown < 2 ? 6 : 7 - (match + unknown);
        // 모르는거 다 틀릴 경우
        answer[1] = match < 2 ? 6 : 7 - match;
        return answer;
    }
}