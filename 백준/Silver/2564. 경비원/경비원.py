# 가로 세로 
w, h = map(int, input().split())
# 상점의 개수
n = int(input())

# 상점
store = []
for _ in range(n):
    a, b = map(int, input().split())
    store.append([a, b])
# 동근
dg = list(map(int, input().split()))

answer = 0

def length(arr, x):
    result = 0
    result += min(x - arr[0] + x - arr[1], arr[0] + arr[1])
    return result

# 1 북 2 남 3 서 4 동
for s in store:
    p = (s[0], dg[0])
    l = (s[1], dg[1])
# 서로 마주보고 있으면
# 북 <-> 남 남 <-> 북 
    if p in [(1, 2), (2, 1)]:
        answer += h
        answer += length(l, w)
# 동 <-> 서 서 <-> 동
    elif p in [(3, 4), (4, 3)]:
        answer += w
        answer += length(l, h)
# 서 <-> 북 
    elif p in [(1, 3), (3, 1)]:
        answer += (l[0] + l[1])
# 동 <-> 남
    elif p in [(2, 4), (4, 2)]:
        answer += (w + h - l[0] - l[1])
# 1 북 2 남 3 서 4 동
# 북 <-> 동
    elif p == (1, 4):
        answer += (l[1] + w - l[0])
    elif p == (4, 1):
        answer += (l[0] + w - l[1])
# 남 <-> 서
    elif p == (2, 3):
        answer += (h - l[1] + l[0])
    elif p == (3, 2):
        answer += (h - l[0] + l[1])
    else:
        answer += abs(l[1] - l[0])
print(answer)

