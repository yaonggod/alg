import java.util.*;

class Solution {
    class File implements Comparable<File>{
        String head;
        int number;
        int index;
        
        File(String head, int number, int index) {
            this.head = head;
            this.number = number;
            this.index = index;
        }
        
        @Override
        public int compareTo(File f) {
            // HEAD를 기준으로 사전 순으로 정렬
            if (this.head.compareTo(f.head) != 0) return this.head.compareTo(f.head);
            // NUMBER 숫자 순으로 정렬
            if (this.number - f.number != 0) return this.number - f.number;
            // 들어온 순서대로 정렬
            return this.index - f.index;
        }
    }
    
    public String[] solution(String[] files) {
        PriorityQueue<File> pq = new PriorityQueue<>();
        for (int i = 0; i < files.length; i++) {
            // 대소문자 무시
            char[] fileArr = files[i].toLowerCase().toCharArray();
            int index = 0;
            // 머리
            String head = "";
            while (true) {
                if (isNum(fileArr[index])) break;
                head += fileArr[index++];
            }
            // 숫자
            String number = "";
            while (index < fileArr.length) {
                if (!isNum(fileArr[index]) || number.length() == 5) break;
                number += fileArr[index++];
            }
            pq.offer(new File(head, Integer.parseInt(number), i));
        }
        
        String[] result = new String[files.length];
        int rIdx = 0;
        while (!pq.isEmpty()) {
            result[rIdx++] = files[pq.poll().index];
        }
        return result;
    } 
    
    static boolean isNum(char c) {
        // 48 - 57
        return (int) c >= 48 && (int) c <= 57;
    }
}