import sys
input = sys.stdin.readline
from collections import deque
m, n, p = map(int, input().split())
board = [list(input()) for _ in range(m)]
player_dict = {}
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
for _ in range(p):
    player, dps = input().split()
    player_dict[player] = [int(dps)]
    for i in range(m):
        for j in range(n):
            if player == board[i][j]:
                queue = deque([[i, j]])
                visited = [[-1] * n for _ in range(m)]
                visited[i][j] = 0
                while queue:
                    x, y = queue.popleft()
                    if board[x][y] == 'B':
                        player_dict[player].append(visited[x][y])
                        break
                    else:
                        for d in range(4):
                            nx, ny = x + dx[d], y + dy[d]
                            if 0 <= nx < m and 0 <= ny < n and board[nx][ny] != 'X' and visited[nx][ny] == -1:
                                visited[nx][ny] = visited[x][y] + 1
                                queue.append([nx, ny])
                                        
hp = int(input())
dict = {}
i = 0
while True:
    dict[i] = 0
    count = 0
    for p in player_dict:
        if player_dict[p][1] <= i:
            dict[i] += player_dict[p][0]
            count += 1
    if sum(dict.values()) >= hp:
        break
    i += 1
print(count)    