import sys
input = sys.stdin.readline
n = int(input())
work = [[] for _ in range(n + 1)]
time = [0] * (n + 1)
timestamp = [[0, 0] for _ in range(n + 1)]
count = [0] * (n + 1)

for i in range(1, n + 1):
    lst = list(map(int, input().split()))
    time[i] = lst[0]
    if lst[1] > 0:
        for j in lst[2:]:
            if j != i:
                # j를 하고 i를 할 수 있다
                work[j].append(i)
                count[i] += 1

from collections import deque
queue = deque()
for i in range(1, n + 1):
    if count[i] == 0:
        timestamp[i] = [0, time[i]]
        queue.append(i)

while queue:
    x = queue.popleft()
    for i in work[x]:
        count[i] -= 1
        if timestamp[i][0] < timestamp[x][1]:
            timestamp[i] = [timestamp[x][1], timestamp[x][1] + time[i]]
        if count[i] == 0:
            queue.append(i)

maxtime = 0
for i in range(1, n + 1):
    if timestamp[i][1] > maxtime:
        maxtime = timestamp[i][1]
print(maxtime)