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
		// 스택에다가 비교대상 넣음
		stack.push(arr[n - 1]);
		// 마지막 수는 보나마나 -1
		oks[n - 1] = -1;
		
		for (int i = n - 2; i >= 0; i--) {
			boolean flag = false;
			while (!stack.isEmpty()) {
				int p = stack.pop();
				if (arr[i] < p) {
					flag = true;
					// 오큰수 당첨
					oks[i] = p;
					// 다음 수에도 비교 대상으로
					stack.push(p);
					break;
				}
			}
			// 오큰수 없엉 => -1
			if (!flag) {
				oks[i] = -1;
			}
			// 나 자신도 다음 수의 비교 대상으로 넣기
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
