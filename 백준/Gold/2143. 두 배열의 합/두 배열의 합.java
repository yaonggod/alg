import java.io.*;
import java.util.*;

public class Main {
    static HashMap<Integer, Long> Amap, Bmap;
    static int[] A, B;
    static int t, n, m;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());

        n = Integer.parseInt(br.readLine());
        A = new int[n];
        Amap = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (i > 0) {
                A[i] = A[i - 1];
                A[i] += num;
            } else {
                A[i] = num;
            }
        }

        for (int i = 0; i < n; i++) {
            Amap.put(A[i], Amap.getOrDefault(A[i], (long) 0) + 1);
            for (int j = 0; j < i; j++) {
                Amap.put(A[i] - A[j], Amap.getOrDefault(A[i] - A[j], (long) 0) + 1);
            }
        }


        m = Integer.parseInt(br.readLine());
        B = new int[m];
        Bmap = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (i > 0) {
                B[i] = B[i - 1];
                B[i] += num;
            } else {
                B[i] = num;
            }
        }

        for (int i = 0; i < m; i++) {
            Bmap.put(B[i], Bmap.getOrDefault(B[i], (long) 0) + 1);
            for (int j = 0; j < i; j++) {
                Bmap.put(B[i] - B[j], Bmap.getOrDefault(B[i] - B[j], (long) 0) + 1);
            }
        }

        long count = 0;
        for (Integer key : Amap.keySet()) {
            if (Bmap.containsKey(t - key)) {
                count += Amap.get(key) * Bmap.get(t - key);
            }
        }

        System.out.println(count);
    }

}