import sys
input = sys.stdin.readline

n = int(input())
lst = [(0, 0)]
dp = [0] * (n + 1)
for i in range(n):
    t, p = map(int, input().split())
    if i + t > n:
        lst.append((1, 0))
    else:
        lst.append((t, p))
max_list = [0] * (n + 1)
p_max = 0
for i in range(n, 0, -1):
    if i + lst[i][0] < n + 1:
        if dp[i] < max_list[i + lst[i][0]] + lst[i][1]:
            dp[i] = max_list[i + lst[i][0]] + lst[i][1]
    else:
        dp[i] = lst[i][1]
    if dp[i] > p_max:
        p_max = dp[i]
    max_list[i] = p_max

print(max(dp))