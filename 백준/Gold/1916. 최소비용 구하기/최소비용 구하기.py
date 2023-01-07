import sys
import heapq
input = sys.stdin.readline
INF = int(1e9)

n = int(input())
m = int(input())
graph = [[] for _ in range(n + 1)]
for _ in range(m):
    a, b, c = map(int, input().split())
    graph[a].append([b, c])
# [[], [[2, 2], [3, 3], [4, 1], [5, 10]], [[4, 2]], [[4, 1], [5, 1]], [[5, 3]], []]
start, end = map(int, input().split())
distance = [INF] * (n + 1)

def dijkstra(graph, start):
    distance[start] = 0
    queue = []
    heapq.heappush(queue, [0, start])
    while queue:
        dist, now = heapq.heappop(queue)
        if distance[now] < dist:
            continue
        for i in graph[now]:
            if dist + i[1] < distance[i[0]]:
                distance[i[0]] = dist + i[1]
                heapq.heappush(queue, [dist + i[1], i[0]])
# 0 1
# [1000000000, 0, 2, 3, 1, 10]
# 1 4
# [1000000000, 0, 2, 3, 1, 4]
# 2 2
# [1000000000, 0, 2, 3, 1, 4]
# 3 3
# [1000000000, 0, 2, 3, 1, 4]
# 4 5
# [1000000000, 0, 2, 3, 1, 4]
# 10 5

dijkstra(graph, start)
print(distance[end])
