import sys
from collections import deque
input = sys.stdin.readline

t = int(input())
for _ in range(t):
    l = int(input())
    x0, y0 = map(int, input().split())
    x1, y1 = map(int, input().split())
    
    chess = [[-1] * l for _ in range(l)]
    chess[x0][y0] = 0
    
    dx = [-2, -2, -1, 1, 2, 2, 1, -1]
    dy = [-1, 1, 2, 2, 1, -1, -2, -2]
    
    queue = deque([[x0, y0]])
    while True:
        k = queue.popleft()
        x = k[0]
        y = k[1]
        if k == [x1, y1]:
            print(chess[x1][y1])
            break
        for d in range(8):
            nx = x + dx[d]
            ny = y + dy[d]
            if 0 <= nx < l and 0 <= ny < l and chess[nx][ny] == -1:
                chess[nx][ny] = chess[x][y] + 1
                queue.append([nx, ny])
                