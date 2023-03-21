import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
	static int k = 0;
	static List<char[]> word = null;
	static Set<Integer> alphabet = null;
	static Integer[] alpArray = null;
	static int ans = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = 0;
		// 알파벳에서 97 빼야댐
		int n = sc.nextInt();
		k = sc.nextInt();
		word = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			word.add(sc.next().toCharArray());
		}
		alphabet = new HashSet<>();
		// antic는 기본으로 깔아두고
		if (k >= 5) {
			num += 1 << ('a' - 97);
			num += 1 << ('n' - 97);
			num += 1 << ('t' - 97);
			num += 1 << ('i' - 97);
			num += 1 << ('c' - 97);
			// antic을 제외한 알파벳을 넣어보자
			for (char[] w : word) {
				for (int i = 4; i < w.length - 4; i++) {
					if ((num & (1 << (w[i] - 97))) == 0) {
						alphabet.add(w[i] - 97);
					}
				}
			}
			alpArray = alphabet.toArray(new Integer[alphabet.size()]);
			// 단어에 있는 모든 알파벳을 가르칠 수 있음
			if (alphabet.size() <= k - 5) {
				ans = n;
			// 알파벳을 k - 5개 골라서 배워보자
			} else {
				teachAlphabet(num, 0, 0);
			}
		}
		System.out.println(ans);
	}
	
	// 알파벳을 k - 5개 뽑아서 가르치자
	public static void teachAlphabet(int num, int idx, int cnt) {
		if (cnt == k - 5) {
			int wordCount = 0;
			for (char[] w : word) {
				boolean teach = true;
				for (int i = 4; i < w.length - 4; i++) {
					// 알파벳이 없음
					if ((num & (1 << (w[i] - 97))) == 0) {
						teach = false;
						break;
					}
				}
				if (teach) {
					wordCount++;
				}
			}
			if (wordCount > ans) {
				ans = wordCount;
			}
			return;
		} 
		// 다 못채웠음
		if (idx == alpArray.length) {
			return;
		}
		// idx번째 알파벳 안가르친다
		teachAlphabet(num, idx + 1, cnt);
		// idx번째 알파벳 가르친다
		num += (1 << alpArray[idx]);
		teachAlphabet(num, idx + 1, cnt + 1);
	}
}
