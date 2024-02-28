import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        int n = numbers.length;
        String[] nString = new String[n];
        for (int i = 0; i < n; i++) {
            nString[i] = Integer.toString(numbers[i]);
        }
        Arrays.sort(nString, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                int test1 = Integer.parseInt(a + b);
                int test2 = Integer.parseInt(b + a);
                return test1 - test2;
            }
        });
        
        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            sb.append(nString[i]);
        }
        String answer = sb.toString();
        int start = answer.length();
        for (int i = 0; i < answer.length(); i++) {
            if (!answer.substring(i, i + 1).equals("0")) {
                start = i;
                break;
            }
        }
        if (start == answer.length()) return "0";
        return answer.substring(start, answer.length());
    }
}