import java.io.*;
import java.util.*;

public class Main {
    // 사람 인덱스 사전
    static Map<String, Integer> people;
    // 이 인덱스의 사람에는 몇 명이 연결되어 있는지
    static int[] connected;
    // 이 인덱스의 사람의 대장은 누군지
    static int[] head;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < t; tc++) {
            int f = Integer.parseInt(br.readLine());
            people = new HashMap<>();
            connected = new int[200000];
            head = new int[200000];
            Arrays.fill(head, -1);
            for (int p = 0; p < f; p++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String f1 = st.nextToken();
                String f2 = st.nextToken();
                // 없던 사람이면 정보를 새로 넣기
                if (!people.containsKey(f1)) {
                    int newidx = people.size();
                    // 새로운 인덱스를 추가하고
                    people.put(f1, newidx);
                    // 나 자신만 연결되어 있으며
                    connected[newidx] = 1;
                    // 내가 대장이다
                    head[newidx] = newidx;
                }
                if (!people.containsKey(f2)) {
                    int newidx = people.size();
                    people.put(f2, people.size());
                    connected[newidx] = 1;
                    head[newidx] = newidx;
                }

                // 사람 인덱스
                int f1idx = people.get(f1);
                int f2idx = people.get(f2);

                int h1 = findHead(f1idx);
                int h2 = findHead(f2idx);
                if (h1 != h2) {
                    connect(h1, h2);
                }
                sb.append(connected[Math.min(h1, h2)]);
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    static int findHead(int a) {
        if (a == head[a]) return a;
        return head[a] = findHead(head[a]);
    }

    static void connect(int a, int b) {
        a = findHead(a);
        b = findHead(b);
        if (a < b) {
            head[b] = a;
            connected[a] += connected[b];
        }
        else {
            head[a] = b;
            connected[b] += connected[a];
        }
    }

}