import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 학생 번호로 loop을 만들어야댐
		// loop을 만들고 꼬랑지는 선택받지 못했으니까 팀번호 -1
		int t = sc.nextInt();
		for (int tc = 0; tc < t; tc++) {
			int n = sc.nextInt();
			int[] choice = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				choice[i] = sc.nextInt();
			}
			int[] team = new int[n + 1];
			int teamno = 1;
			for (int i = 1; i <= n; i++) {
				Stack<Integer> stack = new Stack<>();
				// 팀이 없으면
				if (team[i] == 0) {
					int s = i;
					team[s] = teamno;
					stack.add(s);
					while (true) {
						s = choice[s];
						// 다음 친구가 선택받지 못함
						if (team[s] == 0) {
							stack.add(s);
							team[s] = teamno;
						// 이미 우리팀임 == 루프 완성
						} else if (team[s] == teamno) {
							// 루프 애들 다 빼줘
							while (stack.peek() != s) {
								stack.pop();
							} 
							stack.pop();
							// 선택받지 못한 애들
							while (!stack.isEmpty()) {
								team[stack.pop()] = -1;
							}
							teamno++;
							break;
						// 다른 팀에 있거나 선택받지 못함
						} else {
							while (!stack.isEmpty()) {
								team[stack.pop()] = -1;
							}
							break;
						}
					}	
				}
			}
			int count = 0;
			for (int i = 1; i <= n; i++) {
				if (team[i] == -1) {
					count++;
				}
			}
			System.out.println(count);
		}
	}
}