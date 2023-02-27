import sys
input = sys.stdin.readline

n, m = map(int, input().split())

def nm(n, m, arr):
    if len(arr) == m:
        print(*arr)
    else:
        for i in range(1, n + 1):
            if not arr:
                arr.append(i)
                nm(n, m, arr)
                arr.pop()
            else:
                if i >= arr[-1]:
                    arr.append(i)
                    nm(n, m, arr)
                    arr.pop()

nm(n, m, [])