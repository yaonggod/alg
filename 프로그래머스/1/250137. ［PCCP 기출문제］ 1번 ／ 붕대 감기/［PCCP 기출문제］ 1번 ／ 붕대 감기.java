import java.util.*;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        // 마지막 시간
        int time = attacks[attacks.length - 1][0];
        // 연속
        int conn = 0;
        // 현재 시간
        int curr = 0;
        // 현재 체력
        int hp = health;
        // 몬스터 어택
        int index = 0;
        
        while (curr <= time) {
            // 공격당함
            if (attacks[index][0] == curr) {
                // 연속 취소
                conn = 0;
                // 체력깎임
                hp -= attacks[index][1];
                // 다음공격
                index += 1;
            // 공격 안당함
            } else {
                // 일반 회복
                hp = Math.min(health, hp + bandage[1]);
                // 연속
                conn++;
                // 연속 성공 추가 회복
                if (conn == bandage[0]) {
                    hp = Math.min(health, hp + bandage[2]);
                    conn = 0;
                }
            }
            if (hp <= 0) {
                hp = -1;
                break;
            }
            // 다음 시간
            curr++;
        }
        return hp;
    }
}