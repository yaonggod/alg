import sys
input = sys.stdin.readline
t = int(input())
for _ in range(t):
    n, k = map(int, input().split())
    
    # 각 건물이 지어지는 데 걸리는 시간
    build = [0]
    build += list(map(int, input().split()))
    
    # 몇시부터 몇시까지
    time = [[0, 0] for _ in range(n + 1)]

    graph = [[] for _ in range(n + 1)]
    count = [0] * (n + 1)

    for _ in range(k):
        x, y = map(int, input().split())
        graph[x].append(y)
        count[y] += 1
    
    target = int(input())
    
    from collections import deque
    queue = deque()
    for i in range(1, n + 1):
        # 첫번째면
        if count[i] == 0:
            # 0초부터 시작
            time[i] = [0, build[i]]
            queue.append(i)
   
    while queue:
        x = queue.popleft()
        if x == target:
            break
        for i in graph[x]:
            count[i] -= 1
            # 기존에 시작하는 시간보다 앞 순서 건물이 늦게 지어져서 늦게 시작해야 함
            if time[i][0] < time[x][1]:
                time[i] = [time[x][1], time[x][1] + build[i]]
            if count[i] == 0:
                queue.append(i)
    
    print(time[target][1])