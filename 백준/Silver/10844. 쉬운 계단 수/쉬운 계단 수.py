n = int(input())

dp = [[0] * 11 for _ in range(101)]
dp[1] = [0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0]
for i in range(2, 101):
    dp[i][0] = dp[i - 1][1]
    for j in range(1, 10):
        dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1]

print(sum(dp[n]) % 1000000000)