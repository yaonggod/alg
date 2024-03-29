import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[] min, len;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// arr[i]까지 넣었을 때 가장 길어지는 수열의 길이
		len = new int[n];
		Arrays.fill(len, 1);
		// i길이에서 마지막값으로 들어갈 수 있는 가장 작은 수
		min = new int[n + 1];
		Arrays.fill(min, Integer.MAX_VALUE);
		if (n == 1) {
			System.out.println(1);
			System.out.println(arr[0]);
		}
		else {
			int currentlen = 1;
			for (int i = 0; i < n; i++) {
				// 바이너리서치
				// min[target] < arr[i] < min[target + 1]인 타겟을 찾아야댐
				// 그러면 arr[i]까지 고려한 수열의 최장 길이는 target + 1이고
				// len[i] = target + 1
				// min[target + 1] = arr[i]
				int length = binarysearch(arr[i], 1, currentlen + 1);
				// 못찾았음 : arr[i]가 가장 작음
				if (length == -1) {
					min[1] = arr[i];
				} else {
					len[i] = length + 1;
					min[length + 1] = arr[i];
					if (length > currentlen) {
						currentlen = length;
					}
				}
			}
			int max = 0;
			int startidx = -1;
			for (int i = 0; i < n; i++) {
				if (len[i] > max) {
					max = len[i];
					startidx = i;
				}
			}
			System.out.println(max);
			Stack<Integer> stack = new Stack<>();
			stack.push(arr[startidx]);
			for (int i = startidx - 1; i >= 0; i--) {
				if (len[i] == max - 1) {
					stack.push(arr[i]);
					max--;
				}
			}
			while (!stack.isEmpty()) {
				System.out.print(stack.pop() + " ");
			}
		}
	}
	public static int binarysearch(int x, int start, int end) {
		if (start > end) return -1;
		int mid = (start + end) / 2;
		if (min[mid] == x) return mid - 1;
		if (min[mid + 1] == x) return mid;
		if (min[mid] < x && x < min[mid + 1]) return mid;
		if (min[mid] < x && min[mid + 1] < x) {
			return binarysearch(x, mid + 1, end);
		}
		if (min[mid] > x && min[mid + 1] > x) {
			return binarysearch(x, start, mid - 1);
		}
		return -1;
	}
}