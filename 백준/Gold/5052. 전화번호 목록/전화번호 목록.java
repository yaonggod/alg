import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int num;
        // true면 전화번호 완성됨
        boolean isEnd;
        Node[] next;

        public Node(int num, boolean isEnd, Node[] next) {
            this.num = num;
            this.isEnd = isEnd;
            this.next = next;
        }
    }

    static Node[] root;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.length() - o2.length();
                }
            });

            while (n-- > 0) {
                pq.offer(br.readLine());
            }

            root = new Node[10];

            boolean result = true;
            while (!pq.isEmpty()) {
                String phone = pq.poll();
                if (isPre(phone)) result = false;
                if (!result) break;
            }

            sb.append(result ? "YES" : "NO").append("\n");
        }
        System.out.println(sb);
    }

    static boolean isPre(String phone) {
        // 루트
        int num = Integer.parseInt(phone.substring(0, 1));
        Node node;
        if (root[num] != null) {
            // 한자리만 눌렀더니 끝남
            if (root[num].isEnd) {
                return true;
            }
        } else {
            // 한자리면 여기서 끝내
            root[num] = new Node(num, phone.length() == 1, new Node[10]);
        }
        node = root[num];
        for (int i = 1; i < phone.length(); i++) {
            num = Integer.parseInt(phone.substring(i, i + 1));
            // 번호 없다
            if (node.next[num] == null) {
                node.next[num] = new Node(num, phone.length() == i + 1, new Node[10]);
            } else {
                if (node.next[num].isEnd) return true;
            }
            node = node.next[num];
        }
        return false;
    }
}