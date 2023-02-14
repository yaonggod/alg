import sys
input = sys.stdin.readline


n, m = map(int, input().split())
INF = 10000
matrix = [[INF] * n for _ in range(n)]
for _ in range(n):
    matrix[_][_] = 0
for _ in range(m):
    a, b = map(int, input().split())
    matrix[a - 1][b - 1] = 1
    matrix[b - 1][a - 1] = 1
    
for i in range(n):
    for j in range(n):
        for k in range(n):
            matrix[j][k] = min(matrix[j][k], matrix[j][i] + matrix[i][k])    

lst = []
for i in range(n):
    lst.append([i + 1, sum(matrix[i])])
lst.sort(key=lambda x : x[1])
print(lst[0][0])