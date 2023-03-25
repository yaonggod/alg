import sys
input = sys.stdin.readline
n = int(input())
chess = []
for _ in range(n):
    chess.append(list(map(int, input().split())))

blackx = []
blacky = []
whitex = []
whitey = []
for i in range(n):
    for j in range(n):
        if chess[i][j] == 1:
            if (i + j) % 2 == 0:
                blackx.append(i)
                blacky.append(j)
            else:
                whitex.append(i)
                whitey.append(j)

max_black = 0
max_white = 0
visited_black = [False] * len(blackx)
visited_white = [False] * len(whitex)

# idx번째 비숍을 넣을까 말까
def backtracking_black(idx, cnt):
    global max_black
    
    # 다 넣어봤어
    if idx == len(blackx):
        # 놓은 비숍 갯수 갱신
        if cnt > max_black:
            max_black = cnt
        return

    i = blackx[idx]
    j = blacky[idx]
    # idx번째 자리에 비숍을 넣을 수 있을까
    # 내 앞에 있는 visited 비숍에서 대각선이 없어야됨
    for k in range(idx):
        if visited_black[k] and abs(blackx[k] - i) == abs(blacky[k] - j):
            break
    else:
        visited_black[idx] = True
        backtracking_black(idx + 1, cnt + 1)
        visited_black[idx] = False
    
    # 비숍 안넣고도 다음단계 ㄱㄱ
    backtracking_black(idx + 1, cnt)

# idx번째 비숍을 넣을까 말까
def backtracking_white(idx, cnt):
    global max_white
    
    # 다 넣어봤어
    if idx == len(whitex):
        # 놓은 비숍 갯수 갱신
        if cnt > max_white:
            max_white = cnt
        return

    i = whitex[idx]
    j = whitey[idx]
    # idx번째 자리에 비숍을 넣을 수 있을까
    # 내 앞에 있는 visited 비숍에서 대각선이 없어야됨
    for k in range(idx):
        if visited_white[k] and abs(whitex[k] - i) == abs(whitey[k] - j):
            break
    else:
        visited_white[idx] = True
        backtracking_white(idx + 1, cnt + 1)
        visited_white[idx] = False
    
    # 비숍 안넣고도 다음단계 ㄱㄱ
    backtracking_white(idx + 1, cnt)

backtracking_black(0, 0)
backtracking_white(0, 0)
print(max_black + max_white)