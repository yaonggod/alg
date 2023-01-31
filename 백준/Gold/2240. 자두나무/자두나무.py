import sys
input = sys.stdin.readline

t, w = map(int, input().split())
tree = [0]
for _ in range(t):
    tree.append(int(input()) - 1)

dp = [[[0] * (w + 1), [0] * (w + 1)] for _ in range(t + 1)]
if tree[1] == 0:
    dp[1][0][0] = 1
else:
    dp[1][1][1] = 1

for i in range(2, t + 1):
    if tree[i] == 0:
        dp[i][0][0] = dp[i - 1][0][0] + 1
        dp[i][1][0] = dp[i - 1][1][0]
        for j in range(1, w + 1):
            dp[i][0][j] = max(dp[i - 1][0][j] + 1, dp[i - 1][1][j - 1] + 1)
            dp[i][1][j] = max(dp[i - 1][1][j], dp[i - 1][0][j - 1])
    else:
        dp[i][0][0] = dp[i - 1][0][0]
        dp[i][1][0] = dp[i - 1][1][0] + 1
        for j in range(1, w + 1):
            dp[i][0][j] = max(dp[i - 1][0][j], dp[i - 1][1][j - 1])
            dp[i][1][j] = max(dp[i - 1][1][j] + 1, dp[i - 1][0][j - 1] + 1)
print(max(max(dp[t][0]), max(dp[t][1])))