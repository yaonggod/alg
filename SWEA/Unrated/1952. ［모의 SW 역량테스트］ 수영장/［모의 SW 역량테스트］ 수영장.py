from collections import deque
from copy import deepcopy

t = int(input())
for tc in range(1, t + 1):
    day, m1, m3, year = map(int, input().split())
    lst = list(map(int, input().split()))
    
    queue = deque()
    visited = [False] * 12
    queue.append((0, visited))
    
    min_p = year
    while queue:
        p, v = queue.popleft()
        if v == [True] * 12 and p < min_p:
            min_p = p
        for i in range(12):
            if not v[i]:
                # 하루
                v1 = deepcopy(v)
                v1[i] = True
                p1 = p + lst[i] * day
                queue.append((p1, v1))
                # 한달
                v2 = deepcopy(v)
                v2[i] = True
                p2 = p + m1
                if lst[i]:
                    queue.append((p2, v2))
                else:
                    queue.append((p, v2))
                # 세달
                if i <= 9:
                    v3 = deepcopy(v)
                    v3[i], v3[i + 1], v3[i + 2] = True, True, True
                    p3 = p + m3
                    if lst[i] or lst[i + 1] or lst[i + 2]:
                        queue.append((p3, v3))
                    else:
                        queue.append((p, v3))
                break
    print('#{} {}'.format(tc, min_p))