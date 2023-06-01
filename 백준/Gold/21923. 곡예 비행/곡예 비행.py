import sys

n, m = map(int, input().split())
score = []
for _ in range(n):
    score.append(list(map(int, input().split())))

total = [[0] * m for _ in range(n)]
total2 = [[0] * m for _ in range(n)]
total[n - 1][0] = score[n - 1][0]
total2[n - 1][m - 1] = score[n - 1][m - 1]

from pprint import pprint

# 상승
for i in range(1, n + m - 1):
    for j in range(i + 1):
        if not (n - i - 1 + j < 0 or n - i - 1 + j >= n or j < 0 or j >= m):
            if j == 0:
                total[n - i - 1 + j][j] = total[n - i + j][j] + score[n - i - 1 + j][j]
            elif n - i - 1 + j == n - 1:
                total[n - i - 1 + j][j] = total[n - i - 1 + j][j - 1] + score[n - i - 1 + j][j]
            else:
                total[n - i - 1 + j][j] = max(total[n - i + j][j], total[n - i - 1 + j][j - 1]) + score[n - i - 1 + j][j]

# 하강
for i in range(1, n + m - 1):
    for j in range(i, -1, -1):
        if not (n - i - 1 + j < 0 or n - i - 1 + j >= n or m - 1 - j < 0 or m - 1 - j >= m):
            if n - i - 1 + j == n - 1:
                total2[n - i - 1 + j][m - 1 - j] = total2[n - i - 1 + j][m - j] + score[n - i - 1 + j][m - 1 - j]
            elif m - 1 - j == m - 1:
                total2[n - i - 1 + j][m - 1 - j] = total2[n - i + j][m - 1 - j] + score[n - i - 1 + j][m - 1 - j]
            else:
                total2[n - i - 1 + j][m - 1 - j] = max(total2[n - i - 1 + j][m - j], total2[n - i + j][m - 1 - j]) + score[n - i - 1 + j][m - 1 - j]

max_score = -10000000001
for i in range(n):
    for j in range(m):
        if total[i][j] + total2[i][j] > max_score:
            max_score = total[i][j] + total2[i][j]

print(max_score)