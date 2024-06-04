import java.util.*;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        Map<String, Integer> colIdx = new HashMap<>();
        colIdx.put("code", 0);
        colIdx.put("date", 1);
        colIdx.put("maximum", 2);
        colIdx.put("remain", 3);
        
        Integer[][] data2 = new Integer[data.length][4];
        for (int i = 0; i < data.length; i++) {
            data2[i][0] = data[i][0];
            data2[i][1] = data[i][1];
            data2[i][2] = data[i][2];
            data2[i][3] = data[i][3];
        }
        
        int howToSort = colIdx.get(sort_by);
        
        Arrays.sort(data2, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] a, Integer[] b) {
                return a[howToSort] - b[howToSort];
            }
        });
        
        int n = 0;
        int extract = colIdx.get(ext);
        for (int i = 0; i < data2.length; i++) {
            if (data2[i][extract] < val_ext) n++;
        }
        
        int[][] answer = new int[n][4];
        int idx = 0;
        for (int i = 0; i < data2.length; i++) {
            if (data2[i][extract] < val_ext) {
                answer[idx][0] = data2[i][0];
                answer[idx][1] = data2[i][1];
                answer[idx][2] = data2[i][2];
                answer[idx][3] = data2[i][3];
                idx++;
            }
        }
        
        return answer;
    }
}