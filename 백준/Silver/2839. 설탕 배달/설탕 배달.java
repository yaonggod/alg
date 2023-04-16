import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int[] sugar = new int[n + 1];
		Arrays.fill(sugar, -1);
		sugar[0] = 0;
		
		for (int i = 3; i <= n; i++) {
			if (sugar[i - 3] != -1) {
				sugar[i] = sugar[i - 3] + 1;
			}
		}
		
		for (int i = 5; i <= n; i++) {
			if (sugar[i - 5] != -1) {
				if (sugar[i] != -1) {					
					sugar[i] = Math.min(sugar[i - 5] + 1, sugar[i]);
				} else {
					sugar[i] = sugar[i - 5] + 1;
				}
			}
		}
		System.out.println(sugar[n]);
	}
	
}