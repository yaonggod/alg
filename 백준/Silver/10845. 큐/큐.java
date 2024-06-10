import java.io.*;
import java.util.*;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList<Integer> queue = new LinkedList<>();

        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (command.equals("push")) {
                queue.offer(Integer.parseInt(st.nextToken()));
            } else if (command.equals("pop")) {
                sb.append(queue.isEmpty() ? -1 : queue.poll());
            } else if (command.equals("size")) {
                sb.append(queue.size());
            } else if (command.equals("empty")) {
                sb.append(queue.isEmpty() ? 1 : 0);
            } else if (command.equals("front")) {
                sb.append(queue.isEmpty() ? -1 : queue.getFirst());
            } else if (command.equals("back")) {
                sb.append(queue.isEmpty() ? -1 : queue.getLast());
            }
            if (!command.equals("push") && i != n - 1) sb.append("\n");
        }
        System.out.println(sb);

    }


}