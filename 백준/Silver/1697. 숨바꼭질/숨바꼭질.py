from collections import deque
n, k = map(int, input().split())

queue = deque([n])
visited = [0] * 100001
while True:
    x = queue.popleft()
    if x == k:
        break
    if 0 <= x + 1 <= 100000 and visited[x + 1] == 0:
        visited[x + 1] = visited[x] + 1
        queue.append(x + 1)
    if 0 <= x - 1 <= 100000 and visited[x - 1] == 0:
        visited[x - 1] = visited[x] + 1
        queue.append(x - 1)
    if 0 <= x * 2 <= 100000 and visited[x * 2] == 0:
        visited[x * 2] = visited[x] + 1
        queue.append(x * 2)
            
print(visited[k])