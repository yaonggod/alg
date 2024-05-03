class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int g = gcdArr(arrayA);
        if (g != 1) {
            g = maxa(g, arrayB);
        }
        
        int g2 = gcdArr(arrayB);
        if (g2 != 1) {
            g2 = maxa(g2, arrayA);
        }
        if (g == 1 && g2 == 1) return 0;
        if (g == 1) return g2;
        if (g2 == 1) return g;
        return Math.max(g, g2);
    }
    
    static int gcdArr(int[] arr) {
        int g = arr[0];
        for (int i = 1; i < arr.length; i++) {
            g = gcd(g, arr[i]);
            if (g == 1) return 1;
        }
        return g;
    }
    
    static int gcd(int a, int b) {
        int r = a % b;
        if (r == 0) return b;
        return gcd(b, r);
    }
    
    static int maxa(int g, int[] arr) {
        
        for (int i = g; i >= 1; i--) {
            if (g % i != 0) continue;
            // 약수
            boolean canDiv = false;
            for (int a : arr) {
                if (a % i == 0) {
                    canDiv = true;
                    break;
                }
            }
            if (!canDiv) return i;
        }
        return 1;
    }
}