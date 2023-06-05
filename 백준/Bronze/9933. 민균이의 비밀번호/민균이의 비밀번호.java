import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String[] list = new String[n];
		String[] reverse = new String[n];
		for (int i = 0; i < n; i++) {
			String word = sc.next();
			String rev = "";
			boolean find = false;
			for (char alp : word.toCharArray()) {
				rev = alp + rev;
			}
			list[i] = word;
			reverse[i] = rev;
			int len = word.toCharArray().length;
			for (int j = 0; j <= i; j++) {
				if (rev.equals(list[j])) {
					System.out.print(len);
					System.out.print(" ");
					System.out.print(rev.charAt(len / 2));
					find = true;
					break;
				}
				if (find) {
					break;
				}
			}
		}
	}
}