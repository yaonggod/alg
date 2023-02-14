import sys
from collections import deque
input = sys.stdin.readline

n, m = map(int, input().split())
queue = deque()
r, c, d = map(int, input().split())
matrix = []
for _ in range(n):
    matrix.append(list(map(int, input().split())))
# 청소했음
matrix[r][c] = 2
queue.append([r, c, d])

# 북 동 남 서
# 후진 : -2, 만약 음수면 +4
# 반시계 방향으로 회전 : -1씩 해줌, 만약 음수면 +4
# 이미 청소한 칸으로 가는 경우에는 count를 안하고
# 청소하면 청소 처리하고 count += 1

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

count = 1
while queue:
    x, y, di = queue.popleft()
    # 회전 가능한지 4방향 탐색
    for i in range(1, 5):
        if matrix[x + dx[di - i]][y + dy[di - i]] == 0:
            # 청소
            matrix[x + dx[di - i]][y + dy[di - i]] = 2
            count += 1
            # 큐에다 넣기
            if di - i < 0:
                queue.append([x + dx[di - i], y + dy[di - i], di - i + 4])
            else:
                queue.append([x + dx[di - i], y + dy[di - i], di - i])
            break
    # 회전 못함, 후진
    else:
        if matrix[x + dx[di - 2]][y + dy[di - 2]] == 2:
            queue.append([x + dx[di - 2], y + dy[di - 2], di])
print(count)