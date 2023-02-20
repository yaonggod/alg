import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		Stack<Integer> stack = new Stack<>();
		int[] oks = new int[n];
		stack.push(arr[n - 1]);
		oks[n - 1] = -1;
		
		for (int i = n - 2; i >= 0; i--) {
			boolean flag = false;
			while (!stack.isEmpty()) {
				int p = stack.pop();
				if (arr[i] < p) {
					flag = true;
					oks[i] = p;
					stack.push(p);
					break;
				}
			}
			if (!flag) {
				oks[i] = -1;
			}
			stack.push(arr[i]);
		}
		StringBuilder sb = new StringBuilder();
		for (int o : oks) {
			sb.append(o);
			sb.append(" ");
		}
		System.out.println(sb);
	}
}