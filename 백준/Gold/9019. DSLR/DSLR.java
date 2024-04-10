import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int target;
    static boolean[] visited;

    static class Turn {
        String now;
        String command;

        Turn(String now, String command) {
            this.now = now;
            this.command = command;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String A = st.nextToken();
            String B = st.nextToken();
            visited = new boolean[10000];
            visited[Integer.parseInt(A)] = true;
            target = Integer.parseInt(B);

            String result = "";

            Queue<Turn> queue = new LinkedList<>();
            queue.offer(new Turn(A, ""));
            while (!queue.isEmpty()) {
                Turn turn = queue.poll();

                int now = Integer.parseInt(turn.now);
                String command = turn.command;

                int toD = now * 2 > 9999 ? now * 2 % 10000 : now * 2;
                if (toD == target) {
                    result = command + "D";
                    break;
                }
                if (!visited[toD]) {
                    visited[toD] = true;
                    queue.offer(new Turn(intToSt(toD), command + "D"));
                }

                int toS = now - 1 == -1 ? 9999 : now - 1;
                if (toS == target) {
                    result = command + "S";
                    break;
                }
                if (!visited[toS]) {
                    visited[toS] = true;
                    queue.offer(new Turn(intToSt(toS), command + "S"));
                }

                int toL = now % 1000 * 10 + now / 1000;
                if (toL == target) {
                    result = command + "L";
                    break;
                }
                if (!visited[toL]) {
                    visited[toL] = true;
                    queue.offer(new Turn(intToSt(toL), command + "L"));
                }

                int toR = now / 10 + now % 10 * 1000;
                if (toR == target) {
                    result = command + "R";
                    break;
                }
                if (!visited[toR]) {
                    visited[toR] = true;
                    queue.offer(new Turn(intToSt(toR), command + "R"));
                }
            }
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }
    static String intToSt(int num) {
        String result = "";
        result += num / 1000;
        num = num % 1000;
        result += num / 100;
        num = num % 100;
        result += num / 10;
        num = num % 10;
        result += num;
        return result;
    }
}