from collections import deque
import sys

input = sys.stdin.readline
n, k = map(int, input().split())
virus = [list(map(int, input().split())) for _ in range(n)]
s, x, y = map(int, input().split())

def find_virus(virus, n, virus_number):
    lst = []
    for i in range(n):
        for j in range(n):
            if virus[i][j] == virus_number:
                lst.append([i, j])
    return lst

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

# 바이러스 1 ~ k에 해당하는 큐를 만들어서 관리
queue_list = [0]
for v in range(1, k + 1):
    queue_list.append(deque(find_virus(virus, n, v)))

second = 0
while second != s:
    for v in range(1, k + 1):
        if queue_list[v]:
            for _ in range(len(queue_list[v])):
                c = queue_list[v].popleft()
                v_x = c[0]
                v_y = c[1]
                for d in range(4):
                    nx = v_x + dx[d]
                    ny = v_y + dy[d]
                    if 0 <= nx < n and 0 <= ny < n and not virus[nx][ny]:
                        queue_list[v].append([nx, ny])
                        virus[nx][ny] = v   
        else:
            pass
    second += 1

print(virus[x - 1][y - 1])
    