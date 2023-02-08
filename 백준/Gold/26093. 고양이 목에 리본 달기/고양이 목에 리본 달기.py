import sys
input = sys.stdin.readline
n, k = map(int, input().split())
cat = []
for _ in range(n):
    cat.append(list(map(int, input().split())))

# k = 5
# lst = [10, 20, 50, 40, 30]

# index를 기준으로 앞, 뒤의 최대값
# 0번 인덱스는 맨 앞이므로 비워둠, 1번 인덱스부터 탐색 시작
max_front = [0] * k
# 제일 마지막 인덱스를 비워두고 뒤에서 두 번째 인덱스부터 탐색 시작
max_back = [0] * k

for i in range(1, k):
    max_front[i] = max(cat[0][i - 1], max_front[i - 1])
for i in range(k - 2, -1, -1):
    max_back[i] = max(cat[0][i + 1], max_back[i + 1])

# cat을 채워넣으면서 해당 행의 max_front, max_back을 채워나감

for i in range(1, n):
    for j in range(k):
        cat[i][j] += max(max_front[j], max_back[j])
    for j in range(1, k):
        max_front[j] = max(cat[i][j - 1], max_front[j - 1])
    for j in range(k - 2, -1, -1):
        max_back[j] = max(cat[i][j + 1], max_back[j + 1])

print(max(cat[n - 1]))