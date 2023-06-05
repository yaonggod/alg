import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] word = sc.next().toCharArray();
		int[] alphabet = new int[26];
		
		for (char i : word) {
			alphabet[(int)i - 97]++;
		}
		
		for (int i = 0; i < 26; i++) {
			System.out.print(alphabet[i]);
			System.out.print(' ');
		}
	}
	
}