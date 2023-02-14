import sys
from collections import deque
from itertools import combinations
input = sys.stdin.readline
board = [list(input()) for _ in range(5)]

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
lst = []
for i in range(5):
    for j in range(5):
        lst.append([i, j])

count = 0
comb = list(combinations(lst, 7))
for c in comb:
    flag = True
    board_check = [[0] * 5 for _ in range(5)]
    s_count = 0
    for e in c:
        board_check[e[0]][e[1]] = 1
        if board[e[0]][e[1]] == 'S':
            s_count += 1
    if s_count < 4:
        flag = False
    elif s_count >= 4:
        queue = deque([[c[0][0], c[0][1]]])
        while queue:
            x, y = queue.popleft()
            for d in range(4):
                nx = x + dx[d]
                ny = y + dy[d]
                if 0 <= nx < 5 and 0 <= ny < 5 and board_check[nx][ny] == 1:
                    board_check[nx][ny] = 0
                    queue.append([nx, ny])
        for i in range(5):
            for j in range(5):
                if board_check[i][j] == 1:
                    flag = False
                    break 
    if flag == True: 
        count += 1
        
print(count)