import sys
input = sys.stdin.readline
n = int(input())
m = int(input())
seat = [0] * n
for _ in range(m):
    seat[int(input()) - 1] = 1


# 1 ~ n - 1 범위 내에서 숫자들을 연속하지 않게 고르는 방법의 가지 수
dp = [0] * 41
dp[1] = 1
dp[2] = 2
for i in range(3, 41):
    dp[i] = dp[i - 1] + dp[i - 2]
s = 0
answer = []
a = 1
for i in range(len(seat)):
    if seat[i] == 0:
        s += 1
    else:
        if s > 0:
            answer.append(s)
        s = 0
if s > 0:
    answer.append(s)

for ans in answer:
    a *= dp[ans]
print(a)