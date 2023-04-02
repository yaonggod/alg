import sys
input = sys.stdin.readline
n, m = map(int, input().split())

graph = [[] for _ in range(n + 1)]
count = [0] * (n + 1)

for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    count[b] += 1

from collections import deque
queue = deque()
for i in range(1, n + 1):
    if count[i] == 0:
        queue.append(i)
    
while queue:
    x = queue.popleft()
    print(x, end = " ")
    for i in graph[x]:
        count[i] -= 1
        if count[i] == 0:
            queue.append(i)