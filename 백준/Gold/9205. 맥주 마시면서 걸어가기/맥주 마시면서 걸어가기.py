import sys
from collections import deque
input = sys.stdin.readline

t = int(input())
for _ in range(t):
    # 편의점 개수
    n = int(input())
    # 편의점 위치
    sanggeun = list(map(int, input().split()))
    cvs = deque()
    for _ in range(n):
        cvs.append(list(map(int, input().split())))
    # 락페 위치
    fes = list(map(int, input().split()))

    # 큐를 두 개를 관리해야 한다거나.. 이미 방문한 편의점에서 뻗어나가는 큐, 방문한 편의점 관리 큐
    # 이미 방문한 편의점에서 뻗어나가는 큐가 100개를 다 돌고도 갈 수 있는 편의점이 없으면 탈출
    # 혹은 락페 위치가 편의점 바운더리 안에 있으면 탈출
    # 100개니까 5000000번이면 감당 가능하지 않을까
    result = "sad"
    queue = deque()
    queue.append(sanggeun)
    # 큐가 비면 갈 수 있는 편의점이 없다는 뜻
    while queue:
        # 기준점
        sx, sy = queue.popleft()
        # 만약에 여기서 락페를 갈 수 있으면 탈출
        if abs(sx - fes[0]) + abs(sy - fes[1]) <= 1000:
            result = "happy"
            break
        # 있는 모든 편의점을 검색해서 
        for _ in range(len(cvs)):
            x, y = cvs.popleft()
            # sx, sy에서 갈 수 있는 모든 편의점을 큐에 다 넣음
            if abs(sx - x) + abs(sy - y) <= 1000:
                queue.append([x, y])
            # 못가면 다음 검색을 위해 다시 집어넣고
            else:
                cvs.append([x, y])
    print(result)