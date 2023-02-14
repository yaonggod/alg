import sys
input = sys.stdin.readline
INF = int(1e9)
t = int(input())
for _ in range(t):
    n, m = map(int, input().split())
    route = [[INF] * (n + 1) for _ in range(n + 1)]
    for _ in range(n + 1):
        route[_][_] = 0
    for _ in range(m):
        a, b, c = map(int, input().split())
        route[a][b] = c
        route[b][a] = c
    k = int(input())
    current = list(map(int, input().split()))
    
    for k in range(1, n + 1):
        for i in range(1, n + 1):
            for j in range(1, n + 1):
                route[i][j] = min(route[i][j], route[i][k] + route[k][j])
    
    min_length = INF
    room = 0
    for i in range(n, 0, -1):
        length = 0
        for c in current:
            length += route[c][i]
        if length <= min_length:
            min_length = length
            room = i

    print(room)