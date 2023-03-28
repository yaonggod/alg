import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int t = sc.nextInt();
		for (int tc = 1; tc <= t; tc++) {
			sb.append("#" + tc + " ");
			// 이용권 가격
			int day = sc.nextInt();
			int mon1 = sc.nextInt();
			int mon3 = sc.nextInt();
			int year = sc.nextInt();
			// 이용 계획
			int[] plan = new int[12];
			for (int i = 0; i < 12; i++) {
				plan[i] = sc.nextInt();
			}
			// 1월 - 1일/1월/3월/1년 -> 다음 결제 월 : 2월/2월/4월/13월(없음)
			// BFS해서 다음 결제월이 12 넘으면 끝내면됨
			
			Queue<Integer[]> queue = new LinkedList<>();
			// 다음 결제 월, 비용 넣고
			queue.offer(new Integer[] {0, 0});
			int minCost = Integer.MAX_VALUE;
			while (!queue.isEmpty()) {
				Integer[] mc = queue.poll();
				int m = mc[0];
				int c = mc[1];
				if (m >= 12) {
					if (c < minCost) minCost = c;
				} else {
					// 1일
					queue.offer(new Integer[] {m + 1, c + plan[m] * day});
					// 1달
					queue.offer(new Integer[] {m + 1, c + mon1});
					// 3달
					queue.offer(new Integer[] {m + 3, c + mon3});
					// 1년은 m == 0일때만 하는걸로
					if (m == 0) {
						queue.offer(new Integer[] {m + 12, c + year});
					}
				}
			}
			sb.append(minCost + "\n");
		}
		System.out.println(sb);
	}
}