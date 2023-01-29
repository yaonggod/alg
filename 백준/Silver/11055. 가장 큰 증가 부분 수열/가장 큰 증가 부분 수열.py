n = int(input())
lst = [0]
lst += list(map(int, input().split()))

dp = [0] * (n + 1)
dp[1] = lst[1]
maxsum = 0
for i in range(2, n + 1):
  for j in range(i - 1, 0, -1):
    if lst[i] > lst[j] and dp[i] < dp[j] + lst[i]:
      dp[i] = dp[j] + lst[i]
  if dp[i] == 0:
    dp[i] = lst[i]  
print(max(dp))