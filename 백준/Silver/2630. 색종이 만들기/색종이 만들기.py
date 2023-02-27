import sys
input = sys.stdin.readline

n = int(input())
paper = []
for _ in range(n):
    paper.append(list(map(int, input().split())))

white_count = 0
blue_count = 0

def pap(length, sr, sc):
    global white_count
    global blue_count
    blue = False
    white = False
    for i in range(sr, sr + length):
        for j in range(sc, sc + length):
            if paper[i][j] == 0:
                white = True
            else:
                blue = True
            if blue and white:
                break
    if blue and white:
        if length // 2 > 0:
            pap(length // 2, sr, sc)
            pap(length // 2, sr + length // 2, sc)
            pap(length // 2, sr, sc + length // 2)
            pap(length // 2, sr + length // 2, sc + length // 2)
    elif blue and not white:
        blue_count += 1
    elif not blue and white:
        white_count += 1

pap(n, 0, 0)
print(white_count)
print(blue_count)