import sys
# sys.stdin = open('11\불.txt')
input = sys.stdin.readline
from collections import deque

def fire(w, h, board):
    for i in range(h):
        for j in range(w):
            if board[i][j] == '*':
                start.append([i, j, 'fire'])
                
def sanggeun(w, h, board):
    for i in range(h):
        for j in range(w):
            if board[i][j] == '@':
                start.append([i, j, 'sanggeun'])
                visited[i][j] = 0

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]                
t = int(input())
for _ in range(t):
    w, h = map(int, input().split())
    board = [list(input()) for _ in range(h)]
    visited = [[-1] * w for _ in range(h)]
    start = []
    fire(w, h, board)
    sanggeun(w, h, board)
    queue = deque(start)
    # from pprint import pprint
    # pprint(board)
    # pprint(visited)
    # print(queue)
    answer = 'IMPOSSIBLE'
    while queue:
        x, y, type = queue.popleft()
        if type == 'fire':
            for d in range(4):
                nx = x + dx[d]
                ny = y + dy[d]
                if 0 <= nx < h and 0 <= ny < w and (board[nx][ny] == '.' or board[nx][ny] == '@'):
                    board[nx][ny] = '*'
                    queue.append([nx, ny, 'fire'])
        elif type == 'sanggeun':
            if x == 0 or y == 0 or x == h - 1 or y == w - 1:
                answer = visited[x][y] + 1
            else:
                for d in range(4):
                    nx = x + dx[d]
                    ny = y + dy[d]
                    if 0 <= nx < h and 0 <= ny < w and board[nx][ny] == '.' and visited[nx][ny] == -1:
                        visited[nx][ny] = visited[x][y] + 1
                        queue.append([nx, ny, 'sanggeun'])
        if answer != 'IMPOSSIBLE':
            break
    print(answer)