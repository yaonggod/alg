import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		// 한라인 읽고 int로 만들고
		int n = Integer.parseInt(bf.readLine());
		// 라인 하나 통으로 읽고
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int[] turret = new int[n];
		for (int i = 0; i < n; i++) {
			// st를 나눠 읽나봄
			// 몰라...
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
				// 왼쪽에 나보다 큰 터렛이 있어요 => 다음 터렛 비교할 때 다시 써먹어요~
				if (turret[i] < turret[p - 1]) {
					receive[i] = p;
					stack.push(p);
					break;
				}
			}
			// 나도 다음 터렛 비교할 때 쓸거에요~
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
