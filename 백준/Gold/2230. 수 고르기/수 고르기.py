import sys
n, m = map(int, input().split())
arr = []
for _ in range(n):
    arr.append(int(input()))

arr.sort()
l = len(arr)

if l == 1:
    print(m)
else:
    start = 0
    end = 1
    mingap = arr[-1] - arr[0]
    while start < l - 1 and end < l:
        if abs(arr[start] - arr[end]) >= m:
            mingap = min(abs(arr[start] - arr[end]), mingap)
            start += 1
            end = start + 1
        else:
            end += 1
        if mingap == m:
            break
    print(mingap)