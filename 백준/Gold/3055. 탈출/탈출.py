import sys
input = sys.stdin.readline
from collections import deque
r, c = map(int, input().split())
forest = [list(input().strip()) for _ in range(r)]
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

visited = [[-1] * c for _ in range(r)]

# 도착지         
for i in range(r):
    for j in range(c):
        if forest[i][j] == 'D':
            beaver = [i, j]

queue = deque()
for i in range(r):
    for j in range(c):
        if forest[i][j] == '*':
            queue.append([i, j, 'water'])
for i in range(r):
    for j in range(c):
        if forest[i][j] == 'S':
            queue.append([i, j, 'hog'])
            visited[i][j] = 0

            
while queue:
    k = queue.popleft()
    x = k[0]
    y = k[1]
    type = k[2]
    for d in range(4):
        nx = x + dx[d]
        ny = y + dy[d]
        if 0 <= nx < r and 0 <= ny < c:
            if type == 'water' and forest[nx][ny] != 'D' and forest[nx][ny] != 'X' and forest[nx][ny] != '*':
                forest[nx][ny] = '*'
                queue.append([nx, ny, 'water'])
                # print(1)
                # print(nx, ny)
                # for f in forest:
                #     print(f)
                # for v in visited:
                #     print(v)
            if type == 'hog' and visited[nx][ny] == -1 and forest[nx][ny] != '*' and forest[nx][ny] != 'X':
                visited[nx][ny] = visited[x][y] + 1
                queue.append([nx, ny, 'hog'])
                # print(2)
                # print(nx, ny)
                # for f in forest:
                #     print(f)
                # for v in visited:
                #     print(v)
            
if visited[beaver[0]][beaver[1]] == -1:
    print('KAKTUS')
else:
    print(visited[beaver[0]][beaver[1]])
                