dp = [0] * 101
dp[1] = 1
dp[2] = 1 # 1
dp[3] = 1 # 2
dp[4] = 2 # 1, 3
dp[5] = 2 # 4
dp[6] = 3 # 3, 5
dp[7] = 4 # 2, 6
dp[8] = 5 # 1, 7
dp[9] = 7 # 4, 8
dp[10] = 9 # 5, 9
dp[11] = 12 # 6, 10
dp[12] = 16 # 7, 11
dp[13] = 21 # 8, 12
for i in range(9, 101):
  dp[i] = dp[i - 5] + dp[i - 1]

t = int(input())
for _ in range(t):
  print(dp[int(input())])