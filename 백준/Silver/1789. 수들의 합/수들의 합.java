import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		long psum = 1;
		long nsum = 3;
		long i = 1;
		while (true) {
			if (n >= psum && n < nsum) {
				System.out.println(i);
				break;
			}
			psum += ++i;
			nsum += (i + 1);
		}
	}	
}