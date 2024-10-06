import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int idx;
        char g;
        public Node(int idx, char g) {
            this.idx = idx;
            this.g = g;
        }
    }
    static Map<Integer, Integer> score;
    static List<Integer> root;
    static List<Integer>[] tree;
    static int dfs(int idx) {
        // 리프노드
        if (tree[idx].isEmpty()) {
            return score.get(idx);
        }

        int s = 0;
        for (int child : tree[idx]) {
            s += dfs(child);
        }
        s *= score.get(idx);
        return s;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] gwalho = br.readLine().toCharArray();

        // 트리
        tree = new ArrayList[30];
        for (int i = 0; i < 30; i++) {
            tree[i] = new ArrayList<>();
        }

        // 시작부터 글렀음
        if (gwalho[0] == ')' || gwalho[0] == ']') {
            System.out.println(0);
            return;
        }

        // 노드마다 점수
        score = new HashMap<>();
        score.put(0, gwalho[0] == '(' ? 2 : 3);

        // 루트노드
        root = new ArrayList<>();
        root.add(0);

        // 스택
        Stack<Node> stack = new Stack<>();
        stack.push(new Node(0, gwalho[0]));

        for (int i = 1; i < gwalho.length; i++) {
            if (gwalho[i] == ')') {
                if (stack.isEmpty()) {
                    System.out.println(0);
                    return;
                }
                // 닫는다
                if (stack.peek().g == '(') {
                    stack.pop();
                } else {
                    System.out.println(0);
                    return;
                }
            }

            else if (gwalho[i] == ']') {
                if (stack.isEmpty()) {
                    System.out.println(0);
                    return;
                }
                // 닫는다
                if (stack.peek().g == '[') {
                    stack.pop();
                } else {
                    System.out.println(0);
                    return;
                }
            }

            else if (gwalho[i] == '(') {
                if (!stack.isEmpty()) {
                    // 마지막으로 넣었던 여는 괄호의 인덱스가 부모
                    tree[stack.peek().idx].add(i);
                } else {
                    root.add(i);
                }
                // 점수 기록
                stack.push(new Node(i, gwalho[i]));
                score.put(i, 2);
            }

            else if (gwalho[i] == '[') {
                if (!stack.isEmpty()) {
                    // 마지막으로 넣었던 여는 괄호의 인덱스가 부모
                    tree[stack.peek().idx].add(i);
                } else {
                    root.add(i);
                }
                // 점수 기록
                stack.push(new Node(i, gwalho[i]));
                score.put(i, 3);
            }
        }

        if (!stack.isEmpty()) {
            System.out.println(0);
            return;
        }

        int ans = 0;
        for (int r : root) {
            ans += dfs(r);
        }

        System.out.println(ans);
    }
}