from collections import deque

def ysf(n, k):
    arr = [i for i in range(n)]
    arr = deque(arr)
    lastperson = 0
    while arr:
        for _ in range(k - 1):
            arr.append(arr.popleft())
        lastperson = arr.popleft()
    return lastperson

n, k = map(int, input().split())
if n == 1:
    print(1)
elif n == 2:
    print(ysf(2, k) + 1)
else: 
    p = ysf(2, k)
    for i in range(3, n + 1):
        p = (p + k) % i
    print(p + 1)