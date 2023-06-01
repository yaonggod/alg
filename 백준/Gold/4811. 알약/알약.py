import sys

while True:
    n = int(input())
    if n == 0:
        break
    dp = [[0] * (n + 1) for _ in range(n + 1)]
    # 반개, 한개
    dp[0][n] = 1

    for i in range(n, -1, -1):
        for j in range(n - i, -1, -1):
            if j - 1 >= 0:
                dp[j - 1][i] += dp[j][i]
            if j + 1 <= n:
                dp[j + 1][i - 1] += dp[j][i]

    print(dp[0][0])