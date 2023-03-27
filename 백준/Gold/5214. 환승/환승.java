import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int k = Integer.parseInt(line[1]);
		int m = Integer.parseInt(line[2]);
		
		// N이 속한 하이퍼튜브로 가서 한 칸만 더 가면 됨
		// 하이퍼튜브에 어떤 역이 있는지
		List<Integer>[] hypertube = new ArrayList[m];
		for (int i = 0; i < m; i++) {
			hypertube[i] = new ArrayList<>();
		}
		// 역은 어떤 하이퍼튜브에 속해있는지
		List<Integer>[] station = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			station[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			// i번 하이퍼튜브
			Integer[] h = new Integer[k];
			line = br.readLine().split(" ");
			for (int j = 0; j < k; j++) {
				h[j] = Integer.parseInt(line[j]);
			}
			for (int j = 0; j < k; j++) {
				// i번 하이퍼튜브 - h[j]번 역
				hypertube[i].add(h[j]);
				station[h[j]].add(i);
			}
		}
		
		Queue<Integer> queue = new LinkedList<>();
		// 역 방문처리
		int[] vs = new int[n + 1];
		// 하이퍼튜브 방문처리
		boolean[] vh = new boolean[m];
		Arrays.fill(vs, -1);
		vs[1] = 1;
		queue.offer(1);
		while (!queue.isEmpty()) {
			int x = queue.poll();
			if (x == n) {
				break;
			}
			// x는 자기가 속한 하이퍼튜브의 역으로 한 칸만에 갈 수 있음
			for (int hype : station[x]) {
				if (vh[hype] == false) {
					vh[hype] = true;
					for (int st : hypertube[hype]) {
						if (vs[st] == -1) {
							vs[st] = vs[x] + 1;
							queue.offer(st);
						}
					}
				}
			}
		}
		System.out.println(vs[n]);
	}	
}