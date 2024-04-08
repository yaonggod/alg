import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> card = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            int c = Integer.parseInt(br.readLine());
            card.offer(c);
        }

        if (n == 1) {
            System.out.println(0);
            return;
        }

        int answer = 0;

        for (int i = 0; i < n - 1; i++) {
            int x = card.poll();
            int y = card.poll();
            card.offer(x + y);
            answer += (x + y);
        }

        System.out.println(answer);
    }
}