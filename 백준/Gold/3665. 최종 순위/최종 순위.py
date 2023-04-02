import sys
input = sys.stdin.readline
t = int(input())
for _ in range(t):
    n = int(input())
    lastyear = [0] + list(map(int, input().split()))
    # i번의 작년 순위는 thisyear[i]위였다
    thisyear = [0] * (len(lastyear))
    for i in range(1, len(thisyear)):
        thisyear[lastyear[i]] = i
    
    # i가 j보다 높은 관계(i < j) 존재
    change = [[0] * (n + 1) for _ in range(n + 1)]

    m = int(input())
    for _ in range(m):
        a, b = map(int, input().split())
        # 작년의 관계랑 비교
        if thisyear[a] < thisyear[b]:
            change[b][a] = 1
        else:
            change[a][b] = 1

    for i in range(1, n):
        for j in range(i + 1, n + 1):
            if change[lastyear[j]][lastyear[i]] != 1:
                change[lastyear[i]][lastyear[j]] = 1
    
    impossible = False
    rank = [0] * n
    for i in range(1, n + 1):
        r = sum(change[i])
        if rank[r] != 0:
            impossible = True
        else:
            rank[r] = i
    
    if impossible:
        print("IMPOSSIBLE")
    else:
        for i in rank[::-1]:
            print(i, end = " ")
        print()