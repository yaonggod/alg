import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long answer = 0;
        Stack<Long> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            long height = Long.parseLong(br.readLine());
            while (!stack.isEmpty()) {
                if (stack.peek() > height) break;
                stack.pop();
            }
            // 나는 stack.size()만큼 나보다 큰 애들이 앞에 있다
            // 고로 나는 그들에게 관찰당한다
            answer += stack.size();
            stack.push(height);
        }

        System.out.println(answer);
    }
}