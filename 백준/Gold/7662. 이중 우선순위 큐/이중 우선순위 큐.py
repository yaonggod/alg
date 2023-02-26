import sys
from heapq import heappush, heappop
input = sys.stdin.readline


t = int(input())
for _ in range(t):
    minheap = []
    maxheap = []
    k = int(input())
    deleted = [0] * k
    for i in range(k):
        y, n = map(str, input().split())
        n = int(n)
        if y == "I":
            heappush(minheap, (-n, i))
            heappush(maxheap, (n, i))
        elif y == "D":
            if n == 1 and minheap:
                while minheap: 
                    x = heappop(minheap)
                    if not deleted[x[1]]:
                        deleted[x[1]] = 1
                        break
            elif n == -1 and maxheap:
                while maxheap:
                    y = heappop(maxheap)
                    if not deleted[y[1]]:
                        deleted[y[1]] = 1
                        break
    minq = "EMPTY"
    maxq = "EMPTY"
    while minheap:
        x = heappop(minheap)
        if not deleted[x[1]]:
            minq = -x[0]
            break
    while maxheap:
        y = heappop(maxheap)
        if not deleted[y[1]]:
            maxq = y[0]
            break
    if minq == "EMPTY" or maxq == "EMPTY":
        print("EMPTY")
    else:
        print(minq, maxq)
        