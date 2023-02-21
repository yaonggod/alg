import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] arr = sc.next().toCharArray();
		Stack<Character> notNum = new Stack<>();
		StringBuilder sb = new StringBuilder();
		// A 17
		for (int i = 0; i < arr.length; i++) {
			// 숫자
			if (arr[i] - '0' >= 17 && arr[i] - '0' < 43) {
				sb.append(arr[i]);
			}
			// +- => +- 털고 넣기
			// +- => */ 털고 넣기
			else if (arr[i] == '+' || arr[i] == '-') {
				// 비어있는 큐에 그냥 넣습니다
				if (notNum.isEmpty()) {
					notNum.push(arr[i]);
				}
				// ( 만나면 그냥 넣습니다
				else if (notNum.peek() == '(') {
					notNum.push(arr[i]);
				}
				// +-*/ : 털고 넣기
				else if (notNum.peek() == '+' || notNum.peek() == '-' || notNum.peek() == '*' || notNum.peek() == '/') {
					while (!notNum.isEmpty()) {
						char y = notNum.peek();
						// 괄호를 만나면 멈춥니다
						if (y == '(') {
							break;
						} else {
							sb.append(notNum.pop());
						}
					}
					// 그리고 나를 넣습니다
					notNum.push(arr[i]);
				}
			}
			// */ 다 털고 넣나봄...
			else if (arr[i] == '*' || arr[i] == '/') {
				// 비어있는 큐에 그냥 넣습니다
				if (notNum.isEmpty()) {
					notNum.push(arr[i]);
				}
				// ( 만나면 그냥 넣습니다
				else if (notNum.peek() == '(') {
					notNum.push(arr[i]);
				}
				// */ :	+-( 나올때까지 털기
				else if (notNum.peek() == '*' || notNum.peek() == '/') {
					while (!notNum.isEmpty()) {
						char y = notNum.peek();
						// +-( 만나면 멈춥니다
						if (y == '(' || y == '+' || y == '-') {
							break;
						} else {
							sb.append(notNum.pop());
						}
					}
					// 그리고 나를 넣습니다
					notNum.push(arr[i]);
				} 
				// +- : 넣기
				else if (notNum.peek() == '+' || notNum.peek() == '-') {
					notNum.push(arr[i]);
				}
			}
			// ( 그냥 넣습니다
			else if (arr[i] == '(') {
				notNum.push(arr[i]);
			}
			// ) 괄호 짝을 만날때까지 연산자를 텁니다
			else if (arr[i] == ')') {
				while (true) {
					if (notNum.peek() == '(') {
						notNum.pop();
						break;
					} else {
						sb.append(notNum.pop());
					}
				}
			}
		}
		while (!notNum.isEmpty()) {
			sb.append(notNum.pop());
		}
		System.out.println(sb);
		
	}
}