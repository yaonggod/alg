import sys
from collections import deque
input = sys.stdin.readline
n, k = map(int, input().split())
visited = [0] * 100001
visited[n] = 1
queue = deque([n])
while True:
    x = queue.popleft()
    if x == k:
        break
    for i in [x + 1, x - 1, x * 2]:
        if 0 <= i <= 100000 and not visited[i]:
            visited[i] = visited[x] + 1
            queue.append(i)
print(visited[k] - 1)
lst = deque([[k]])
while True:
    x = lst[0]
    if n in x:
        break
    l = []
    for c in x:
        for i in [c - 1, c + 1, c // 2]:
            if 0 <= i <= 100000 and i not in l and visited[i] == visited[c] - 1:
                l.append(i)
    lst.appendleft(l)
for i in range(visited[k]):
    print(lst[i][0], end = ' ')