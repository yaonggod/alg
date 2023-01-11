import sys
import heapq
input = sys.stdin.readline
n = int(input())
c = []
for _ in range(n):
    c.append([*map(int, input().split())])
c.sort(key = lambda x : (x[0], x[1]))

room = [0]
heapq.heapify(room)

for i in range(n):
    x = heapq.heappop(room)
    if x <= c[i][0]:
        heapq.heappush(room, c[i][1])
    else:
        heapq.heappush(room, x)
        heapq.heappush(room, c[i][1])
        
print(len(room))