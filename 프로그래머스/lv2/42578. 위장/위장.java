import java.util.Map;
import java.util.HashMap;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> c = new HashMap<>();
        for (String[] comb : clothes) {
			if (!c.containsKey(comb[1])) {
				c.put(comb[1], 1);
			} else {
				c.put(comb[1], c.get(comb[1]) + 1);
			}
		}
		int base = 1;
		for (int count : c.values()) {
			base *= (count + 1);
		}
		return base - 1;
    }
}