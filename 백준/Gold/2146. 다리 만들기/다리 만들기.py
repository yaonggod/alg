import sys
input = sys.stdin.readline

from collections import deque

n = int(input())
map_ = [list(map(int, input().split())) for _ in range(n)]
island = [[0] * n for _ in range(n)]

def matrix_sum(matrix, n):
    matrix_sum = 0
    for i in range(n):
        for j in range(n):
            matrix_sum += matrix[i][j]
    return matrix_sum

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
island_number = 0

# 섬을 찾아서 개수 세고 1, 2, 3 ... 마킹하기, 지도는 0으로 마킹하고 방문 처리
while True:
    if matrix_sum(map_, n) == 0:
        break
    for i in range(n):
        for j in range(n):
            if map_[i][j] == 1:
                island_number += 1
                map_[i][j] = 0
                island[i][j] = island_number
                queue = deque([[i, j]])
                while queue:
                    a = queue.popleft()
                    x = a[0]
                    y = a[1]
                    for d in range(4):
                        nx = x + dx[d]
                        ny = y + dy[d]
                        if 0 <= nx < n and 0 <= ny < n and map_[nx][ny] == 1:
                            queue.append([nx, ny])
                            map_[nx][ny] = 0
                            island[nx][ny] = island_number

# 1 1 1 0 0 0 0 2 2 2 
# 1 1 1 1 0 0 0 0 2 2
# 1 0 1 1 0 0 0 0 2 2
# 0 0 1 1 1 0 0 0 0 2
# 0 0 0 1 0 0 0 0 0 2
# 0 0 0 0 0 0 0 0 0 2
# 0 0 0 0 0 0 0 0 0 0
# 0 0 0 0 3 3 0 0 0 0
# 0 0 0 0 3 3 3 0 0 0
# 0 0 0 0 0 0 0 0 0 0

# 다리 놓기
bridge = []

for a in range(1, island_number):
    for i in range(n):
        for j in range(n):
            if island[i][j] == a:
                # 바다랑 맞닿아 있는지
                lst = []
                for d in range(4):
                    nx = i + dx[d]
                    ny = j + dy[d]
                    if 0 <= nx < n and 0 <= ny < n:
                        lst.append(island[nx][ny])
                if 0 in lst:
                    visited = [[0] * n for _ in range(n)] 
                    queue = deque([[i, j]])
                    while queue:
                        k = queue.popleft()
                        x = k[0]
                        y = k[1]
                        if island[x][y] != 0 and island[x][y] != a:
                            bridge.append(visited[x][y] - 1)
                            break
                        for d in range(4):
                            nx = x + dx[d]
                            ny = y + dy[d]
                            if 0 <= nx < n and 0 <= ny < n and island[nx][ny] != a and not visited[nx][ny]:
                                queue.append([nx, ny])
                                visited[nx][ny] = visited[x][y] + 1
            
print(min(bridge))