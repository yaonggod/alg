class Solution {
    public String solution(String m, String[] musicinfos) {
        int time = 0;
        String answer = "(None)";
        for (String music : musicinfos) {
            String[] minfo = music.split(",");
            String[] start = minfo[0].split(":");
            String[] end = minfo[1].split(":");
            int rt = (Integer.parseInt(end[0]) - Integer.parseInt(start[0])) * 60 +
                      Integer.parseInt(end[1]) - Integer.parseInt(start[1]);
            String title = minfo[2];
            String pattern = minfo[3];
            
            char[] musicArr = patternToArr(pattern);
            char[] newArr = new char[rt];
            char[] listenArr = patternToArr(m);
            int idx = 0;
            for (int i = 0; i < rt; i++) {
                newArr[i] = musicArr[idx++];
                if (idx == musicArr.length) idx = 0;
            }
            String realMusic = "";
            for (int i = 0; i < rt; i++) {
                realMusic += newArr[i];
            }
            String listenMusic = "";
            for (int i = 0; i < listenArr.length; i++) {
                listenMusic += listenArr[i];
            }
            if (realMusic.contains(listenMusic) && rt > time) {
                time = rt;
                answer = title;
            }
            
        }
        return answer;
    }
    
    static char[] patternToArr(String pattern) {
        int sharp = 0;
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == '#') sharp++;
        }
        int patternlen = pattern.length() - sharp;
        char[] patternArr = new char[patternlen];
        int idx = 0;
        int putWhere = 0;
        while (idx < pattern.length()) {
            char now = pattern.charAt(idx++);
            if (now == '#') {
                patternArr[putWhere - 1] = (char)((int) patternArr[putWhere - 1] + 32);
            } else {
                patternArr[putWhere++] = now;
            }
        }
        return patternArr;
    }
}