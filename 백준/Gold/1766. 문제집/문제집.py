import sys
input = sys.stdin.readline
n, m = map(int, input().split())

count = [0] * (n + 1)
graph = [[] for _ in range(n + 1)]

from heapq import heappop, heappush
pq = []
for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    count[b] += 1

for i in range(1, n + 1):
    if count[i] == 0:
        heappush(pq, i)

while pq:
    x = heappop(pq)
    print(x, end = ' ')
    for i in graph[x]:
        count[i] -= 1
        if count[i] == 0:
            heappush(pq, i)