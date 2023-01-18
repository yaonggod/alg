import sys
input = sys.stdin.readline

n = int(input())
step = [0]
for _ in range(n):
    step.append(int(input()))

if n == 1:
    print(step[1])
elif n == 2:
    print(sum(step))
else:
    dp = [0] * (n + 1)
    dp[1] = step[1]
    dp[2] = step[1] + step[2]
    for i in range(3, n - 1):
        dp[i] = max(dp[i - 3] + step[i - 1] + step[i], dp[i - 2] + step[i])
        
    dp[n] = max(dp[n - 2] + step[n], dp[n - 3] + step[n - 1] + step[n])
    print(dp[n])