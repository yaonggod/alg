import sys
input = sys.stdin.readline
n = int(input())
a = list(map(int, input().split()))

up = [1] * n
down = [1] * n

for i in range(1, n):
    for j in range(i):
        if a[j] < a[i]:
            up[i] = max(up[i], up[j] + 1)

for i in range(n - 2, -1, -1):
    for j in range(n - 1, i, -1):
        if a[j] < a[i]:
            down[i] = max(down[i], down[j] + 1)

answer = 0
for i in range(n):
    if up[i] + down[i] > answer:
        answer = up[i] + down[i]
print(answer - 1)