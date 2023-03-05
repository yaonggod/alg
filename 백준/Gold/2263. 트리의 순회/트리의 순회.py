import sys
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 6)

n = int(input())
inorder = list(map(int, input().split()))
postorder = list(map(int, input().split()))
# 나 인덱스 찾기
inindex = [0 for i in range(n + 1)]
for i in range(n):
    inindex[inorder[i]] = i
# 인 : 왼 - 나 - 오
# 포 : 왼 - 오 - 나
# 프 : 나 - 왼 - 오
# 서브 트리들을 '나'를 기준으로 왼 - 오로 나눔

def preorder(instart, poststart, length):
    if poststart + length - 1 > len(inorder):
        return
    # 나 : 포스트배열의 제일 마지막 원소
    me = postorder[poststart + length - 1]
    print(me, end = " ")
    # 인배열을 나를 기준으로 왼쪽 오른쪽으로 나눔
    # 왼쪽
    leftlength = inindex[me] - instart
    if leftlength == 1:
        print(postorder[poststart], end = " ")
    elif leftlength > 1:
        preorder(instart, poststart, leftlength)
    # 오른쪽
    rightlength = length - leftlength - 1
    if rightlength == 1:
        print(postorder[poststart + leftlength], end = " ")
    elif rightlength > 1:
        preorder(instart + leftlength + 1, poststart + leftlength, rightlength)

preorder(0, 0, len(inorder))