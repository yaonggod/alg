import sys
input = sys.stdin.readline
from collections import deque

k = int(input())
for _ in range(k):
    n, e = map(int, input().split())
    graph = [[] for _ in range(n + 1)]
    for _ in range(e):
        u, v = map(int, input().split())
        graph[u].append(v)
        graph[v].append(u)

    color = [0] * (n + 1)
    color[1] = 1
    result = 'YES'
    for j in range(1, n + 1):
        queue = deque([j])
        while queue:
            k = queue.popleft()
            for i in graph[k]:
                if color[i] == 0:
                    queue.append(i)
                    if color[k] == 0:
                        color[k] = 1
                        color[i] = 2
                    elif color[k] == 1:
                        color[i] = 2
                    elif color[k] == 2:
                        color[i] = 1
                elif color[i] == 1:
                    if color[k] == 1:
                        result = 'NO'
                elif color[i] == 2:
                    if color[k] == 2:
                        result = 'NO'
    print(result)