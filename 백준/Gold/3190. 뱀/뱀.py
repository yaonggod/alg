from collections import deque
n = int(input())
k = int(input())
apple = []
for _ in range(k):
    apple.append(list(map(int, input().split())))
# 사과의 실제 위치를 (0, 0) 기준으로 맞춰줌
for i in range(k):
    for j in range(2):
        apple[i][j] -= 1
l = int(input())
move = []
for _ in range(l):
    move.append(list(map(str, input().split())))
    
# 오른쪽, 아래, 왼쪽, 위
dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

# 뱀의 시작 위치   
queue = deque([[0, 0]])
time = 0
# 방향
a = 0

i = 0
while True:
    x = queue[-1]
    new_x = [x[0] + dx[a], x[1] + dy[a]]
    if new_x in queue:
        time += 1
        break
    if 0 <= new_x[0] < n and 0 <= new_x[1] < n:
        if new_x in apple:
            apple.remove(new_x)
            queue.append(new_x)
        else:
            queue.popleft()
            queue.append(new_x)
        time += 1
    else:
        time += 1
        break
    if i < len(move):
        if time == int(move[i][0]):
            if move[i][1] == 'D':
                a += 1
                if a >= 4:
                    a -= 4
                i += 1
            else:
                i += 1
                a -= 1
                if a < -4:
                    a += 4

print(time)
    