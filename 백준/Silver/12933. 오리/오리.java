import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ducks = sc.next();
        char[] darr = ducks.toCharArray();
        if (darr.length % 5 != 0) {
            System.out.println(-1);
            return;
        }
        if (darr[0] != 'q' || darr[darr.length - 1] != 'k') {
            System.out.println(-1);
            return;
        }
        int q = 0;
        int u = 0;
        int a = 0;
        int c = 0;
        int k = 0;
        int count = 0;
        for (int i = 0; i < darr.length; i++) {
            // 오리가 새로 시작하거나
            if (darr[i] == 'q') {
                q++;
                // 기존 오리를 이어받거나
                if (count > 0) {
                    count--;
                }
            // 그 뒤에 오는 문자들은 앞에 나온 문자들보다 수가 적어야함
            } else if (darr[i] == 'u' && q > u) {
                u++;
            } else if (darr[i] == 'a' && u > a) {
                a++;
            } else if (darr[i] == 'c' && a > c) {
                c++;
            // 오리 완성됨
            } else if (darr[i] == 'k' && q > 0 && u > 0 && a > 0 && c > 0) {
                q--;
                u--;
                a--;
                c--;
                count++;
            } else {
                count = -1;
                break;
            }
        }
        if (q == 0 && u == 0 && a == 0 && c == 0) {
            System.out.println(count);
        } else {
            System.out.println(-1);
        }
    }
}


