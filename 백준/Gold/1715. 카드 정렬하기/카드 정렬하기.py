import heapq
import sys

heap = []
n = int(sys.stdin.readline())
for i in range(n):
    heapq.heappush(heap, int(sys.stdin.readline()))

count = 0
while True:
    if len(heap) == 1:
        break
    x = heapq.heappop(heap)
    y = heapq.heappop(heap)
    heapq.heappush(heap, x + y)
    count += x + y
    
print(count)