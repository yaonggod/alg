class Solution {
    public String solution(String m, String[] musicinfos) {
        int time = 0;
        String answer = "(None)";
        
        // 내가 들은거
        String listened = changePattern(m);
        
        for (String music : musicinfos) {
            String[] minfo = music.split(",");
            String[] start = minfo[0].split(":");
            String[] end = minfo[1].split(":");
            int rt = (Integer.parseInt(end[0]) - Integer.parseInt(start[0])) * 60 +
                      Integer.parseInt(end[1]) - Integer.parseInt(start[1]);
            String title = minfo[2];
            String pattern = changePattern(minfo[3]);
            
            int repeat = rt / pattern.length();
            int left = rt % pattern.length();
            StringBuilder musicSb = new StringBuilder();
            
            // repeat번 반복됨
            for (int i = 0; i < repeat; i++) {
                musicSb.append(pattern);
            }
            // 그리고 남은 패턴 쪼가리
            musicSb.append(pattern.substring(0, left));
            
            if (musicSb.toString().contains(listened) && rt > time) {
                time = rt;
                answer = title;
            }
        }
        return answer;
    }
    
    static String changePattern(String pattern) {
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while (idx < pattern.length()) {
            if (idx != pattern.length() - 1 && pattern.charAt(idx + 1) == '#') {
                // 소문자로 바꾸자
                char next = (char) ((int) pattern.charAt(idx) + 32);
                sb.append(next);
                idx += 2;
            } else {
                sb.append(pattern.charAt(idx++));
            }
        }
        return sb.toString();
    }
}