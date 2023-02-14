import sys
input = sys.stdin.readline

r, c = map(int, input().split())
board = [list(input()) for _ in range(r)]
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

max_len = 1
visited = [False] * 26
visited[ord(board[0][0]) - 65] = True
def backtracking(x, y, lst : list):
    global max_len
    global visited
    if len(lst) > max_len:
        max_len = len(lst)
    
    for d in range(4):
        nx = x + dx[d]
        ny = y + dy[d]
        if 0 <= nx < r and 0 <= ny < c and not visited[ord(board[nx][ny]) - 65]:
            visited[ord(board[nx][ny]) - 65] = True
            lst.append(board[nx][ny])
            backtracking(nx, ny, lst)
            lst.pop()
            visited[ord(board[nx][ny]) - 65] = False

backtracking(0, 0, [board[0][0]])
print(max_len)