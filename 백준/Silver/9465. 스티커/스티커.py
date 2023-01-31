import sys
input = sys.stdin.readline
t = int(input())
for _ in range(t):
    n = int(input())
    sticker = []
    for _ in range(2):
        sticker.append(list(map(int, input().split())))
    answer = [[0, sticker[0][0], sticker[1][0]]]
    for i in range(1, n):
        lst = []
        # 안더한다
        lst.append(max(answer[i - 1][1], answer[i - 1][2]))
        # 1번 줄 더한다
        lst.append(max(answer[i - 1][0], answer[i - 1][2]) + sticker[0][i])
        # 2번 줄 더한다
        lst.append(max(answer[i - 1][0], answer[i - 1][1]) + sticker[1][i])
        answer.append(lst)
    print(max(answer[n - 1]))