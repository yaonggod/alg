import java.util.*;

class Solution {
    static int N;
    static int answer; 
    static String[] con;
    public int solution(int n, String[] data) {
        con = data;
        N = n;
        answer = 0;
        go(0, new String[8]);        
        
        return answer;
    }
    
    public static void go(int idx, String[] current) {
        if (idx == N) {
            int mult = 1;
            int add = 1;
            for (String s : current) {
                if (s == null) {
                    add *= mult++;
                }
            }
            answer += add;
            return;
        }
        
        
        // 지금 명령을 분석해서
        String from = con[idx].substring(0, 1);
        String to = con[idx].substring(2, 3);
        int num = Integer.parseInt(con[idx].substring(4, 5)) + 1;
        String compare = con[idx].substring(3, 4);
        int fromIdx = -1;
        int toIdx = -1;
        // from이랑 to가 이미 있나
        for (int i = 0; i < 8; i++) {
            if (current[i] != null && current[i].equals(from)) fromIdx = i;
            if (current[i] != null && current[i].equals(to)) toIdx = i;
        }
        
        if (compare.equals("=")) {
            // 둘 다 이미 있는데 조건에 일치한다
            if (fromIdx != -1 && toIdx != -1) {
                if (Math.abs(fromIdx - toIdx) == num) go(idx + 1, current);
            // 하나만 있는데
            } else if (fromIdx != -1) {
                if (fromIdx - num >= 0 && current[fromIdx - num] == null) {
                    current[fromIdx - num] = to;
                    go(idx + 1, current);
                    current[fromIdx - num] = null;
                }
                if (fromIdx + num < 8 && current[fromIdx + num] == null) {
                    current[fromIdx + num] = to;
                    go(idx + 1, current);
                    current[fromIdx + num] = null;
                }
            } else if (toIdx != -1) {
                if (toIdx - num >= 0 && current[toIdx - num] == null) {
                    current[toIdx - num] = from;
                    go(idx + 1, current);
                    current[toIdx - num] = null;
                } 
                if (toIdx + num < 8 && current[toIdx + num] == null) {
                    current[toIdx + num] = from;
                    go(idx + 1, current);
                    current[toIdx + num] = null;
                }
            } else {
                for (int i = 0; i < 8; i++) {
                    if (current[i] == null && i - num >= 0 && current[i - num] == null) {
                        current[i] = from;
                        current[i - num] = to;
                        go(idx + 1, current);
                        current[i] = null;
                        current[i - num] = null;
                    }
                    if (current[i] == null && i + num < 8 && current[i + num] == null) {
                        current[i] = from;
                        current[i + num] = to;
                        go(idx + 1, current);
                        current[i] = null;
                        current[i + num] = null;
                    }
                }
            }
        
        } else if (compare.equals("<")) {
            if (fromIdx != -1 && toIdx != -1) {
                if (Math.abs(fromIdx - toIdx) < num) go(idx + 1, current);
            } else if (fromIdx != -1) {
                for (int i = fromIdx - num + 1; i < fromIdx + num; i++) {
                    if (i >= 0 && i < 8 && i != fromIdx && current[i] == null) {
                        current[i] = to;
                        go(idx + 1, current);
                        current[i] = null;
                    }
                }
            } else if (toIdx != -1) {
                for (int i = toIdx - num + 1; i < toIdx + num; i++) {
                    if (i >= 0 && i < 8 && i != toIdx && current[i] == null) {
                        current[i] = from;
                        go(idx + 1, current);
                        current[i] = null;
                    }
                }
            } else {
                for (int i = 0; i < 8; i++) {
                    if (current[i] == null) {
                        for (int j = i - num + 1; j < i + num; j++) {
                            if (j >= 0 && j < 8 && j != i && current[j] == null) {
                                current[i] = from;
                                current[j] = to;
                                go(idx + 1, current);
                                current[i] = null;
                                current[j] = null;
                            }
                        }
                    }
                }
            }
        } else if (compare.equals(">")) {
            
            if (fromIdx != -1 && toIdx != -1) {
                if (Math.abs(fromIdx - toIdx) > num) go(idx + 1, current);
            } else if (fromIdx != -1) {
                for (int i = fromIdx - num - 1; i >= 0; i--) {
                    if (current[i] == null) {
                        current[i] = to;
                        go(idx + 1, current);
                        current[i] = null;
                    }
                }
                for (int i = fromIdx + num + 1; i < 8; i++) {
                    if (current[i] == null) {
                        current[i] = to;
                        go(idx + 1, current);
                        current[i] = null;
                    }
                }
            } else if (toIdx != -1) {
                for (int i = toIdx - num - 1; i >= 0; i--) {
                    if (current[i] == null) {
                        current[i] = from;
                        go(idx + 1, current);
                        current[i] = null;
                    }
                }
                for (int i = toIdx + num + 1; i < 8; i++) {
                    if (current[i] == null) {
                        current[i] = from;
                        go(idx + 1, current);
                        current[i] = null;
                    }
                }
            } else {
                for (int i = 0; i < 8; i++) {
                    if (current[i] == null) {
                        for (int j = i - num - 1; j >= 0; j--) {
                            if (current[j] == null) {
                                current[i] = from;
                                current[j] = to;
                                go(idx + 1, current);
                                current[i] = null;
                                current[j] = null;
                            }
                        }
                        for (int j = i + num + 1; j < 8; j++) {
                            if (current[j] == null) {
                                current[i] = from;
                                current[j] = to;
                                go(idx + 1, current);
                                current[i] = null;
                                current[j] = null;
                            }
                        }
                    }
                }
            }
        }
    }
}