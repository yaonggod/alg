import sys
input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))

s = set(arr)
s = list(s)
s.sort()
ans = []

def bs(n, s, start, end):
    mid = (start + end) // 2
    if s[mid] == n:
        return mid
    elif n > s[mid]:
        return bs(n, s, mid + 1, end)
    else:
        return bs(n, s, start, mid - 1)

for a in arr:
    ans.append(bs(a, s, 0, len(s) - 1))

print(*ans)