n = int(input())
lst = [0]
lst += list(map(int, input().split()))
dp = [0] * (n + 1)
dp[1] = lst[1]
for i in range(2, n + 1):
    dp[i] = max(dp[i - 1] + lst[i], lst[i])

print(max(dp[1:]))
