import sys
input = sys.stdin.readline

n, m = map(int, input().split())
lst = list(map(int, input().split()))
lst.sort()
def nm(n, m, arr):
    if len(arr) == m:
        print(*arr)
    else:
        for i in lst:
            if i not in arr:
                arr.append(i)
                nm(n, m, arr)
                arr.pop()

nm(n, m, [])