import sys
from collections import deque
input = sys.stdin.readline

m, n, h = map(int, input().split())
boxes = []
for _ in range(h):
    box = []
    for _ in range(n):
        box.append(list(map(int, input().split())))
    boxes.append(box)

# 6방향 탐색
dh = [-1, 1, 0, 0, 0, 0]
dx = [0, 0, -1, 1, 0, 0]
dy = [0, 0, 0, 0, -1, 1]
day = -1
# 새로 익은 토마토를 카운팅해서 새로 익은 토마토가 없을때까지 돌려야되는데
# 일단 새로 익은 토마토를 바로 바꾸지 않고 탐색이 다 끝나면 그때 바꾸기
# 모든 과정이 끝나면 다음 날로 넘어감 day += 1

# 초기 토마토 찾기
queue = deque()
for a in range(h):
    for b in range(n):
        for c in range(m):
            if boxes[a][b][c] == 1:
                queue.append([a, b, c])

while True:
    for _ in range(len(queue)):
        a, b, c = queue.popleft()
        # 다음에 전염시킬 토마토 찾기
        for d in range(6):
            nh, nx, ny = a + dh[d], b + dx[d], c + dy[d]
            if 0 <= nh < h and 0 <= nx < n and 0 <= ny < m and boxes[nh][nx][ny] == 0:
                boxes[nh][nx][ny] = 1
                queue.append([nh, nx, ny])
    # 하루 지남
    day += 1
    if not queue:
        break

for a in range(h):
    for b in range(n):
        for c in range(m):
            # 안익은 토마토 찾음
            if boxes[a][b][c] == 0:
                day = -1
                break
print(day)
