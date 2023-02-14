from collections import deque
import sys
input = sys.stdin.readline 

n, m, t = map(int, input().split())
miro = [list(map(int, input().split())) for _ in range(n)]
visited = [[0] * m for _ in range(n)]
visited_sword = [[0] * m for _ in range(n)]
visited[0][0] = 1
visited_sword[0][0] = 1
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

# 일반 visited
queue = deque([[0, 0]])
while queue:
    k = queue.popleft()
    x = k[0]
    y = k[1]
    for d in range(4):
        nx = x + dx[d]
        ny = y + dy[d]
        if 0 <= nx < n and 0 <= ny < m and (miro[nx][ny] == 0 or miro[nx][ny] == 2) and visited[nx][ny] == 0:
            visited[nx][ny] = visited[x][y] + 1
            queue.append([nx, ny])

go_without_sword = visited[n - 1][m - 1] - 1

# 검 찾기
sword_x = -1
sword_y = -1    
queue = deque([[0, 0]])
while queue:
    k = queue.popleft()
    x = k[0]
    y = k[1]
    if miro[x][y] == 2:
        sword_x = x
        sword_y = y
        tosword = visited[x][y] - 1
        break
    for d in range(4):
        nx = x + dx[d]
        ny = y + dy[d]
        if 0 <= nx < n and 0 <= ny < m and (miro[nx][ny] == 0 or miro[nx][ny] == 2) and visited_sword[nx][ny] == 0:
            visited_sword[nx][ny] = visited_sword[x][y] + 1
            queue.append([nx, ny])

# 검 못찾음
if sword_x == sword_y == -1:
    if go_without_sword == -1:
        print('Fail')
    else:
        if go_without_sword > t:
            print('Fail')
        else:
            print(go_without_sword)
# 검 찾음
else:      
    # 검 들고 가기
    queue = deque([[sword_x, sword_y]]) 
    visited_sword = [[0] * m for _ in range(n)]
    visited_sword[sword_x][sword_y] = 1
    while queue:
        k = queue.popleft()
        x = k[0]
        y = k[1]
        for d in range(4):
            nx = x + dx[d]
            ny = y + dy[d]
            if 0 <= nx < n and 0 <= ny < m and (miro[nx][ny] == 0 or miro[nx][ny] == 1) and visited_sword[nx][ny] == 0:
                visited_sword[nx][ny] = visited_sword[x][y] + 1
                queue.append([nx, ny])  
                    
    go_with_sword = tosword + visited_sword[n - 1][m - 1] - 1
    if go_without_sword == -1:
        if go_with_sword > t:
            print('Fail')
        else:
            print(go_with_sword)
    else:
        if min(go_without_sword, go_with_sword) > t:
            print('Fail')
        else:
            print(min(go_without_sword, go_with_sword))