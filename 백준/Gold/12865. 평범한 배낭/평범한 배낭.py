import sys
input = sys.stdin.readline
n, k = map(int, input().split())
lst = [[]]
for _ in range(n):
    lst.append(list(map(int, input().split())))
    # 가치
knapsack = [[0] * (k + 1) for _ in range(n + 1)]
for i in range(1, n + 1):
    for j in range(1, k + 1):
        if j - lst[i][0] >= 0:
            knapsack[i][j] = max(knapsack[i - 1][j], knapsack[i - 1][j - lst[i][0]] + lst[i][1])
        else:
            knapsack[i][j] = knapsack[i - 1][j]
print(knapsack[n][k])