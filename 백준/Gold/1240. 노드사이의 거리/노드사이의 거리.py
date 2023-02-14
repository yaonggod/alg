import sys
input = sys.stdin.readline
from collections import deque

n, m = map(int, input().split())
graph = [[] for _ in range(n + 1)]
for _ in range(n - 1):
    a, b, length = map(int, input().split())
    graph[a].append([b, length])
    graph[b].append([a, length])
for _ in range(m):
    a, b = map(int, input().split())
    visited = [False] * (n + 1)
    visited[a] = True
    queue = deque([[a, 0]])
    while queue:
        k = queue.popleft()
        x = k[0]
        length = k[1]
        if x == b:
            print(length)
        for y in graph[x]:
            if not visited[y[0]]:
                visited[y[0]] = True
                queue.append([y[0], length + y[1]])