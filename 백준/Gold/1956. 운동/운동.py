import sys
input = sys.stdin.readline
v, e = map(int, input().split())

INF = int(1e9)
cost = [[INF] * (v + 1) for _ in range(v + 1)]
for i in range(1, v + 1):
    cost[i][i] = 0

for i in range(e):
    a, b, c = map(int, input().split())
    cost[a][b] = c

for k in range(1, v + 1):
    for i in range(1, v + 1):
        for j in range(1, v + 1):
            if i != j:
                if cost[i][j] > cost[i][k] + cost[k][j]:
                    cost[i][j] = cost[i][k] + cost[k][j]

ans = INF
for i in range(1, v + 1):
    for j in range(1, v + 1):
        if i != j:
            if cost[i][j] != INF and cost[j][i] != INF:
                if cost[i][j] + cost[j][i] < ans:
                    ans = cost[i][j] + cost[j][i] 
if ans == INF:
    print(-1)
else:
    print(ans)