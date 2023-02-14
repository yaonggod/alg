import sys
from collections import deque

t = int(sys.stdin.readline())
for _ in range(t):
    n, m = map(int, sys.stdin.readline().split())
    lst = list(map(int, sys.stdin.readline().split()))
    queue = deque()
    for i in range(n):
        queue.append([i, lst[i]])
    count = 0
    while True:
        x = queue.popleft()
        max_importance = 0
        for i in range(len(queue)):
            if queue[i][1] > max_importance:
                max_importance = queue[i][1]
        if x[1] < max_importance:
            queue.append(x)
        else:
            count += 1
            if x[0] == m:
                break
        # print(queue)
    print(count)