import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        // 미리 알파벳 인덱스를 만들어놓음
        char[] alphabet = new char[52];
        for (int i = 0; i < 26; i++) {
            alphabet[i] = (char) ('A' + i);
        }
        for (int i = 0; i < 26; i++) {
            alphabet[i + 26] = (char) ('a' + i);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // i -> j로 가는 경로
        boolean[][] route = new boolean[52][52];

        // A 17 a 49 변환
        while (n-- > 0) {
            char[] proof = br.readLine().toCharArray();
            char from = proof[0];
            char to = proof[5];

            int fn = convert(from);
            int tn = convert(to);

            if (fn != tn) {
                route[fn][tn] = true;
            }
        }

        // a -> b, b -> c => a -> c
        for (int b = 0; b < 52; b++) {
            for (int a = 0; a < 52; a++) {
                for (int c = 0; c < 52; c++) {
                    if (a != b && b != c && a != c) {
                        if (route[a][b] && route[b][c]) {
                            route[a][c] = true;
                        }
                    }
                }
            }
        }

        int count = 0;
        for (int i = 0; i < 52; i++) {
            for (int j = 0; j < 52; j++) {
                count += route[i][j] ? 1 : 0;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(count).append("\n");

        for (int i = 0; i < 52; i++) {
            for (int j = 0; j < 52; j++) {
                if (route[i][j]) {
                    sb.append(alphabet[i]).append(" => ").append(alphabet[j]).append("\n");
                }
            }
        }
        System.out.println(sb);
    }

    static int convert(char c) {
        if (c - '0' >= 49) {
            return c - '0' - 23;
        } else {
            return c - '0' - 17;
        }
    }
}