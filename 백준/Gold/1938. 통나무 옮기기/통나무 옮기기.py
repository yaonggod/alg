import sys
input = sys.stdin.readline
from collections import deque

n = int(input())
land = [list(input().strip()) for _ in range(n)]

xy = []
for i in range(n):
    for j in range(n):
        xy.append((i, j))
        
visited = []
    
b = []
for i in range(n):
    for j in range(n):
        if land[i][j] == 'B':
            b.append(xy.index((i, j)))
b.append(0)
visited.append(b[:3])
            
e = []
for i in range(n):
    for j in range(n):
        if land[i][j] == 'E':
            e.append(xy.index((i, j)))
            
queue = deque()
queue.append(b)
result = 0
while queue:
    x = queue.popleft()
    if x[:3] == e:
        result = x[-1]
        break
    
    # up
    x_u = []
    for elem in x[:3]:
        if 0 <= elem - n < n ** 2:
            if land[xy[elem - n][0]][xy[elem - n][1]] != '1':
                x_u.append(elem - n)
    if len(x_u) == 3:
        x_u.append(x[-1] + 1)
        # print(x_u)
        if x_u[:3] not in visited:
            visited.append(x_u[:3])
            queue.append(x_u)
        
    # down
    x_d = []
    for elem in x[:3]:
        if 0 <= elem + n < n ** 2:
            if land[xy[elem + n][0]][xy[elem + n][1]] != '1':
                x_d.append(elem + n)
    if len(x_d) == 3:
        x_d.append(x[-1] + 1)
        # print(x_d)
        if x_d[:3] not in visited:
            visited.append(x_d[:3])
            queue.append(x_d)
        
    # left
    x_l = []
    x0 = x[0] - 1
    x1 = x[1] - 1
    x2 = x[2] - 1
    # 가로
    if x[1] - x[0] == x[2] - x[1] == 1:
        if 0 <= x0 < n ** 2 and x0 // n == x1 // n == x2 // n and land[xy[x0][0]][xy[x0][1]] != '1' and land[xy[x1][0]][xy[x1][1]] != '1' and land[xy[x2][0]][xy[x2][1]] != '1':
            x_l.append(x0)
            x_l.append(x1)
            x_l.append(x2)
            x_l.append(x[3] + 1)
            # print(x_l)
            if x_l[:3] not in visited:
                visited.append(x_l[:3])
                queue.append(x_l)
    
    elif x[1] - x[0] == x[2] - x[1] == n:
        if 0 <= x0 < n ** 2 and x0 // n == x[0] // n and land[xy[x0][0]][xy[x0][1]] != '1' and land[xy[x1][0]][xy[x1][1]] != '1' and land[xy[x2][0]][xy[x2][1]] != '1':
            x_l.append(x0)
            x_l.append(x1)
            x_l.append(x2)
            x_l.append(x[3] + 1)
            # print(x_l)
            if x_l[:3] not in visited:
                visited.append(x_l[:3])
                queue.append(x_l)
    
    # right
    x_r = []
    x0 = x[0] + 1
    x1 = x[1] + 1
    x2 = x[2] + 1
    # 가로
    if x[1] - x[0] == x[2] - x[1] == 1:
        if 0 <= x0 < n ** 2 and x0 // n == x1 // n == x2 // n and land[xy[x0][0]][xy[x0][1]] != '1' and land[xy[x1][0]][xy[x1][1]] != '1' and land[xy[x2][0]][xy[x2][1]] != '1':
            x_r.append(x0)
            x_r.append(x1)
            x_r.append(x2)
            x_r.append(x[3] + 1)
            # print(x_r)
            if x_r[:3] not in visited:
                visited.append(x_r[:3])
                queue.append(x_r)
    
    elif x[1] - x[0] == x[2] - x[1] == n:
        if 0 <= x0 < n ** 2 and x0 // n == x[0] // n and land[xy[x0][0]][xy[x0][1]] != '1' and land[xy[x1][0]][xy[x1][1]] != '1' and land[xy[x2][0]][xy[x2][1]] != '1':
            x_r.append(x0)
            x_r.append(x1)
            x_r.append(x2)
            x_r.append(x[3] + 1)
            # print(x_r)
            if x_r[:3] not in visited:
                visited.append(x_r[:3])
                queue.append(x_r)
        
    # turn
    x_t = []
    # 가로
    if x[1] - x[0] == x[2] - x[1] == 1:
        x0, x1, x2 = x[0] - n + 1, x[1], x[2] + n - 1
        x3 = x0 - 1
        x4 = x2 - 1
        x5 = x0 + 1
        x6 = x2 + 1
        # print(x[0], x[1], x[2], x0, x1, x2, x3, x4, x5, x6)
        if 0 <= x0 < n ** 2 and 0 <= x2 < n ** 2 and x0 // n != x[0] // n and x2 // n != x[2] // n and land[xy[x0][0]][xy[x0][1]] != '1' and land[xy[x2][0]][xy[x2][1]] != '1':
            # print(True)
            if land[xy[x3][0]][xy[x3][1]] != '1' and land[xy[x4][0]][xy[x4][1]] != '1' and land[xy[x5][0]][xy[x5][1]] != '1' and land[xy[x6][0]][xy[x6][1]] != '1':
                x_t.append(x0)
                x_t.append(x1)
                x_t.append(x2)
                x_t.append(x[3] + 1)
                # print(x_t)
                if x_t[:3] not in visited:
                    visited.append(x_t[:3])
                    queue.append(x_t)
    # 세로
    elif x[1] - x[0] == x[2] - x[1] == n:
        x0, x1, x2 = x[0] + n - 1, x[1], x[2] - n + 1
        x3 = x0 - n
        x4 = x0 + n
        x5 = x2 - n
        x6 = x2 + n
        # print(x[0], x[1], x[2], x0, x1, x2, x3, x4, x5, x6)
        if 0 <= x0 < n ** 2 and 0 <= x2 < n ** 2 and x0 // n != x[0] // n and x2 // n != x[2] // n and land[xy[x0][0]][xy[x0][1]] != '1' and land[xy[x2][0]][xy[x2][1]] != '1':
            # print(True)
            if land[xy[x3][0]][xy[x3][1]] != '1' and land[xy[x4][0]][xy[x4][1]] != '1' and land[xy[x5][0]][xy[x5][1]] != '1' and land[xy[x6][0]][xy[x6][1]] != '1':
                x_t.append(x0)
                x_t.append(x1)
                x_t.append(x2)
                x_t.append(x[3] + 1)
                # print(x_t)
                if x_t[:3] not in visited:
                    visited.append(x_t[:3])
                    queue.append(x_t)
                
print(result)