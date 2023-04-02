import sys
input = sys.stdin.readline
n, m = map(int, input().split())

graph = [set() for _ in range(n + 1)]
count = [0] * (n + 1)
said = set()
for _ in range(m):
    lst = list(map(int, input().split()))
    if len(lst) == 2:
        said.add(lst[1])
    else:
        for i in range(1, len(lst) - 1):
            a = lst[i]
            b = lst[i + 1]
            said.add(a)
            said.add(b)
            if b not in graph[a]:
                graph[a].add(b)
                count[b] += 1

result = []
visited = [False] * (n + 1)
from collections import deque
queue = deque()
for i in said:
    if count[i] == 0:
        visited[i] = True
        queue.append(i)

while queue:
    x = queue.popleft()
    result.append(x)
    for i in graph[x]:
        count[i] -= 1
        if count[i] == 0:
            visited[i] = True
            queue.append(i)

if len(result) != len(said):
    print(0)
else:
    for i in result:
        print(i)
    for i in range(1, n + 1):
        if not visited[i]:
            print(i)
