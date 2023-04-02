import sys
input = sys.stdin.readline
INF = int(1e9)

n = int(input())
city = [[INF] * n for _ in range(n)]
# 같은 곳으로 가는 좌표는 0으로 초기화
for _ in range(n):
    city[_][_] = 0
m = int(input())
# 단방향 버스 정보 업데이트, 같은 노선인데 다른 비용인 경우가 있음
for _ in range(m):
    a, b, c = map(int, input().split())
    city[a - 1][b - 1] = min(c, city[a - 1][b - 1])

for i in range(n):
    for j in range(n):
        for k in range(n):
            city[j][k] = min(city[j][k], city[j][i] + city[i][k])

for i in range(n):
    for j in range(n):
        if city[i][j] == INF:
            city[i][j] = 0

for i in range(n):
    for j in range(n):
        print(city[i][j], end = ' ')
    print()
            
    
