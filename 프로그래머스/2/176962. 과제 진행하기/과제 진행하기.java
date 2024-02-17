import java.util.*;

class Solution {
    class Subject implements Comparable<Subject>{
        String name;
        int h;
        int m;
        int time;
        
        public Subject(String name, int h, int m, int time) {
            this.name = name;
            this.h = h;
            this.m = m;
            this.time = time;
        }
        
        @Override
        public int compareTo(Subject subject) {
            if (this.h < subject.h) {
                return -1;
            } else if (this.h > subject.h) {
                return 1;
            } else {
                if (this.m < subject.m) {
                    return -1;
                }
            }
            return 1;
        }
    }
    public String[] solution(String[][] plans) {
        PriorityQueue<Subject> todo = new PriorityQueue<>();
        for (int i = 0; i < plans.length; i++) {
            String name = plans[i][0];
            String[] start = plans[i][1].split(":");
            int h = Integer.parseInt(start[0]);
            int m = Integer.parseInt(start[1]);
            int time = Integer.parseInt(plans[i][2]);
            todo.offer(new Subject(name, h, m, time));
        }
        
        
        Stack<Subject> pause = new Stack<>();
        String[] result = new String[plans.length];
        int index = 0;
        // 지금 하고 있음
        Subject doing = todo.poll();
        int ch = doing.h;
        int cm = doing.m;
        Subject nextTodo;
        while (index < result.length) {
            // 시작 안한거 꺼내기
            if (!todo.isEmpty()) {
                nextTodo = todo.poll(); 
                
                int timeLapse = (nextTodo.h - ch) * 60 + (nextTodo.m - cm);
                // System.out.println(doing.name + " " + doing.time + " " + timeLapse + " " + nextTodo.name);
                // 시간이 흘렀는데 지금 하고 있는 거를 끝냈다
                if (timeLapse >= doing.time) {
                    result[index] = doing.name;
                    index++;
                    // 그러면 지금 당장 하지 않아도 됨
                    // 스택에 있는거부터 우선 처리하기
                    if (!pause.isEmpty()) {
                        cm += doing.time;
                        if (cm > 60) {
                            cm -= 60;
                            ch += 1;
                        }
                        doing = pause.pop();
                        todo.offer(nextTodo);
                    } else {
                        doing = nextTodo;
                        ch = doing.h;
                        cm = doing.m;
                    }
                // 못끝냈다
                } else {
                    doing.time -= timeLapse;
                    // 미뤄두기
                    pause.push(doing);
                    doing = nextTodo;
                    ch = doing.h;
                    cm = doing.m;
                }
                
            // 스택에 남아있는거 쓰기 
            } else {
                System.out.println(doing.name + " " + doing.time);
                cm += doing.time;
                if (cm > 60) {
                    cm -= 60;
                    ch += 1;
                }
                result[index] = doing.name;
                index++;
                if (!pause.isEmpty()) {
                    doing = pause.pop();
                }
            }
            
        }
        return result;
    }
}