import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        String name;
        TreeMap<String, Node> map;

        public Node(String name, TreeMap<String, Node> map) {
            this.name = name;
            this.map = map;
        }
    }

    static TreeMap<String, Node> root;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        root = new TreeMap<>();

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            Node node;

            // 루트
            String r = st.nextToken();
            if (!root.containsKey(r)) {
                node = new Node(r, new TreeMap<>());
                root.put(r, node);
            } else {
                node = root.get(r);
            }

            k--;

            while (k > 0) {
                String next = st.nextToken();

                if (!node.map.containsKey(next)) {
                    node.map.put(next, new Node(next, new TreeMap<>()));
                }
                node = node.map.get(next);
                k--;
            }
        }

        sb = new StringBuilder();

        for (String key : root.keySet()) {
            sb.append(key).append("\n");
            printTree(root.get(key), 1);
        }

        System.out.println(sb);

    }

    static void printTree(Node node, int level) {
        for (String key : node.map.keySet()) {
            for (int l = 0; l < level; l++) {
                sb.append("--");
            }
            sb.append(key).append("\n");
            printTree(node.map.get(key), level + 1);
        }
    }
}