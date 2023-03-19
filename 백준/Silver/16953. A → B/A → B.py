import sys
input = sys.stdin.readline
a, b = map(int, input().split())

visited = {}
visited[a] = 1

from collections import deque
queue = deque()
queue.append(a)

while queue:
    x = queue.popleft()
    if x * 2 <= b and x * 2 not in visited:
        queue.append(x * 2)
        visited[x * 2] = visited[x] + 1
    if x * 10 + 1 <= b and x * 10 + 1 not in visited:
        queue.append(x * 10 + 1)
        visited[x * 10 + 1] = visited[x] + 1

if b not in visited:
    print(-1)
else:
    print(visited[b])