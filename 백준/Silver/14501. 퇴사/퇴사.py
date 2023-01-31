import sys
input = sys.stdin.readline

n = int(input())
lst = []
dp = []
for i in range(n):
    t, p = map(int, input().split())
    if i + t > n:
        lst.append((0, 0))
        dp.append(0)
    else:
        lst.append((t, p))
        dp.append(p)

for i in range(n):
    for j in range(i + lst[i][0], n):
        if dp[i] + lst[j][1] > dp[j]:
            # print(i, j)
            dp[j] = dp[i] + lst[j][1]
            # print(dp)
print(max(dp))
