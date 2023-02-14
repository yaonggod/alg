from collections import deque
from itertools import combinations
from copy import deepcopy
import sys
input = sys.stdin.readline

n, m = map(int, input().split())
lab = [list(map(int, input().split())) for _ in range(n)]
infected_lab = [[0] * n for _ in range(n)]

def findvirus(lab, n):
    lst = []
    for i in range(n):
        for j in range(n):
            if lab[i][j] == 2:
                lst.append([i, j])
    return lst

def infection(lab, lst, n):
    for c in lst:
        lab[c[0]][c[1]] = 1
        
def wall(lab, new_lab, n):
    for i in range(n):
        for j in range(n):
            if lab[i][j] == 1:
                new_lab[i][j] = -1
        
def findzero(lab, n):
    result = False
    for i in range(n):
        for j in range(n):
            if lab[i][j] == 0:
                result = True
                break
    return result

    
def findmax(lab, n):
    max = 0
    for i in range(n):
        for j in range(n):
            if lab[i][j] > max:
                max = lab[i][j]
    return max
        
dx = [-1, 1, 0, 0]      
dy = [0, 0, -1, 1]
min_hour = 9999999

for comb in combinations(findvirus(lab, n), m):
    new_infected_lab = deepcopy(infected_lab)
    wall(lab, new_infected_lab, n)
    infection(new_infected_lab, comb, n)
    queue = deque(list(comb))
    while queue:
        c = queue.popleft()
        x = c[0]
        y = c[1]
        for d in range(4):
            nx = x + dx[d]
            ny = y + dy[d]
            if 0 <= nx < n and 0 <= ny < n and new_infected_lab[nx][ny] == 0:
                new_infected_lab[nx][ny] = new_infected_lab[x][y] + 1
                queue.append([nx, ny])
    # if not findzero(new_infected_lab, n):
    #     print(comb)
    #     print(findmax(new_infected_lab, n) - 1)
    if not findzero(new_infected_lab, n) and min_hour > findmax(new_infected_lab, n) - 1:
        min_hour = findmax(new_infected_lab, n) - 1
if min_hour == 9999999:
    print(-1)
else:   
    print(min_hour)
# comb = list(([0, 2], [3, 0], [6, 0]))
# new_infected_lab = deepcopy(infected_lab)
# wall(lab, new_infected_lab, n)
# infection(new_infected_lab, comb, n)

# queue = deque(comb)
# while queue:
#     c = queue.popleft()
#     x = c[0]
#     y = c[1]
#     for d in range(4):
#         nx = x + dx[d]
#         ny = y + dy[d]
#         if 0 <= nx < n and 0 <= ny < n and new_infected_lab[nx][ny] == 0:
#             new_infected_lab[nx][ny] = new_infected_lab[x][y] + 1
#             queue.append([nx, ny])

    
# pprint(new_infected_lab)
