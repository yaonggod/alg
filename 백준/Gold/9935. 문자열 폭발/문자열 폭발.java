import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String exp = br.readLine();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));
            if (sb.length() >= exp.length()) {
                if (sb.substring(sb.length() - exp.length(), sb.length()).equals(exp)) {
                    sb.delete(sb.length() - exp.length(), sb.length());
                }
            }
        }
        if (sb.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(sb);
        }
    }


}