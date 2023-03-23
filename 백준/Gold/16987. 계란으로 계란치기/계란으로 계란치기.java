import java.util.Scanner;

public class Main {
	static int n = 0;
	static int[] dur = null;
	static int[] weight = null;
	static boolean[] broken = null;
	static int maxegg = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		dur = new int[n];
		weight = new int[n];
		broken = new boolean[n];
		for (int i = 0; i < n; i++) {
			dur[i] = sc.nextInt();
			weight[i] = sc.nextInt();
		}
		backtracking(0);
		System.out.println(maxegg);
	}
	// egg번째 계란으로 ㄱㄱ
	public static void backtracking(int egg) {
		// 마지막 계란까지 다 해봤으면 끝내
		if (egg == n) {
			int count = 0;
			for (int i = 0; i < n; i++) {
				if (broken[i]) {
					count++;
				}
			}
			if (count > maxegg) {
				maxegg = count;
			}
			return;
		}
		// 깨졌으면 하지말고 다음 단계
		if (broken[egg]) {
			backtracking(egg + 1);
		} else {
			boolean eggalive = false;
			for (int i = 0; i < n; i++) {
				// 다른 계란이면서 아직 살아있으면
				if (i != egg && broken[i] == false) {
					eggalive = true;
					// 치자
					dur[i] -= weight[egg];
					dur[egg] -= weight[i];
					if (dur[i] <= 0) {
						broken[i] = true;
					}
					if (dur[egg] <= 0) {
						broken[egg] = true;
					}
					backtracking(egg + 1);
					// 돌려놓자
					dur[i] += weight[egg];
					dur[egg] += weight[i];
					broken[i] = false;
					broken[egg] = false;
				}
			}
			// 계란이 없어도 다음단계는 해야됨
			if (!eggalive) {
				backtracking(egg + 1);
			}
		}
		
	}
}
