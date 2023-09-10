n = int(input())
matrix = []
for _ in range(n):
    matrix.append(list(map(int, input().split())))

result = [[0] * n for _ in range(n)]
result[0][0] = 1

for i in range(n):
    for j in range(n):
        step = matrix[i][j]
        if step != 0:
            if i + step < n:
                result[i + step][j] += result[i][j]
            if j + step < n:
                result[i][j + step] += result[i][j]

print(result[n - 1][n - 1])
