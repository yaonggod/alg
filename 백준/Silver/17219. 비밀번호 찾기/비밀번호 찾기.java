import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Map<String, String> address = new HashMap<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String add = st.nextToken();
            String pass = st.nextToken();
            address.put(add, pass);
        }

        for (int i = 0; i < m; i++) {
            String find = br.readLine();
            bw.write(address.get(find) + "\n");
        }
        bw.close();
    }
}