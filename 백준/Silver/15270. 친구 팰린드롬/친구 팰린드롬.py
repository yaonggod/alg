import sys
n, m = map(int, input().split())
friends = []
for _ in range(m):
    friends.append(list(map(int, input().split())))

# 이 조합을 택하냐 안하냐의 문제
def backtracking(a, c):
    global visited
    global max_count
    if a == m:
        if c > max_count:
            max_count = c
    else:
        comb = friends[a]
        if not visited[comb[0]] and not visited[comb[1]]:
            visited[comb[0]] = True
            visited[comb[1]] = True
            backtracking(a + 1, c + 1)
            visited[comb[0]] = False
            visited[comb[1]] = False
            backtracking(a + 1, c)
        else:
            backtracking(a + 1, c)

        
max_count = 0
visited = [False] * (n + 1)
backtracking(0, 0)

if max_count == n // 2:
    print(n)
else:
    print(max_count * 2 + 1)