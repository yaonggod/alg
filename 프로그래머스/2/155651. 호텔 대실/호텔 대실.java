import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        // 입실 빠른것부터
        PriorityQueue<Integer[]> book = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(Integer[] a, Integer[] b) {
                if (a[0] < b[0]) return -1;
                return 1;
            }
        });
        for (int i = 0; i < book_time.length; i++) {
            book.offer(new Integer[] {timeToInt(book_time[i][0]), timeToInt(book_time[i][1]) + 10});
        }
        
        int maxRoom = 0;
        
        // 방들의 퇴실 시간
        PriorityQueue<Integer> room = new PriorityQueue<>((a, b) -> a - b);
        for (int i = 0; i < book_time.length; i++) {
            Integer[] b = book.poll();
            if (room.isEmpty()) {
                room.offer(b[1]);
                maxRoom++;
            } else {
                // 퇴실이 가장 빠른 방을 꺼내서
                int canBook = room.poll();
                // 들어갈 수 있는지
                if (b[0] < canBook) {
                    // 못들어감 == 방 + 1
                    room.offer(canBook);
                    maxRoom++;
                }
                room.offer(b[1]);
            }
        }
        
        return maxRoom;
    }
    
    public static int timeToInt(String time) {
        int result = 0;
        String[] hourMin = time.split(":");
        result += Integer.parseInt(hourMin[0]) * 60;
        result += Integer.parseInt(hourMin[1]);
        return result;
    }
}