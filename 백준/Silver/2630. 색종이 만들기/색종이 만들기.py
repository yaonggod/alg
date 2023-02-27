import sys
input = sys.stdin.readline

n = int(input())
paper = []
for _ in range(n):
    paper.append(list(map(int, input().split())))

white_count = 0
blue_count = 0

# (0, 0)에서 n의 길이로 시작해서
def pap(length, sr, sc):
    global white_count
    global blue_count
    blue = False
    white = False
    # length * length 영역 탐색해서 한 가지 색으로 통일되었는지 검사
    for i in range(sr, sr + length):
        for j in range(sc, sc + length):
            if paper[i][j] == 0:
                white = True
            else:
                blue = True
            if blue and white:
                break
    # 두 색깔이 모두 나오면 길이를 반으로 나눠서 4개 영역으로 나눈 후 탐색
    if blue and white:
        # 길이가 0으로 가지 않게
        if length // 2 > 0:
            pap(length // 2, sr, sc)
            pap(length // 2, sr + length // 2, sc)
            pap(length // 2, sr, sc + length // 2)
            pap(length // 2, sr + length // 2, sc + length // 2)
    # 한 색깔만 나오면 카운트 += 1
    elif blue and not white:
        blue_count += 1
    elif not blue and white:
        white_count += 1

pap(n, 0, 0)
print(white_count)
print(blue_count)
