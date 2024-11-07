import java.util.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        Map<String, List<Integer>> filter = new HashMap<>();
        
        for (int i = 0; i < info.length; i++) {
            String[] apply = info[i].split(" ");
            
            
            String[][] cases = new String[][] {{apply[0], "-"}, {apply[1], "-"}, {apply[2], "-"}, {apply[3], "-"}, };
            
            for (int a = 0; a < 2; a++) {
                for (int b = 0; b < 2; b++) {
                    for (int c = 0; c < 2; c++) {
                        for (int d = 0; d < 2; d++) {
                            StringBuilder sb = new StringBuilder();
                            
                            sb.append(cases[0][a]).append(cases[1][b]).append(cases[2][c]).append(cases[3][d]);
                            if (!filter.containsKey(sb.toString())) {
                                filter.put(sb.toString(), new ArrayList<>());
                            }
                            filter.get(sb.toString()).add(Integer.parseInt(apply[4]));
                        }
                    }
                }
            }
        }
        
        for (String f : filter.keySet()) {
            Collections.sort(filter.get(f));
        }
        
        for (int i = 0; i < query.length; i++) {
            String[] q = query[i].split(" ");
            StringBuilder sb = new StringBuilder();
            String fKey = sb.append(q[0]).append(q[2]).append(q[4]).append(q[6]).toString();
            
            if (!filter.containsKey(fKey)) {
                continue;
            }
            
            int start = 0;
            int end = filter.get(fKey).size() - 1;
            
            int pass = Integer.parseInt(q[7]);
            
            while (start <= end) {
                int mid = (start + end) / 2;
                
                if (filter.get(fKey).get(mid) < pass) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            
            answer[i] = filter.get(fKey).size() - start;
        }
        return answer;
    }
    
}