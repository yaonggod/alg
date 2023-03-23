import java.util.Scanner;

public class Solution {
	static int[] col = null;
	static int n = 0;
	static int count = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int t = sc.nextInt();
		for (int tc = 1; tc <= t; tc++) {
			sb.append("#" + tc + " ");
			n = sc.nextInt();
			count = 0;
			// idx번 행에 칠해진 col
			col = new int[n + 1];
			// 1행부터 채우자
			nqueen(1);
			sb.append(count + "\n");
		}
		System.out.println(sb);
	}
	
	public static void nqueen(int r) {
		if (r == n + 1) {
			count++;
			return;
		}
		for (int c = 1; c <= n; c++) {
			// 일단 넣고
			col[r] = c;
			// 없으면 못감
			if (promising(r)) {
				nqueen(r + 1);
			}
		}
	}
	
	public static boolean promising(int r) {
		// r 이전 행만 검사
		for (int i = 1; i < r; i++) {
			// 같은 열에 이미 있거나
			// 대각선에 있거나
			if (col[i] == col[r] || Math.abs(col[i] - col[r]) == Math.abs(i - r)) {
				return false;
			}
		}
		return true;
	}
	
}