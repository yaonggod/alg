import sys
sys.setrecursionlimit(100000)

v, e = map(int, input().split())
route = [[] for _ in range(v + 1)]
graph = [[0] * (v + 1) for _ in range(v + 1)]
for _ in range(e):
    a, b = map(int, input().split())
    route[a].append(b)
    route[b].append(a)

ans = 'NO'

def dfs(x, i, count):
    global ans
    if count == e:
        ans = 'YES'
    else:
        for y in route[x]:
            if graph[x][y] != i:
                graph[x][y] = i
                graph[y][x] = i
                dfs(y, i, count + 1)

for i in range(1, v + 1):
    dfs(i, i, 0)
    if ans == 'YES':
        break

print(ans)