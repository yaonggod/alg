import imghdr
import sys
sys.setrecursionlimit(100000)
n = int(input())
INF = int(1e9)
city = [[] for _ in range(n + 1)]
for _ in range(n - 1):
    u, v = map(int, input().split())
    city[u].append(v)
    city[v].append(u)

def dfs(x, l):
    global maxlen
    global maxnode
    global visited
    for y in city[x]:
        if not visited[y]:
            visited[y] = True
            dfs(y, l + 1)
    else:
        if l > maxlen:
            maxlen = l
            maxnode = x

minlen = INF
maxlen = 0
maxnode = 0
visited = [False] * (n + 1)
visited[1] = True
dfs(1, 0)
visited = [False] * (n + 1)
visited[maxnode] = True
dfs(maxnode, 0)
if maxlen < minlen:
    minlen = maxlen
print((minlen + 1) // 2)