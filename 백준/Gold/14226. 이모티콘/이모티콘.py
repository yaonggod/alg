from collections import deque
s = int(input())
visited = [[1, 0]]
queue = deque([(1, 0, 0)])
while queue:
    e, c, t = queue.popleft()
    if e == s:
        print(t)
        break
    # 1번 연산
    if e != c:
        queue.append((e, e, t + 1))
    # 2번 연산
    if e + c <= 1000 and [e + c, c] not in visited:
        queue.append((e + c, c, t + 1))  
        visited.append([e + c, c])
    # 3번 연산
    if e - 1 >= 0 and [e - 1, c] not in visited:
        queue.append((e - 1, c, t + 1))
        visited.append([e - 1, c])