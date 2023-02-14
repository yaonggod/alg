import sys
from collections import deque
input = sys.stdin.readline

h, w, n = map(int, input().split())
cheese = [list(input().strip()) for _ in range(h)]

def findposition(h, w, x : str):
    for i in range(h):
        for j in range(w):
            if cheese[i][j] == x:
                return [i, j]
            
start_x = findposition(h, w, 'S')[0]
start_y = findposition(h, w, 'S')[1]
moves = 0
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def bfs(x, y, target : str):
    visited = [[-1] * w for _ in range(h)]
    visited[x][y] = 0
    queue = deque([[x, y]])
    while True:
        k = queue.popleft()
        x = k[0]
        y = k[1]
        if cheese[x][y] == target:
            return x, y, visited[x][y]
        for d in range(4):
            nx = x + dx[d]
            ny = y + dy[d]
            if 0 <= nx < h and 0 <= ny < w and cheese[nx][ny] != 'X' and visited[nx][ny] == -1:
                visited[nx][ny] = visited[x][y] + 1
                queue.append([nx, ny])
                
x, y, move = bfs(start_x, start_y, '1')
moves += move

for i in range(1, n):
    x, y, move = bfs(x, y, str(i + 1))
    moves += move

print(moves)