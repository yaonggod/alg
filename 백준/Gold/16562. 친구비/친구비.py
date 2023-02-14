import sys
from collections import deque
# sys.stdin = open('친구비.txt')

input = sys.stdin.readline

n, m, k = map(int, input().split())
k1 = k
a = list(map(int, input().split()))
friends = [[i, a[i - 1]] for i in range(n + 1)]
for _ in range(m):
    v, w = map(int, input().split())
    if v not in friends[w]:
        friends[w].append(v)
    if w not in friends[v]:
        friends[v].append(w)
friends.sort(key = lambda x : x[1])
visited = [False] * (n + 1)
queue = deque()
while True:
    if visited == [False] + [True] * n:
        break
    for i in range(n + 1):
        if friends[i][0] and not visited[friends[i][0]] and k1 - friends[i][1] >= 0:
            visited[friends[i][0]] = True
            queue.append(friends[i][0])
            k1 -= friends[i][1]
            while queue:
                x = queue.popleft()
                for y in friends:
                    if y[0] == x:
                        for f in y[2:]:
                            if not visited[f]:
                                visited[f] = True
                                queue.append(f) 
    else:
        break
if visited == [False] + [True] * n:
    print(k - k1)
else:
    print('Oh no')  