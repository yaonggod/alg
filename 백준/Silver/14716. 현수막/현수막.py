import sys
from collections import deque
input = sys.stdin.readline

m, n = map(int, input().split())
poster = [list(map(int, input().split())) for _ in range(m)]

count = 0
dx = [-1, 1, 0, 0, -1, -1, 1, 1]
dy = [0, 0, -1, 1, -1, 1, -1, 1]

for i in range(m):
    for j in range(n):
        if poster[i][j]:
            queue = deque([[i, j]])
            poster[i][j] = 0
            while queue:
                k = queue.popleft()
                x = k[0]
                y = k[1]
                for d in range(8):
                    nx = x + dx[d]
                    ny = y + dy[d]
                    if 0 <= nx < m and 0 <= ny < n and poster[nx][ny]:
                        queue.append([nx, ny])
                        poster[nx][ny] = 0
            count += 1

print(count)