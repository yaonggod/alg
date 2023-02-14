import sys
input = sys.stdin.readline
from collections import deque

n, m = map(int, input().split())
village = [list(map(int, input().split())) for _ in range(n)]
visited = [[False] * m for _ in range(n)]

queue1 = deque()
queue2 = deque()
for i in range(n):
    for j in range(m):
        if village[i][j] == 1:
            queue1.append((i, j))
            visited[i][j] = True
for i in range(n):
    for j in range(m):
        if village[i][j] == 2:
            queue2.append((i, j))
            visited[i][j] = True

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
while queue1 or queue2:
    set1 = set()
    set2 = set()
    while queue1:
        x, y = queue1.popleft()
        for d in range(4):
            nx, ny = x + dx[d], y + dy[d]
            if 0 <= nx < n and 0 <= ny < m and village[nx][ny] == 0:
                set1.add((nx, ny))
    while queue2:
        x, y = queue2.popleft()
        for d in range(4):
            nx, ny = x + dx[d], y + dy[d]
            if 0 <= nx < n and 0 <= ny < m and village[nx][ny] == 0:
                set2.add((nx, ny))
                
    set3 = set1 & set2
    for x, y in set3:
        village[x][y] = 3
        
    for x, y in set1 - set3:
        village[x][y] = 1
        queue1.append((x, y))
    
    for x, y in set2 - set3:
        village[x][y] = 2
        queue2.append((x, y))

v1 = 0
v2 = 0
v3 = 0
for i in range(n):
    for j in range(m):
        if village[i][j] == 1:
            v1 += 1
        elif village[i][j] == 2:
            v2 += 1
        elif village[i][j] == 3:
            v3 += 1
print(v1, v2, v3)