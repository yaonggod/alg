import sys
n, m = map(int, sys.stdin.readline().split())
tree = list(map(int, sys.stdin.readline().split()))

start, end = 1, max(tree)

while True:
    # print(start, end)
    if start > end:
        break
    # 목재절단기의 높이
    mid = (start + end) // 2
    pieces = 0
    for i in range(n):
        if tree[i] - mid > 0:
            pieces += tree[i] - mid
    # print(pieces)
    # 조각이 너무 많이 나와서 mid를 늘려야함
    if pieces >= m:
        start = mid + 1
    else:
        end = mid - 1
print(end)