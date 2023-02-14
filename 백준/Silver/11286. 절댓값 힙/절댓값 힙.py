import heapq
import sys

heap = []
n = int(sys.stdin.readline())
for i in range(n):
    x = int(sys.stdin.readline())
    if x == 0:
        if heap == []:
            print(0)
        else:
            y = heapq.heappop(heap)
            print(y[0] * y[1])
    else:
        heapq.heappush(heap, (x if x > 0 else -x, 1 if x > 0 else -1))
            