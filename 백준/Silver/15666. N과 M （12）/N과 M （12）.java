import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	static int n = 0;
	static int m = 0;
	static Map<Integer, Integer> map = new HashMap<>();
	static int[] arr = null;
	static int[] newarr = null;
	static Integer[] keyset = null;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		arr = new int[n];
		newarr = new int[m];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		for (int i = 0; i < n; i++) {
			if (map.containsKey(arr[i])) {
				map.put(arr[i], map.get(arr[i]) + 1);
			} else {
				map.put(arr[i], 1);
			}
		}
		keyset = map.keySet().toArray(new Integer[0]);
		Arrays.sort(keyset);
		backtracking(0, 0);
		System.out.println(sb);
	}
	
	public static void backtracking(int key, int c) {
		if (c == m) {
			for (int i = 0; i < m; i++) {
				sb.append(newarr[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for (int i = key; i < keyset.length; i++) {
			newarr[c] = keyset[i];
			backtracking(i, c + 1);
		}
	}
}
