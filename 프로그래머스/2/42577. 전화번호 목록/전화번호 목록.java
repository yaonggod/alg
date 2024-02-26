import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i].length() < phone_book[i + 1].length()) {
                int sub = phone_book[i].length();
                if (phone_book[i + 1].substring(0, sub).equals(phone_book[i])) {
                    return false;
                }
            }
        }
        return true;
    }
}