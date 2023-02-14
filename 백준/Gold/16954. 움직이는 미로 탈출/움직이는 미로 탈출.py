import sys
input = sys.stdin.readline
from collections import deque

chess = [list(input()) for _ in range(8)]
start = [[7, 0]]     
          
dx = [-1, 1, 0, 0, -1, -1, 1, 1, 0]
dy = [0, 0, -1, 1, -1, 1, 1, -1, 0]
queue = deque(start)
answer = 0

while queue:
    visited = [[False] * 8 for _ in range(8)] 
    for _ in range(len(queue)):
        x, y = queue.popleft()
        if x == 0 and y == 7:
            answer = 1
            break
        else:
            if chess[x][y] != '#':
                for d in range(9):
                    nx = x + dx[d]
                    ny = y + dy[d]
                    if 0 <= nx < 8 and 0 <= ny < 8 and chess[nx][ny] != '#' and visited[nx][ny] == False:
                        # print(nx, ny)
                        visited[nx][ny] = True
                        queue.append([nx, ny])
    if answer == 1:
        break
        
    chess.pop()
    chess.insert(0, ['.', '.', '.', '.', '.', '.', '.', '.'])
   
print(answer)