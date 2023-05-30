import sys

def backtracking(i, s):
    global visited
    global max_sum
    if i == 11:
        if s > max_sum:
            max_sum = s
    else:
        for idx in range(11):
            if position[i][idx] > 0 and not visited[idx]:
                visited[idx] = True
                backtracking(i + 1, s + position[i][idx])
                visited[idx] = False

t = int(input())
for _ in range(t):
    position = []
    for _ in range(11):
        position.append(list(map(int, input().split())))
    visited = [False] * 11
    max_sum = 0
    backtracking(0, 0)
    print(max_sum)