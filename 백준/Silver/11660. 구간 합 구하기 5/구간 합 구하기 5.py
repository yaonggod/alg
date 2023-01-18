import sys

n, m = map(int, sys.stdin.readline().split())
original_list = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
new_list = [[0] * n for _ in range(n)]


new_list[0][0] = original_list[0][0]
for i in range(1, n):
    new_list[i][0] = new_list[i - 1][0] + original_list[i][0]
    new_list[0][i] = new_list[0][i - 1] + original_list[0][i]
for i in range(1, n):
    for j in range(1, n):
        new_list[i][j] = original_list[i][j] + new_list[i - 1][j] + new_list[i][j - 1] - new_list[i -1][j - 1]

for _ in range(m):
    x1, y1, x2, y2 = map(int, sys.stdin.readline().split())
    x1 -= 1
    y1 -= 1
    x2 -= 1
    y2 -= 1
    if x1 > 0 and y1 > 0:
        sum_list = new_list[x2][y2] - new_list[x1 - 1][y2] - new_list[x2][y1 - 1] + new_list[x1 - 1][y1 - 1]
    elif x1 == 0 and y1 > 0:
        sum_list = new_list[x2][y2] - new_list[x2][y1 - 1]
    elif x1 > 0 and y1 == 0:
        sum_list = new_list[x2][y2] - new_list[x1 - 1][y2]
    else:
        sum_list = new_list[x2][y2]
    print(sum_list)