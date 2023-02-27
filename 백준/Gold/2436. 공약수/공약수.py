from math import gcd
ab_gcd, ab_lcm = map(int, input().split())

if ab_gcd == ab_lcm:
    print(ab_gcd, ab_gcd)
else:
    ab = ab_lcm // ab_gcd
    ab_yaksu = []
    for i in range(1, int(ab ** 0.5) + 1):
        if ab % i == 0:
            ab_yaksu.append(i)
            ab_yaksu.append(ab // i)
    ab_yaksu.sort()
    min_a_plus_b = ab_yaksu[0] + ab_yaksu[-1]
    a = ab_yaksu[0]
    b = ab_yaksu[-1]
    for i in range(1, (len(ab_yaksu) + 1) // 2):
        if ab_yaksu[i] + ab_yaksu[-i - 1] < min_a_plus_b and gcd(ab_yaksu[i], ab_yaksu[-i - 1]) == 1:
            a = ab_yaksu[i]
            b = ab_yaksu[-i - 1]

    print(a * ab_gcd, b * ab_gcd)