import java.io.*;
import java.util.*;

public class Main {

    static class Problem implements Comparable<Problem> {
        int num;
        int level;
        int alg;

        public Problem(int num, int level, int alg) {
            this.num = num;
            this.level = level;
            this.alg = alg;
        }

        @Override
        public int compareTo(Problem p) {
            if (this.alg == p.alg && this.level == p.level) return Integer.compare(this.num, p.num);
            if (this.alg == p.alg) return Integer.compare(this.level, p.level);
            return Integer.compare(this.alg, p.alg);
        }
    }

    static class Problem2 implements Comparable<Problem2> {
        int num;
        int level;

        public Problem2(int num, int level) {
            this.num = num;
            this.level = level;
        }

        @Override
        public int compareTo(Problem2 p) {
            if (this.level == p.level) return Integer.compare(this.num, p.num);
            return Integer.compare(this.level, p.level);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        TreeSet<Problem> ts = new TreeSet<>();
        TreeSet<Problem2> ts2 = new TreeSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            ts.add(new Problem(p, l, g));
            ts2.add(new Problem2(p, l));
            map.put(p, l);
            map2.put(p, g);
        }

        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int p, l, g, x;
            Problem result;
            Problem2 result2;
            switch (command) {
                case "recommend":
                    g = Integer.parseInt(st.nextToken());
                    x = Integer.parseInt(st.nextToken());
                    if (x == 1) {
                        result = ts.lower(new Problem(0, 0, g + 1));
                        sb.append(result.num).append("\n");
                    } else {
                        result = ts.higher(new Problem(0, 0, g));
                        sb.append(result.num).append("\n");
                    }
                    break;
                case "recommend2":
                    x = Integer.parseInt(st.nextToken());
                    if (x == 1) {
                        result2 = ts2.last();
                        sb.append(result2.num).append("\n");
                    } else {
                        result2 = ts2.first();
                        sb.append(result2.num).append("\n");
                    }
                    break;
                case "recommend3":
                    x = Integer.parseInt(st.nextToken());
                    l = Integer.parseInt(st.nextToken());
                    if (x == 1) {
                        result2 = ts2.higher(new Problem2(0, l));
                        sb.append(result2 == null ? -1 : result2.num).append("\n");
                    } else {
                        result2 = ts2.lower(new Problem2(0, l));
                        sb.append(result2 == null ? -1 : result2.num).append("\n");
                    }
                    break;
                case "add":
                    p = Integer.parseInt(st.nextToken());
                    l = Integer.parseInt(st.nextToken());
                    g = Integer.parseInt(st.nextToken());
                    ts.add(new Problem(p, l, g));
                    ts2.add(new Problem2(p, l));
                    map.put(p, l);
                    map2.put(p, g);
                    break;
                case "solved":
                    p = Integer.parseInt(st.nextToken());
                    ts2.remove(new Problem2(p, map.get(p)));
                    ts.remove(new Problem(p, map.get(p), map2.get(p)));
                    map2.remove(p);
                    map.remove(p);
                    break;
            }
        }
        System.out.println(sb);
    }

}