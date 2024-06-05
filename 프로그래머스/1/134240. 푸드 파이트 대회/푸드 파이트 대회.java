class Solution {
    public String solution(int[] food) {
        StringBuilder sb = new StringBuilder();
        StringBuilder reverse = new StringBuilder();
        for (int i = 0; i < food.length; i++) {
            if (food[i] > 1) {
                int count = food[i] / 2;
                for (int c = 0; c < count; c++) {
                    sb.append(i);
                    reverse.append(i);
                }
            }
        }
        sb.append(0).append(reverse.reverse());
        return sb.toString();
    }
}