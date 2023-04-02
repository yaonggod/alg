import sys
input = sys.stdin.readline
n, m = map(int, input().split())

count = [[0] * (n + 1) for _ in range(n + 1)]

for i in range(m):
    a, b = map(int, input().split())
    # a보다 b가 크다
    count[a][b] = 1

for k in range(1, n + 1):
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            if i != j:
                # i보다 k가 크고 k보다 j가 크면 i보다 j가 큰게 맞음
                if count[i][k] == 1 and count[k][j] == 1:
                    count[i][j] = 1

c = 0
for i in range(1, n + 1):
    s = 0
    for j in range(1, n + 1):
        # i보다 큰 사람
        s += count[i][j]
        # i보다 작은 사람
        s += count[j][i]
    # 정확히 자기 자신을 제외한 사람 수와 같음
    if s == n - 1:
        c += 1
    
print(c)
