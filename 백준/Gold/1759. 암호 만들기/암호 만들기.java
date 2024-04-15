import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static String[] text;
    static boolean[] isVow;
    static boolean[] chosen;
    static int l, c;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        text = new String[c];
        isVow = new boolean[c];
        chosen = new boolean[c];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            text[i] = st.nextToken();
        }
        Arrays.sort(text);
        for (int i = 0; i < c; i++) {
            if (text[i].equals("a") || text[i].equals("e") ||text[i].equals("i") ||text[i].equals("o") ||text[i].equals("u")) {
                isVow[i] = true;
            }
        }

        makeCode(0, 0, 0, 0);
        System.out.println(sb);
    }

    static void makeCode(int index, int vCount, int cCount, int count) {
        if (count == l && vCount >= 1 && cCount >= 2) {
            for (int i = 0; i < c; i++) {
                if (chosen[i]) {
                    sb.append(text[i]);
                }
            }
            sb.append("\n");
            return;
        }

        if (index == c) return;

        // index번째 기호를 넣을까 말까
        if (isVow[index]) {
            chosen[index] = true;
            makeCode(index + 1, vCount + 1, cCount, count + 1);
            chosen[index] = false;
        } else {
            chosen[index] = true;
            makeCode(index + 1, vCount, cCount + 1, count + 1);
            chosen[index] = false;
        }
        makeCode(index + 1, vCount, cCount, count);
    }

}