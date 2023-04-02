import sys
input = sys.stdin.readline
n, m = map(int, input().split())

count = [[0] * (n + 1) for _ in range(n + 1)]

for i in range(m):
    a, b = map(int, input().split())
    count[a][b] = 1

for k in range(1, n + 1):
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            if i != j:
                if count[i][k] == 1 and count[k][j] == 1:
                    count[i][j] = 1

c = 0
for i in range(1, n + 1):
    s = 0
    for j in range(1, n + 1):
        s += count[i][j]
        s += count[j][i]
    if s == n - 1:
        c += 1
    
print(c)