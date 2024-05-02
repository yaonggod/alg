import java.util.*;

class Solution {
    class Node {
        int num;
        int x;
        int y;
        
        public Node(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }
    
    static Node[][] tree;
    static ArrayList<Integer> preorder, postorder;
    public int[][] solution(int[][] nodeinfo) {
        int n = nodeinfo.length;
        
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node a, Node b) {
                if (b.y == a.y) return a.x - b.x;
                return b.y - a.y;
            }
        });
        
        for (int i = 0; i < n; i++) {
            pq.offer(new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]));
        }
        
        // i번 노드의 왼쪽, 오른쪽 노드의 정보 
        tree = new Node[n + 1][2];
        
        Node root = pq.poll();
        
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            Node parent = root;
            
            while (true) {
                // x좌표가 작으니 왼쪽으로 간다
                if (node.x < parent.x) {
                    // 근데 지금 노드의 왼쪽에 뭐가 없어
                    if (tree[parent.num][0] == null) {
                        // 그럼 넣기
                        tree[parent.num][0] = node;
                        break;
                    } else {
                        parent = tree[parent.num][0];
                    }
                } else {
                    if (tree[parent.num][1] == null) {
                        tree[parent.num][1] = node;
                        break;
                    } else {
                        parent = tree[parent.num][1];
                    }
                }
            }
        }
        preorder = new ArrayList<>();
        pre(root.num);
        postorder = new ArrayList<>();
        post(root.num);
        
        int[][] answer = new int[2][n];
        answer[0] = preorder.stream().mapToInt(Integer::intValue).toArray();
        answer[1] = postorder.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
    
    static void pre(int current) {
        // 루트 왼쪽 오른쪽
        preorder.add(current);
        if (tree[current][0] != null) {
            pre(tree[current][0].num);
        }
        if (tree[current][1] != null) {
            pre(tree[current][1].num);
        }
    }
    
    static void post(int current) {
        // 왼쪽 오른쪽 루트
        if (tree[current][0] != null) {
            post(tree[current][0].num);
        }
        if (tree[current][1] != null) {
            post(tree[current][1].num);
        }
        postorder.add(current);
    }
}