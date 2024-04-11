import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] strArr = br.readLine().toCharArray();
        int one = 0;
        int zero = 0;
        boolean current = strArr[0] == '0' ? false : true;
        for (int i = 1; i < strArr.length; i++) {
            // 0이 나오고 있는데 1이 나오기 시작했다
            if (!current && strArr[i] == '1') {
                zero++;
                current = true;
            } else if (current && strArr[i] == '0') {
                one++;
                current = false;
            }
        }
        if (!current) {
            zero++;
        } else {
            one++;
        }
        System.out.println(Math.min(one, zero));
    }
}