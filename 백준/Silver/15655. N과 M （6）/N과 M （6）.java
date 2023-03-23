import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	static int n = 0;
	static int m = 0;
	static int[] arr = null;
	static Stack<Integer> stack = new Stack<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		backtracking(0);
	}
	
	public static void backtracking(int idx) {
		if (stack.size() == m) {
			for (Integer e : stack) {
				System.out.print(e + " ");
			}
			System.out.println();
			return;
		}
		if (idx == n) {
			return;
		}
		// idx 넣자
		stack.add(arr[idx]);
		backtracking(idx + 1);
		stack.pop();
		// idx 넣지 말자
		backtracking(idx + 1);
	}
}