import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (t-- > 0) {
            // 숫자가 몇 개 나올건지
            int m = Integer.parseInt(br.readLine());

            // 중앙값 갯수
            sb.append(m / 2 + 1).append("\n");

            // 몇 번째 수
            int idx = 1;
            // 스트링빌더에 쓰인 숫자 개수
            int count = 0;
            // 중앙값
            int mid = 0;

            // 중앙값보다 큰거
            PriorityQueue<Integer> larger = new PriorityQueue<>();
            // 중앙값보다 작은거
            PriorityQueue<Integer> smaller = new PriorityQueue<>();

            st = new StringTokenizer(br.readLine());

            while (idx <= m) {
                if (idx != 1 && idx % 10 == 1) {
                    st = new StringTokenizer(br.readLine());
                }
                int num = Integer.parseInt(st.nextToken());

                if (idx == 1) {
                    mid = num;
                    sb.append(mid).append(" ");
                    count++;
                } else {
                    // 맞는 위치에 넣기
                    if (num >= mid) {
                        larger.offer(num);
                    } else {
                        smaller.offer(-num);
                    }

                    // 홀수 번째다
                    if (idx % 2 == 1) {
                        // 두 큐를 비교해서 같으면 넘어가기
                        if (larger.size() == smaller.size()) {
                            sb.append(mid).append(" ");
                        // 큰 수가 2개 더 들어가 있음
                        } else if (larger.size() > smaller.size()) {
                            smaller.offer(-mid);
                            mid = larger.poll();
                            sb.append(mid).append(" ");
                        // 작은 수가 2개 더 들어가 있음
                        } else {
                            larger.offer(mid);
                            mid = -smaller.poll();
                            sb.append(mid).append(" ");
                        }
                        count++;
                    }
                }
                idx++;

                if (count == 10) {
                    count = 0;
                    sb.append("\n");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);



    }

}