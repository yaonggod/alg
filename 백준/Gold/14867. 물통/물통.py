from collections import deque
a, b, c, d = map(int, input().split())
visited = set()
visited.add((0, 0))
queue = deque([[0, 0, 0]])
answer = -1
while queue:
    x, y, t = queue.popleft()
    if x == c and y == d:
        answer = t
        break
    # F(x)
    if (a, y) not in visited:
        queue.append([a, y, t + 1])
        visited.add((a, y))
    # F(y)
    if (x, b) not in visited:
        queue.append([x, b, t + 1])
        visited.add((x, b))
    # E(x)
    if (0, y) not in visited:
        queue.append([0, y, t + 1])
        visited.add((0, y))
    # E(y)
    if (x, 0) not in visited:
        queue.append([x, 0, t + 1])
        visited.add((x, 0))
    # M(x, y)
    if x + y > b:
        if (x + y - b, b) not in visited:
            queue.append([x + y - b, b, t + 1])
            visited.add((x + y - b, b))
    else:
        if (0, x + y) not in visited:
            queue.append([0, x + y, t + 1])
            visited.add((0, x + y))
    # M(y, x)
    if x + y > a:
        if (a, x + y - a) not in visited:
            queue.append([a, x + y - a, t + 1])
            visited.add((a, x + y - a))
    else:
        if (x + y, 0) not in visited:
            queue.append([x + y, 0, t + 1])
            visited.add((x + y, 0))
            
print(answer) 