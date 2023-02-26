import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**9)

n = int(input())
tree = [[] for _ in range(n + 1)]
for _ in range(n - 1):
    a, b, w = map(int, input().split())
    tree[a].append((b, w))
    tree[b].append((a, w))

maxnode = 0
maxlen = 0
visited = [False] * (n + 1)
# 부모 노드 i에서 가장 멀리 나온 노드
def dfs(i, l):
    global maxlen
    global maxnode
    for t in tree[i]:
        if not visited[t[0]]:
            visited[t[0]] = True
            dfs(t[0], l + t[1])
            visited[t[0]] = False
    else:
        if l > maxlen:
            maxlen = l
            maxnode = i

visited[1] = True
dfs(1, 0)

# maxnode에서 가장 멀리 나온 노드
visited = [False] * (n + 1)
visited[maxnode] = True
dfs(maxnode, 0)
print(maxlen)