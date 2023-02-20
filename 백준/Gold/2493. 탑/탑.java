import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine());
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int[] turret = new int[n];
		for (int i = 0; i < n; i++) {
			turret[i] = Integer.parseInt(st.nextToken());
		}
		Stack<Integer> stack = new Stack<>();
		int[] receive = new int[n];
		// 인덱스 + 1로...
		stack.push(1);
		// 왼쪽보다 작은 터렛은 아무짝에도 쓸모가 없다~
		for (int i = 1; i < n; i++) {
			while (!stack.isEmpty()) {
				int p = stack.pop();
				// 왼쪽에 나보다 큰 터렛이 있어요
				if (turret[i] < turret[p - 1]) {
					receive[i] = p;
					stack.push(p);
					break;
				}
			}
			stack.push(i + 1);
		}
		StringBuilder sb = new StringBuilder();
		for (int r : receive) {
			sb.append(r);
			sb.append(" ");
		}
		System.out.println(sb);
	}
}