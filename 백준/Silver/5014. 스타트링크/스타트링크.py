from collections import deque
f, s, g, u, d = map(int, input().split())
button = [u, -d]
floor = [-1] * (f + 1)
floor[s] = 0
queue = deque([s])
answer = 'use the stairs'
while queue:
    k = queue.popleft()
    if k == g:
        answer = floor[k]
        break
    for d in range(2):
        nf = k + button[d]
        if 1 <= nf <= f:
            if floor[nf] == -1:
                floor[nf] = floor[k] + 1
                queue.append(nf)
            elif floor[nf] > floor[k] + 1:
                floor[nf] = floor[k] + 1
                queue.append(nf)
print(answer)