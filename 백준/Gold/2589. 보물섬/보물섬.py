from collections import deque
import sys 
input = sys.stdin.readline
n, m = map(int, input().split())
treasure_map = [list(input()) for _ in range(n)]

def max_matrix(matrix, n, m):
    max_matrix = 0
    for i in range(n):
        for j in range(m):
            if matrix[i][j] > max_matrix:
                max_matrix = matrix[i][j]  
    return max_matrix

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
hours = 0
for i in range(n):
    for j in range(m):
        if treasure_map[i][j] == 'L':
            empty_map = [[0] * m for _ in range(n)]
            empty_map[i][j] = 1
            queue = deque([[i, j]])
            while queue:
                k = queue.popleft()
                x = k[0]
                y = k[1]
                for d in range(4):
                    nx = x + dx[d]
                    ny = y + dy[d]
                    if 0 <= nx < n and 0 <= ny < m and treasure_map[nx][ny] == 'L' and empty_map[nx][ny] == 0:
                        queue.append([nx, ny])
                        empty_map[nx][ny] = empty_map[x][y] + 1
            if max_matrix(empty_map, n, m) - 1 > hours:
                hours = max_matrix(empty_map, n, m) - 1

print(hours)