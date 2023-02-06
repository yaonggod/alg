import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] road = new int[n - 1];
		for (int i = 0; i < n - 1; i++) {
			road[i] = sc.nextInt();
		}
		int[] city = new int[n];
		for (int i = 0; i < n; i++) {
			city[i] = sc.nextInt();
		}
		long answer = 0;
		long min_price = city[0];
		for (int i = 0; i < n - 1; i++) {
			if (city[i] < min_price) {
				min_price = city[i];
			}
			answer += min_price * road[i];
		}
		System.out.println(answer);
	}	
}
