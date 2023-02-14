n = int(input())
r1, c1, r2, c2 = map(int, input().split())
from collections import deque

dx = [-2, -2, 0, 0, 2, 2]
dy = [-1, 1, -2, 2, -1, 1]

visited = [[-1] * n for _ in range(n)]
visited[r1][c1] = 0
queue = deque([[r1, c1]])
while queue:
    x, y = queue.popleft()
    if x == r2 and y == c2:
        break
    for d in range(6):
        nx = x + dx[d]
        ny = y + dy[d]
        if 0 <= nx < n and 0 <= ny < n and visited[nx][ny] == -1:
            visited[nx][ny] = visited[x][y] + 1
            queue.append([nx, ny])
            
print(visited[r2][c2])