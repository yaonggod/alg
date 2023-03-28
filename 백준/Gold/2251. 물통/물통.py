import sys
input = sys.stdin.readline
a, b, c = list(map(int, input().split()))

# ab ba bc cb ac ca
from collections import deque
queue = deque()
queue.append([0, 0, c])
visited = [[[False] * (c + 1) for _ in range(b + 1)] for _ in range(a + 1)]
ans = set()
# 물 붓기
def water(x, y, wx, wy):
    # y물병에는 y - wy만큼 넣을 수 있음
    if wx > y - wy:
        rx = (wx + wy) - y
        ry = y
    else:
        rx = 0
        ry = wx + wy
    return [rx, ry]

while queue:
    ca, cb, cc = queue.popleft()
    if ca == 0:
        ans.add(cc)
    if not visited[ca][cb][cc]:
        visited[ca][cb][cc] = True
        queue.append([water(a, b, ca, cb)[0], water(a, b, ca, cb)[1], cc])
        queue.append([water(b, a, cb, ca)[1], water(b, a, cb, ca)[0], cc])
        queue.append([water(a, c, ca, cc)[0], cb, water(a, c, ca, cc)[1]])
        queue.append([water(c, a, cc, ca)[1], cb, water(c, a, cc, ca)[0]])
        queue.append([ca, water(b, c, cb, cc)[0], water(b, c, cb, cc)[1]])
        queue.append([ca, water(c, b, cc, cb)[1], water(c, b, cc, cb)[0]])

ans = list(ans)
ans.sort()
for i in ans:
    print(i, end = " ")