import sys
from collections import deque
from copy import deepcopy
input = sys.stdin.readline

n = int(input())
land = [list(map(int, input().split())) for _ in range(n)]

def height(n, land):
    min_height = 101
    max_height = 0
    for i in range(n):
        for j in range(n):
            if land[i][j] < min_height:
                min_height = land[i][j]
            if land[i][j] > max_height:
                max_height = land[i][j]
    return min_height, max_height

def rain(n, land, a):
    for i in range(n):
        for j in range(n):
            if land[i][j] <= a:
                land[i][j] = 0
                
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
area_list = []
for a in range(height(n, land)[0] - 1, height(n, land)[1] + 1):
    new_land = deepcopy(land)
    rain(n, new_land, a)
    area = 0
    for i in range(n):
        for j in range(n):
            if new_land[i][j]:
                queue = deque([[i, j]])
                while queue:
                    k = queue.popleft()
                    x = k[0]
                    y = k[1]
                    for d in range(4):
                        nx = x + dx[d]
                        ny = y + dy[d]
                        if 0 <= nx < n and 0 <= ny < n and new_land[nx][ny]:
                            new_land[nx][ny] = 0
                            queue.append([nx, ny])
                area += 1
    area_list.append(area)

print(max(area_list))