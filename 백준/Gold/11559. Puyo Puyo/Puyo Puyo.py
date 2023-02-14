import sys
input = sys.stdin.readline
from collections import deque

pp = [list(input().strip()) for _ in range(12)]
p = ['R', 'G', 'B', 'P', 'Y']
c = 0

def down(pp):
    for i in range(10, -1, -1):
        for j in range(6):
            if pp[i + 1][j] == '.':
                t = 1
                while True:
                    if i + t > 11:
                        break
                    elif pp[i + t][j] in p:
                        break
                    else:
                        pp[i + t][j] = pp[i + t - 1][j]
                        pp[i + t - 1][j] = '.'
                    t += 1
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
                       
while True:
    count = 0
    for i in range(12):
        for j in range(6):
            if pp[i][j] in p:
                visited = [[False] * 6 for _ in range(12)]
                visited[i][j] = True
                lst = [[i, j]]
                queue = deque(lst)
                while queue:
                    k = queue.popleft()
                    x = k[0]
                    y = k[1]
                    for d in range(4):
                        nx = x + dx[d]
                        ny = y + dy[d]
                        if 0 <= nx < 12 and 0 <= ny < 6 and not visited[nx][ny] and pp[nx][ny] == pp[i][j]:
                            visited[nx][ny] = True
                            queue.append([nx, ny])
                            lst.append([nx, ny])
                if len(lst) >= 4:
                    count += 1
                    for l in lst:
                        pp[l[0]][l[1]] = '.'
    down(pp)
    if count > 0:
        c += 1
    else:
        break
    
print(c)