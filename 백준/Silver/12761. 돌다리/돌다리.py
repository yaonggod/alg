from collections import deque
a, b, n, m = map(int, input().split())
stone = [0] * 100001
queue = deque([n])
while True:
    x = queue.popleft()
    if x == m:
        print(stone[x])
        break
    for i in [x + 1, x - 1, x + a, x - a, x + b, x - b, x * a, x * b]:
        if 0 <= i <= 100000 and stone[i] == 0:
            stone[i] = stone[x] + 1
            queue.append(i)