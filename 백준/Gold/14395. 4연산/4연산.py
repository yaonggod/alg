from collections import deque
s, t = map(int, input().split())

if s == t:
    print(0)
else:
    result = False
    visited = set()
    visited.add(s)
    queue = deque([[s, '']])
    while queue:
        a, b = queue.popleft()
        if a == t:
            result = True
            print(b)
            break
        else:
            if a * a <= 1000000000 and a * a not in visited:
                queue.append([a * a, b + '*'])
                visited.add(a * a)
            if a + a <= 1000000000 and a + a not in visited:
                queue.append([a + a, b + '+'])
                visited.add(a + a)
            if a != 0 and 1 not in visited:
                queue.append([1, b + '/'])
                visited.add(1)
    if result == False:
        print(-1)