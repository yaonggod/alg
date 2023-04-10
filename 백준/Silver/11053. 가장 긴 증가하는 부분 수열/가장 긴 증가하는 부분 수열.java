import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		// arr[i]까지 넣었을 때 가장 길어지는 수열의 길이
		int[] len = new int[n];
		Arrays.fill(len, 1);
		// i길이에서 마지막값으로 들어갈 수 있는 가장 작은 수
		int[] min = new int[n + 1];
		Arrays.fill(min, Integer.MAX_VALUE);
		// 초기값
		len[0] = 1;
		int max = 1;
		for (int i = 0; i < n; i++) {
			for (int j = max; j >= 1; j--) {
				if (min[j] < arr[i]) {
					len[i] = j + 1;
					if (len[i] > max) max = len[i];
					min[len[i]] = arr[i];
					break;
				}
			}
			if (len[i] == 1 && min[1] > arr[i]) {
				min[1] = arr[i];
			}
//			System.out.println(Arrays.toString(len));
//			System.out.println(Arrays.toString(min));
		}
		System.out.println(max);
	}
}