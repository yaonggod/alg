import sys
input = sys.stdin.readline

n = int(input())
lst = list(map(int, input().split()))
dp = [[] for _ in range(n)]
dp[0].append(0)
for i in range(1, n):
    for j in range(i):
        if lst[j] < lst[i]:
            if len(dp[i]) < len(dp[j] + [i]):
                dp[i] = dp[j] + [i]
    if len(dp[i]) == 0:
        dp[i].append(i)
max_len = 0
for i in range(n):
    if len(dp[i]) > max_len:
        max_len = len(dp[i])
# print(dp)
print(max_len)
for i in range(n):
    if len(dp[i]) == max_len:
        for idx in dp[i]:
            print(lst[idx], end = " ")
        break