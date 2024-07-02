import java.io.*;
import java.util.*;

public class Main {

    static class Problem implements Comparable<Problem> {
        int num;
        int level;

        public Problem(int num, int level) {
            this.num = num;
            this.level = level;
        }

        @Override
        public int compareTo(Problem p) {
            if (this.level == p.level) return Integer.compare(this.num, p.num);
            return Integer.compare(this.level, p.level);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        TreeSet<Problem> ts = new TreeSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            ts.add(new Problem(p, l));
            map.put(p, l);
        }

        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch (command) {
                case "recommend":
                    int x = Integer.parseInt(st.nextToken());
                    if (x == 1) {
                        sb.append(ts.last().num).append("\n");
                    } else {
                        sb.append(ts.first().num).append("\n");
                    }
                    break;
                case "add":
                    int p = Integer.parseInt(st.nextToken());
                    int l = Integer.parseInt(st.nextToken());
                    ts.add(new Problem(p, l));
                    map.put(p, l);
                    break;
                case "solved":
                    p = Integer.parseInt(st.nextToken());
                    ts.remove(new Problem(p, map.get(p)));
                    map.remove(p);
                    break;
            }
        }
        System.out.println(sb);
    }

}