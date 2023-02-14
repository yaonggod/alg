import sys
input = sys.stdin.readline
from collections import deque

r, c = map(int, input().split())
miro = [list(input().strip()) for _ in range(r)]
visited = [[-1] * c for _ in range(r)] 
queue = deque()
for i in range(r):
    for j in range(c):
        if miro[i][j] == 'F':
            queue.append([i, j, 'fire'])
for i in range(r):
    for j in range(c):
        if miro[i][j] == 'J':
            queue.append([i, j, 'jihoon'])
            visited[i][j] = 0
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
result = 'IMPOSSIBLE'
while queue:
    x, y, type = queue.popleft()
    if not (0 <= x < r and 0 <= y < c):
        result = type
        break
    else:
        for d in range(4):
            nx = x + dx[d]
            ny = y + dy[d]
            if type == 'fire' and 0 <= nx < r and 0 <= ny < c and (miro[nx][ny] == 'J' or miro[nx][ny] == '.'):
                miro[nx][ny] = 'F'
                queue.append([nx, ny, 'fire'])
            if type == 'jihoon' and 0 <= nx < r and 0 <= ny < c and visited[nx][ny] == - 1 and miro[nx][ny] == '.':
                visited[nx][ny] = visited[x][y] + 1
                queue.append([nx, ny, 'jihoon'])
            if type == 'jihoon' and not (0 <= nx < r and 0 <= ny < c):
                queue.append([nx, ny, visited[x][y] + 1])
                 
print(result)