import sys
n, m = map(int, input().split())

visited = [False] * n
friends = [[] for _ in range(n)]

for _ in range(m):
    a, b = map(int, input().split())
    friends[a].append(b)
    friends[b].append(a)

exist = 0

def backtracking(f, cnt):
    global exist
    if cnt == 5:
        exist = 1
        return
    for g in friends[f]:
        if not visited[g]:
            visited[g] = True
            backtracking(g, cnt + 1)
            visited[g] = False

for i in range(n):
    backtracking(i, 0)
    if exist == 1:
        break

print(exist)
