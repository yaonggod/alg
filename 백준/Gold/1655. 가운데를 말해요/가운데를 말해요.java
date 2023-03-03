import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {
	static PriorityQueue<Integer> left;
	static PriorityQueue<Integer> right;
	static BufferedWriter bw;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// 중간 수보다 작은 수
		// 작은 수중에서 제일 큰 수 찾아야 하니까 음수로 넣음
		left = new PriorityQueue<>();
		// 중간 수보다 큰 수
		right = new PriorityQueue<>();
		int n = Integer.parseInt(br.readLine());
		// 첫 번째 수
		int first = Integer.parseInt(br.readLine());
		bw.write(first + "\n");
		// 두 번째 수
		if (n > 1) {
			int second = Integer.parseInt(br.readLine());
			if (first <= second) {
				bw.write(first + "\n");
				left.offer(-first);
				right.offer(second);
			} else {
				bw.write(second + "\n");
				left.offer(-second);
				right.offer(first);
			}	
		}
		// 세 번째 수부터 ㄱㄱ
		for (int i = 0; i < n - 2; i++) {
			int a = Integer.parseInt(br.readLine());
			if (left.size() == right.size()) {
				left.offer(-a);
				if (-left.peek() > right.peek()) {
					left.offer(-right.poll());
					right.offer(-left.poll());
				}
			} else {
				right.offer(a);
				if (-left.peek() > right.peek()) {
					left.offer(-right.poll());
					right.offer(-left.poll());
				}
			}
			bw.write(-left.peek() + "\n");
		}
		bw.flush();
		bw.close();
	}
}