import java.util.*;

class Solution {
    // 나왔던 조합
    static Set<String> combs;
    // combs에 있는 조합이 또 나오면 fixed에 넣기
    static Map<String, Integer> fixed;
    static char[] menuArr;
    static int[] courseCount;
    public String[] solution(String[] orders, int[] course) {
        combs = new HashSet<>();
        fixed = new HashMap<>();
        Map<Integer, List<String>> finalMenu = new HashMap<>();
        Map<Integer, Integer> maxCount = new HashMap<>();
        courseCount = course;
        
        // 손님별로 조합 만들어보기
        for (String o : orders) {
            makeComb(o);
        }
        
        // 길이별로 이에 해당하는 메뉴 넣는 리스트와 길이별 최대 갯수
        for (int l : course) {
            finalMenu.put(l, new ArrayList<>());
            maxCount.put(l, 0);
        }
        
        // fixed에 있는 조합을 하나씩 꺼내면서 확인해보기
        for (String f : fixed.keySet()) {
            // 메뉴 길이
            int l = f.length();
            // 메뉴 개수 
            int c = fixed.get(f);
            // 그 메뉴 길이에서 개수가 제일 많다 
            if (c > maxCount.get(l)) {
                // 리스트 갱신
                ArrayList<String> menus = new ArrayList<>();
                menus.add(f);
                finalMenu.put(l, menus);
                // 개수 갱신 
                maxCount.put(l, c);
            // 개수 똑같음
            } else if (c == maxCount.get(l)) {
                // 리스트에 추가
                finalMenu.get(l).add(f);
            }
        }
        
        // 몇 개 나왔는지
        int resultLen = 0;
        for (int l : finalMenu.keySet()) {
            resultLen += finalMenu.get(l).size();
        }
        
        String[] result = new String[resultLen];
        int index = 0;
        for (int l : finalMenu.keySet()) {
            for (String f : finalMenu.get(l)) {
                result[index++] = f;
            }
        }
        Arrays.sort(result);
        return result;
    }
    
    // 조합 만들기 - 알파벳순으로
    static void makeComb(String customer) {
        menuArr = customer.toCharArray();
        Arrays.sort(menuArr);
        for (int l : courseCount) {
            // 조합이 만들어지는 길이일 경우에만 
            if (l <= menuArr.length) {
                go("", 0, 0, l);
            }
        }
    }
    
    static void go(String made, int index, int count, int length) {
        // 완성
        if (count == length) {
            check(made);
            return;
        }
        
        // 미완성
        if (index == menuArr.length) return;
        
        // index번째 원소를 넣을지 말지 
        go(made + menuArr[index], index + 1, count + 1, length);
        go(made, index + 1, count, length);
    }
    
    static void check(String target) {
        // 이미 나왔던 조합이면
        if (combs.contains(target)) {
            // 나온 갯수 + 1
            if (fixed.containsKey(target)) {
                fixed.put(target, fixed.get(target) + 1);
            // 2번 나왔음 
            } else {
                fixed.put(target, 2);
            }
            
        // 없으면 나온 조합에 넣고
        } else {
            combs.add(target);
        }
    }
}