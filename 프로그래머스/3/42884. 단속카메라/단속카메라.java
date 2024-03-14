import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        // 루트 맨 뒤에다가 카메라 설치해서 최대한 많이 커버하게
        int last = routes[0][1];
        int camera = 1;
        for (int i = 1; i < routes.length; i++) {
            // 안겹친다
            if (routes[i][0] > last) {
                last = routes[i][1];
                camera++;
            }
        }
        return camera;
    }
}