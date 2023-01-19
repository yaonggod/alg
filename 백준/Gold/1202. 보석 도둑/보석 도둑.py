import sys
input = sys.stdin.readline
from heapq import heapify, heappop, heappush

n, k = map(int, input().split())
gem = []
for _ in range(n):
    m, v = (map(int, input().split()))
    gem.append((m, v))
heapify(gem)
bag = []
for _ in range(k):
    bag.append(int(input()))
bag.sort()

gem_temp = []
result = 0

for b in bag:
    # 들어갈 수 있는 보석들 일단 다 밀어놓고 그중에서 가장 큰 거 찾기
    while gem and gem[0][0] <= b:
        heappush(gem_temp, -heappop(gem)[1])
    if gem_temp:
        result += -heappop(gem_temp)
    elif not gem:
        break    
print(result)
        