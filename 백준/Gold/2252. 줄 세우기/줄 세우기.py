import sys
input = sys.stdin.readline
n, m = map(int, input().split())

graph = [[] for _ in range(n + 1)]
count = [0] * (n + 1)

for _ in range(m):
    a, b = map(int, input().split())
    # a보다 b가 더 크다
    graph[a].append(b)
    # b보다 먼저 오는 사람 하나 증가
    count[b] += 1

from collections import deque
queue = deque()
for i in range(1, n + 1):
    # i보다 먼저 오는 사람이 없으면 제일 앞에 세울 수 있음
    if count[i] == 0:
        queue.append(i)
    
while queue:
    x = queue.popleft()
    print(x, end = " ")
    for i in graph[x]:
        # i보다 우선하는 사람을 이미 한 명(x) 세웠음
        count[i] -= 1
        # 이제 i보다 먼제 세워야 할 사람이 없으므로
        if count[i] == 0:
            # 큐에 넣고 세울 때까지 기다린다
            queue.append(i)
