import sys
input = sys.stdin.readline
n = int(input())

build = [[] for _ in range(n + 1)]
time = [0]
count = [0] * (n + 1)
for i in range(1, n + 1):
    lst = list(map(int, input().split()))
    time.append(lst[0])
    for j in range(1, len(lst) - 1):
        # 이거보다 먼저 지어야 한다 == 이거 다음에 이거를 지을 수 있다
        build[lst[j]].append(i)
    count[i] = len(lst[1:-1])

timestamp = [[0, 0] for _ in range(n + 1)]
from collections import deque
queue = deque()
for i in range(1, n + 1):
    if count[i] == 0:
        queue.append(i)
        timestamp[i] = [0, time[i]]

while queue:
    x = queue.popleft()
    for i in build[x]:
        count[i] -= 1
        if timestamp[i][0] < timestamp[x][1]:
            timestamp[i] = [timestamp[x][1], timestamp[x][1] + time[i]]
        if count[i] == 0:
            queue.append(i)

for i in range(1, n + 1):
    print(timestamp[i][1])