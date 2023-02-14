from copy import deepcopy
import sys
input = sys.stdin.readline
from itertools import combinations
from collections import deque

n, m = map(int, input().split())
lab = [list(map(int, input().split())) for _ in range(n)]

# 바이러스 위치 찾기
virus = []
for i in range(n):
    for j in range(n):
        if lab[i][j] == 2:
            virus.append([i, j])

# 랩에서 바이러스가 퍼지는 데 걸리는 시간 구하기
def time(visited):
    lst = []
    for i in range(n):
        for j in range(n):
            if lab[i][j] == 1:
                pass
            elif [i, j] in deactivate:
                pass
            else:
                lst.append(visited[i][j])          
    if -1 in lst:
        return -1
    else:
        return max(lst)
    
def bfs(lst):
    queue = deque(lst)
    visited = [[-1] * n for _ in range(n)]
    for virus in lst:
        visited[virus[0]][virus[1]] = 0
    while queue:
        x, y = queue.popleft()
        for d in range(4):
            nx = x + dx[d]
            ny = y + dy[d]
            if 0 <= nx < n and 0 <= ny < n and lab[nx][ny] == 0 and visited[nx][ny] == -1:
                visited[nx][ny] = visited[x][y] + 1
                queue.append([nx, ny])
            if 0 <= nx < n and 0 <= ny < n and lab[nx][ny] == 2 and visited[nx][ny] == -1:
                visited[nx][ny] = visited[x][y] + 1
                queue.append([nx, ny])
    # from pprint import pprint
    # pprint(visited)
    answer_list.append(time(visited))
                                 
# 바이러스 조합을 뽑아서 배치하기, bfs
answer_list = []
virus_list = list(combinations(virus, m))
# print(virus_list)
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
for comb in virus_list:
    # 비활성화 바이러스
    deactivate = []
    for v in virus:
        if v not in comb:
            deactivate.append([v[0], v[1]])
    bfs(list(comb))
# print(sorted(answer_list))

min_value = 10000000
for ans in answer_list:
    if ans > -1 and ans < min_value:
        min_value = ans
if min_value == 10000000:
    print(-1)
else:
    print(min_value)