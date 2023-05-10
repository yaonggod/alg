import sys
n, s = map(int, input().split())
arr = list(map(int, input().split()))
start = 0
end = 0
sumarr = arr[0]
minlen = n

while start < n and end < n:
    if sumarr < s:
        end += 1
        if end < n:
            sumarr += arr[end]
    else:
        if end - start + 1 < minlen:
            minlen = end - start + 1
        sumarr -= arr[start]
        start += 1
    if minlen == 1:
        break
if start == 0 and end == n and sum(arr) < s:
    minlen = 0
print(minlen)