import sys
from collections import deque
input = sys.stdin.readline

n, m = map(int, input().split())
ice = []
for _ in range(n):
    ice.append(list(map(int, input().split())))

dx = [-1, 1, 0, 0]
dy = [0, 0, 1, -1]
day = 0
while True:
    # 다음 차수에 들어갈 빙산
    next_ice = [[0] * m for _ in range(n)]
    visited = [[False] * m for _ in range(n)]
    count = 0 
    for i in range(n):
        for j in range(m):
            # 첫 빙산 찾음
            if ice[i][j] > 0 and not visited[i][j]:
                queue = deque()
                queue.append([i, j])
                visited[i][j] = True
                # 큐를 다 돌았다 == 빙산 하나가 완성됐다
                while queue:
                    x, y = queue.popleft()
                    c = 0
                    for d in range(4):
                        nx = x + dx[d]
                        ny = y + dy[d]
                        # 바다다
                        if ice[nx][ny] == 0:
                            c += 1
                        # 방문한 적이 없는 연결된 빙산이다
                        elif ice[nx][ny] > 0 and not visited[nx][ny]:
                            visited[nx][ny] = True
                            queue.append([nx, ny])
                    next_ice[x][y] = max(ice[x][y] - c, 0)
                count += 1
    # 빙산이 두 개 이상으로 갈라짐
    if count >= 2:
        break
    # 빙산이 두 개 이상으로 간 적이 없는데 다 녹음
    elif count == 0:
        day = 0
        break
    # 하루 더해주고 다음날 빙산을 오늘 빙산으로 바꿈
    ice = next_ice
    day += 1
print(day)