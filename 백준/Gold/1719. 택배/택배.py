import sys
input = sys.stdin.readline
n, m = map(int, input().split())

INF = int(1e9)
cost = [[INF] * (n + 1) for _ in range(n + 1)]
for i in range(1, n + 1):
    cost[i][i] = 0
route = [['-'] * (n + 1) for _ in range(n + 1)]

for _ in range(m):
    a, b, c = map(int, input().split())
    cost[a][b] = c
    cost[b][a] = c
    route[a][b] = b
    route[b][a] = a

for k in range(1, n + 1):
    for i in range(1, n + 1):
        for j in range(1, n + 1):
        # i -> j & j -> i
            if i != j:
                if cost[i][k] + cost[k][j] < cost[i][j]:
                    cost[i][j] = cost[i][k] + cost[k][j]
                    route[i][j] = route[i][k]

for i in range(1, n + 1):
    for j in range(1, n + 1):
        print(route[i][j], end = ' ')
    print()