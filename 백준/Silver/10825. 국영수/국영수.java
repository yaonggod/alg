import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static class Student implements Comparable<Student>{
        String name;
        int kor;
        int eng;
        int math;
        Student(String name, int kor, int eng, int math) {
            this.name = name;
            this.kor = kor;
            this.eng = eng;
            this.math = math;
        }

        @Override
        public int compareTo(Student s) {
            if (this.kor > s.kor) return -1;
            if (this.kor == s.kor && this.eng < s.eng) return -1;
            if (this.kor == s.kor && this.eng == s.eng && this.math > s.math) return -1;
            if (this.kor == s.kor && this.eng == s.eng && this.math == s.math) return this.name.compareTo(s.name);
            return 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Student> pq = new PriorityQueue<>();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int kor = Integer.parseInt(st.nextToken());
            int eng = Integer.parseInt(st.nextToken());
            int math = Integer.parseInt(st.nextToken());
            pq.offer(new Student(name, kor, eng, math));
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Student s = pq.poll();
            sb.append(s.name).append("\n");
        }
        System.out.println(sb);
    }


}