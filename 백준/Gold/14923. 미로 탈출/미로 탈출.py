import sys
from collections import deque
input = sys.stdin.readline

n, m = map(int, input().split())
hx, hy = map(int, input().split())
ex, ey = map(int, input().split())
hx -= 1
hy -= 1
ex -= 1
ey -= 1
miro = [list(map(int, input().split())) for _ in range(n)]
visited = [[[0, 0] for _ in range(m)] for _ in range(n)]
visited[hx][hy] = [1, 0]

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
result = False
queue = deque([[hx, hy, 0]])
while queue:
    x, y, z = queue.popleft()
    if x == ex and y == ey:
        result = True
        print(visited[x][y][z] - 1)
        break
    for d in range(4):
        nx = x + dx[d]
        ny = y + dy[d]
        if 0 <= nx < n and 0 <= ny < m:
            if miro[nx][ny] == 1 and z == 0:
                visited[nx][ny][1] = visited[x][y][0] + 1
                queue.append([nx, ny, 1])
            elif miro[nx][ny] == 0 and visited[nx][ny][z] == 0:
                visited[nx][ny][z] = visited[x][y][z] + 1
                queue.append([nx, ny, z])
                
if result == False:
    print(-1)