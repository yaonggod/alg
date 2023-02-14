from collections import deque
h, w = map(int, input().split())
cheese = [list(map(int, input().split())) for _ in range(h)]
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

# 바깥 영역 구하기
def outside(cheese):
    outside = [[0, 0]]
    queue = deque([[0, 0]])
    visited = [[False] * w for _ in range(h)]
    visited[0][0] = True
    while queue:
        x, y = queue.popleft()
        for d in range(4):
            nx = x + dx[d]
            ny = y + dy[d]
            if 0 <= nx < h and 0 <= ny < w and cheese[nx][ny] == 0 and not visited[nx][ny]:
                visited[nx][ny] = True
                queue.append([nx, ny])
                outside.append([nx, ny])
    return outside

# 바깥 영역이랑 맞닿은 치즈 구하기
t = 0
c = 0
while True:
    if len(outside(cheese)) == w * h:
        break
    else:
        o = outside(cheese)
        temp = []
        ch = 0
        for i in range(h):
            for j in range(w):
                if cheese[i][j] == 1:
                    for d in range(4):
                        nx = i + dx[d]
                        ny = j + dy[d]
                        # 외부랑 접촉함
                        if [nx, ny] in o:
                            ch += 1
                            cheese[i][j] = 0
                            break
        t += 1
        c = ch
print(t)
print(c)   