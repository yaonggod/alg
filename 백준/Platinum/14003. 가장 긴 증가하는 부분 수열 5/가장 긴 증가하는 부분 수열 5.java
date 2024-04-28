import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static List<Integer> dp;
    static int[] maxLen;
    static int[] A;
    // 현재 배열의 최대 길이
    static int length;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        dp = new ArrayList<>();
        A = new int[n];
        maxLen = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        length = 1;
        dp.add(A[0]);
        maxLen[0] = 1;

        for (int i = 1; i < n; i++) {
            if (dp.get(dp.size() - 1) < A[i]) {
                dp.add(A[i]);
                maxLen[i] = dp.size();
            } else if (dp.get(0) >= A[i]) {
                dp.set(0, A[i]);
                maxLen[i] = 1;
            } else {
                int left = 0;
                int right = dp.size() - 1;
                while (left <= right) {
                    int mid = (left + right) / 2;
                    if (dp.get(mid) == A[i]) {
                        right = mid;
                        break;
                    }

                    if (dp.get(mid + 1) == A[i]) {
                        right = mid + 1;
                        break;
                    }

                    if (dp.get(mid) < A[i] && dp.get(mid + 1) > A[i]) {
                        right = mid + 1;
                        break;
                    }

                    if (dp.get(mid) > A[i]) {
                        right = mid - 1;
                    }

                    if (dp.get(mid + 1) < A[i]) {
                        left = mid + 1;
                    }
                }
                dp.set(right, A[i]);
                maxLen[i] = right + 1;
            }
        }
        int len = dp.size();
        StringBuilder sb = new StringBuilder();
        sb.append(len).append("\n");
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            if (maxLen[i] == len) {
                stack.push(A[i]);
                len--;
            }

        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb);
    }


}