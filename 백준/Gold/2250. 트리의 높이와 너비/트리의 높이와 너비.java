import java.io.*;
import java.util.*;

public class Main {
    // 레벨별 최소 최대 위치
    static int[][] width;

    // 번호 붙이기
    static int[] nodeNum;

    // 트리
    static int[][] tree;

    // 부모
    // 부모가 없으면(패드립 ㄴㄴ) 루트노드
    static int[] parent;

    // 노드
    static int n;

    // 루트
    static int root;

    // 노드 번호
    static int idx = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        parent = new int[n + 1];
        nodeNum = new int[n + 1];
        tree = new int[n + 1][2];

        // 최악의 경우 균형이 어그러진 이진 트리
        width = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            width[i][0] = n;
        }

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            if (left != -1) {
                tree[node][0] = left;
                parent[left] = node;
            }
            if (right != -1) {
                tree[node][1] = right;
                parent[right] = node;
            }
        }

        // 루트노드 찾기
        for (int i = 1; i <= n; i++) {
            if (parent[i] == 0) {
                root = i;
                break;
            }
        }
        
        inorder(root, 1);

        // 루트 레벨에서는 너비가 항상 1
        int maxWidth = 1;
        int maxLevel = 1;
        for (int i = 2; i <= n; i++) {
            if (width[i][0] == 0 && width[i][1] == 0) break;
            if (width[i][1] - width[i][0] + 1 > maxWidth) {
                maxWidth = width[i][1] - width[i][0] + 1;
                maxLevel = i;
            }
        }
        System.out.println(maxLevel + " " + maxWidth);
    }

    static void inorder(int p, int level) {
        // 왼 -> 중 -> 오
        int left = tree[p][0];
        int right = tree[p][1];

        if (left != 0) {
            inorder(left, level + 1);
        }

        width[level][0] = Math.min(idx, width[level][0]);
        width[level][1] = Math.max(idx, width[level][1]);
        nodeNum[p] = idx++;

        if (right != 0) {
            inorder(right, level + 1);
        }
    }

}