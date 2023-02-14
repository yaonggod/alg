import sys
from collections import deque
input = sys.stdin.readline

n, m, k = map(int, input().split())
board = [['.'] * m for _ in range(n)]
for _ in range(k):
    a, b = map(int, input().split())
    board[a - 1][b - 1] = '#'

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
max_size = 0
size = 0

for i in range(n):
    for j in range(m):
        if board[i][j] == '#':
            size = 1
            board[i][j] = '.'
            queue = deque([[i, j]])
            while queue:
                c = queue.popleft()
                x = c[0]
                y = c[1]
                for d in range(4):
                    nx = x + dx[d]
                    ny = y + dy[d]
                    if 0 <= nx < n and 0 <= ny < m and board[nx][ny] == '#':
                        queue.append([nx, ny])
                        board[nx][ny] = '.'
                        size += 1
            if size > max_size:
                max_size = size

print(max_size) 