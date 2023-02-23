import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int tc = 1; tc < 11; tc++) {
			int n = sc.nextInt();
			Queue<Integer> pw = new LinkedList<>();
			for (int i = 0; i < 8; i++) {
				pw.offer(sc.nextInt());
			}
			int minus = 1;
			while (true) {
				int x = Math.max(pw.poll() - minus, 0);
				pw.offer(x);
				if (x == 0) {
					break;
				}
				minus++;
				if (minus == 6) {
					minus = 1;
				}
			}
			System.out.print("#" + tc + " ");
			while (!pw.isEmpty()) {
				System.out.print(pw.poll() + " ");
			}
            System.out.println();
		}
	}
}