import sys
input = sys.stdin.readline
a, b = map(int, input().split())

ans = 0
if a == b:
    ans = 0
else:
    sec = 1
    xset1 = set()
    xset1.add(a)
    xset2 = set()
    visited = [[False, False] for _ in range(500001)]
    visited[a][0] = True 

    # sec초일때 위치
    while True:
        b += sec
        if not 0 <= b <= 500000:
            ans = -1
            break
        # sec - 1초일때 위치 큐로 sec초일때 위치 큐를 만들기
        for x in xset1:
            for y in [x - 1, x + 1, x * 2]:
                if 0 <= y <= 500000 and visited[y][sec % 2] == False:
                    visited[y][sec % 2] = True
                    xset2.add(y)
        
        if visited[b][sec % 2]:
            ans = sec
            break
             
        xset1 = xset2
        xset2 = set()
        sec += 1
        
print(ans)
