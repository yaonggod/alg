import sys
n = int(input())
div = list(map(int, input().split()))
m = int(input())

minimum = 0
maximum = max(div)
ans = 0

while minimum <= maximum:
    # 상한액
    mid = (minimum + maximum) // 2
    sum = 0
    for i in range(n):
        if div[i] <= mid:
            sum += div[i]
        else:
            sum += mid
    if sum <= m:
        ans = max(ans, mid)
        minimum = mid + 1
    else:
        maximum = mid - 1

print(ans)