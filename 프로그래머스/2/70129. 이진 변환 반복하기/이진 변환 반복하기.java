class Solution {
    public int[] solution(String s) {
        int[] answer = new int[] {0, 0};
        char[] sArr = s.toCharArray();
        int end = sArr.length;
        while (true) {
            // 변환 차수
            answer[0]++;
            // 0 제거하기
            int one = 0;
            for (int i = 0; i < end; i++) {
                if (sArr[i] == '0') {
                    answer[1]++;
                } else {
                    one++;
                }
            }
            // 길이 1이면 그만하기
            if (one == 1) break;
            // 0 제거 후 길이를 이진 변환하기
            int index = 0;
            while (one != 0) {
                sArr[index] = (char) (one % 2 + '0');
                index++;
                one /= 2;
            }
            // 다음번에는 end 미만까지 도세요
            end = index;
        }
        return answer;
    }
}