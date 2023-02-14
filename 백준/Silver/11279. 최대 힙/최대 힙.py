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
            print(-y)
    else:
        heapq.heappush(heap, -x)