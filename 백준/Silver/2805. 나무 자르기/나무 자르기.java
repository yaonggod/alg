import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] tree = new int[n];
        int maxtree = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
            maxtree = Math.max(tree[i], maxtree);
        }

        int start = 0;
        int end = maxtree;

        while (start <= end) {
            int mid = (start + end) / 2;
            long take = 0;
            for (int i = 0; i < n; i++) {
                if (tree[i] > mid) {
                    take += tree[i] - mid;
                }
            }
            if (take >= m) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(end);
    }


}