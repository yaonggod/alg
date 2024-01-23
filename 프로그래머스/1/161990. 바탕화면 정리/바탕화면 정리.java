class Solution {
    public int[] solution(String[] wallpaper) {
        int h = wallpaper.length;
        int w = wallpaper[0].length();
        int minh = h;
        int minw = w;
        int maxh = 0;
        int maxw = 0;
        
        for (int i = 0; i < h; i++) {
            char[] wp = wallpaper[i].toCharArray();
            for (int j = 0; j < w; j++) {
                if (wp[j] == '#') {
                    if (i < minh) minh = i;
                    if (i + 1 > maxh) maxh = i + 1;
                    if (j < minw) minw = j;
                    if (j + 1 > maxw) maxw = j + 1;
                }
            }
        }
        int[] answer = {minh, minw, maxh, maxw};
        
        return answer;
    }
}