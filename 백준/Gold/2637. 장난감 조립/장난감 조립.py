import sys
input = sys.stdin.readline
n = int(input())
part = [[] for _ in range(n + 1)]
count = [0] * (n + 1)
m = int(input())
for _ in range(m):
    x, y, k = map(int, input().split())
    # x를 만드는 데 k개의 y가 필요하다
    part[y].append([x, k])
    count[x] += 1

from collections import deque
queue = deque()
basic = set()
for i in range(1, n + 1):
    if count[i] == 0:
        basic.add(i)
        queue.append(i)

# i를 만드는 데 j를 몇 개 썼다
total = [[0] * (n + 1) for _ in range(n + 1)]
while queue:
    x = queue.popleft()
    # x로 i를 만들 수 있음
    for i in part[x]:
        p = i[0]
        c = i[1]
        # x가 기본이면 그냥 더해주기
        if x in basic:
            total[p][x] = c
        else:
            # x를 만들 때 썼던 기본 부품을 가져와서 곱해주기
            for b in basic:
                total[p][b] += total[x][b] * c
        count[p] -= 1
        if count[p] == 0:
            queue.append(p)

for b in basic:
    print(b, total[n][b])