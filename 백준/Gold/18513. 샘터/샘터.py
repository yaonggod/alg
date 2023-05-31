import sys
n, k = map(int, input().split())
water = list(map(int, input().split()))

from collections import deque
visited = set()
queue = deque()
for i in water:
    queue.append((i, 0))
    visited.add(i)

count = 0
len = 0

while True:
    x = queue.popleft()
    if x[1] != 0:
        count += 1
    len += x[1]
    if count == k:
        break 
    for i in [-1, 1]:
        if x[0] + i not in visited:
            visited.add(x[0] + i)
            queue.append((x[0] + i, x[1] + 1))

print(len)