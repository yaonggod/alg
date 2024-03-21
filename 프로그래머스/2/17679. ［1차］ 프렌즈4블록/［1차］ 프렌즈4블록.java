import java.util.*;

class Solution {
    static char[][] friends;
    static boolean[][] delete;
    public int solution(int m, int n, String[] board) {
        delete = new boolean[m][n];
        friends = new char[m][n];
        for (int i = 0; i < m; i++) {
            friends[i] = board[i].toCharArray();
        }
        int result = 0;
        while (true) {
            delete = new boolean[m][n]; 
            
            int r = removeCount(m, n);
            if (r != 0) {
                result += r;
                remove(m, n);
                goDown(m, n);
            } else {
                break;
            }
        }
        return result;
    }
    
    // 이번에 삭제될 대상이 몇개일까
    static int removeCount(int m, int n) {
        int count = 0;
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (same(friends[i][j], friends[i + 1][j], friends[i][j + 1], friends[i + 1][j + 1])) {
                    count += del(i, j);
                    count += del(i + 1, j);
                    count += del(i, j + 1);
                    count += del(i + 1, j + 1);
                }
            }
        }
        return count;
    }
    
    static boolean same(char a, char b, char c, char d) {
        return a == b && b == c && c == d && d == a && a != ' ';
    }
    
    // delete로 바꾸기, 기존 영역과 중복되면 0 아니면 1
    static int del(int i, int j) {
        if (!delete[i][j]) {
            delete[i][j] = true;
            return 1;
        } 
        return 0;
    }
    
    // 실제 블록을 빈 블록으로 대체하기 
    static void remove(int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (delete[i][j]) {
                    friends[i][j] = ' ';
                }
            }
        }
    } 
    
    // 블록 내리기
    static void goDown(int m, int n) {
        for (int j = 0; j < n; j++) {
            int count = 0;
            Queue<Character> queue = new LinkedList<>();
            // 공백 수 세서 블록을 위에서부터 큐에 넣고
            for (int i = 0; i < m; i++) {
                if (friends[i][j] == ' ') count++;
                else queue.offer(friends[i][j]);
            }
            // 공백 -> 블록 순으로 재배치
            for (int i = 0; i < m; i++) {
                if (count > 0) {
                    friends[i][j] = ' ';
                    count--;
                } else {
                    friends[i][j] = queue.poll();
                }
            }
        }
    }
}