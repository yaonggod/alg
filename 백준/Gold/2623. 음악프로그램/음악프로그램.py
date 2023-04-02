import sys
input = sys.stdin.readline
n, m = map(int, input().split())

graph = [set() for _ in range(n + 1)]
count = [0] * (n + 1)
# pd가 언급했다
said = set()
for _ in range(m):
    lst = list(map(int, input().split()))
    # 선후 관계 없이 한사람만 언급함
    if len(lst) == 2:
        said.add(lst[1])
    else:
        for i in range(1, len(lst) - 1):
            a = lst[i]
            b = lst[i + 1]
            # a, b가 언급됨
            said.add(a)
            said.add(b)
            # a 뒤에 b 관계 추가
            if b not in graph[a]:
                graph[a].add(b)
                count[b] += 1

result = []
# pd에게 언급된 사람, 언급되지 않은 사람 현황 파악
visited = [False] * (n + 1)
from collections import deque
queue = deque()
for i in said:
    if count[i] == 0:
        visited[i] = True
        queue.append(i)

while queue:
    x = queue.popleft()
    result.append(x)
    for i in graph[x]:
        count[i] -= 1
        if count[i] == 0:
            visited[i] = True
            queue.append(i)

# pd가 언급한 모든 사람을 배치하는 데 실패
if len(result) != len(said):
    print(0)
else:
    for i in result:
        print(i)
    # 언급한 사람 외 나머지 사람 배치
    for i in range(1, n + 1):
        if not visited[i]:
            print(i)
