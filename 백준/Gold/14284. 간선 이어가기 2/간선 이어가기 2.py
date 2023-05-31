import sys
sys.setrecursionlimit(100000)
n, m = map(int, input().split())
graph = [[] for _ in range(n + 1)]
for _ in range(m):
    a, b, c = map(int, input().split())
    graph[a].append((c, b))
    graph[b].append((c, a))
s, t = map(int, input().split())

INF = int(1e9)
route = [INF] * (n + 1)
route[s] = 0

from heapq import heappop, heappush
pq = []
heappush(pq, (0, s))

while pq:
    x = heappop(pq)
    if x[0] <= route[x[1]]:
        for y in graph[x[1]]:
            if (route[y[1]] > route[x[1]] + y[0]):
                route[y[1]] = route[x[1]] + y[0]
                heappush(pq, (route[y[1]], y[1]))

print(route[t])