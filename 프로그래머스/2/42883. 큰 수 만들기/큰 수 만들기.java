class Solution {
    public String solution(String number, int k) {
        // n - k자리를 뒤에 있는 수로 만들고
        // 앞에 있는 수를 가져다가 교체 작업
        // 같거나 크면 그 자리 수를 temp에다 두고
        // temp는 다음 자리 수와 비교하는데 temp가 작아서 갈 곳이 없으면 그만하기
        int n = number.length();
        int[] answer = new int[n - k];
        for (int i = 0; i < n - k; i++) {
            answer[i] = Integer.parseInt(number.substring(k + i, k + i + 1));
        }
        for (int i = k - 1; i >= 0; i--) {
            int target = Integer.parseInt(number.substring(i, i + 1));
            int index = 0;
            int temp = target;
            while (index < n - k) {
                int compare = answer[index];
                if (temp >= compare) {
                    answer[index++] = temp;
                    temp = compare;
                } else {
                    break;
                }
            }
        }
        String str = "";
        for (int i = 0; i < n - k; i++) {
            str += answer[i];
        }
        return str;
    }
}