from collections import deque

t = int(input())
for i in range(t):
    # 가로길이 m, 세로길이 n -> n이 행이고 m이 열
    m, n, k = map(int, input().split())  
    baechu = [[0] * m for _ in range(n)]
    for j in range(k):
        x, y = map(int, input().split())
        baechu[y][x] = 1
    
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1] 
        
    def bfs(graph, n, m, a, b):
        graph[a][b] = 0
        queue = deque([(a, b)])
        count = 1
        
        while queue:
            x, y = queue.popleft()
            for i in range(4):
                nx = x + dx[i]
                ny = y + dy[i]
                if 0 <= nx < n and 0 <= ny < m and graph[nx][ny] == 1:
                    graph[nx][ny] = 0
                    count += 1
                    queue.append((nx, ny))
        return count
    lst = []             
    for x in range(n):
        for y in range(m):
            if baechu[x][y] == 1:
                lst.append(bfs(baechu, n, m, x, y))
    print(len(lst))