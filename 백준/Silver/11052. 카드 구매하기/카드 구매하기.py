import sys
input = sys.stdin.readline
n = int(input())
lst = [0] + list(map(int, input().split()))

answer = [0] * (n + 1)
answer[1] = lst[1]

for i in range(2, n + 1):
    max_val = lst[i]
    for j in range(1, i):
        if answer[i - j] + lst[j] > max_val:
            max_val = answer[i - j] + lst[j]
    answer[i] = max_val

print(answer[n])