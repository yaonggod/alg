import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            String[] phone = new String[n];
            for (int i = 0; i < n; i++) {
                phone[i] = br.readLine();
            }
            // 정렬하고
            Arrays.sort(phone);
            sb.append(consistency(n, phone));
            if (tc != t - 1) {
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    static String consistency(int n, String[] phone) {
        for (int i = 0; i < n - 1; i++) {
            String front = phone[i];
            String back = phone[i + 1];
            // 앞에 전화번호가 뒤에 전화번호보다 짧은데
            // 뒤에 전화번호가 앞에 전화번호로 시작함
            if (front.length() < back.length()) {
                if (back.indexOf(front) == 0) {
                    return "NO";
                }
            }
        }
        return "YES";
    }
}