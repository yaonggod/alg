import sys
input = sys.stdin.readline
from collections import deque
from copy import deepcopy

prime = [True] * 10000
prime[0] = False
prime[1] = False
for i in range(2, 10000):
    if prime[i] == True:
        j = 2
        while i * j < 10000:
            prime[i * j] = False
            j += 1

# def backtracking(n):
#     global result
#     if a == b:
#         result = n
#     else:
#         for i in range(4):
#             if i == 0:
#                 for j in range(1, 10):
#                     if a[i] != str(j):
#                         temp = a[i]
#                         a[i] = str(j)
#                         if prime[int(''.join(a))] and (visited[int(''.join(a))] == -1 or visited[int(''.join(a))] > n + 1):
#                             visited[int(''.join(a))] = n + 1
#                             # print(i, n + 1, int(''.join(a)))
#                             backtracking(n + 1)
#                         a[i] = temp

#             else:
#                 for j in range(10):
#                     if a[i] != str(j):
#                         temp = a[i]
#                         a[i] = str(j)
#                         if prime[int(''.join(a))] and (visited[int(''.join(a))] == -1 or visited[int(''.join(a))] > n + 1):
#                             visited[int(''.join(a))] = n + 1
#                             # print(i, n + 1, int(''.join(a)))
#                             backtracking(n + 1)
#                         a[i] = temp
                    
def bfs(a):
    global result
    queue = deque()
    queue.append(a)
    while queue:
        k = queue.popleft()
        if k == b:
            result = visited[int(''.join(k))]
            break
        for i in range(4):
            for j in range(10):
                n_k = deepcopy(k)
                n_k[i] = str(j)
                if 1000 <= int(''.join(n_k)) < 10000 and prime[int(''.join(n_k))] and visited[int(''.join(n_k))] == -1:
                    visited[int(''.join(n_k))] = visited[int(''.join(k))] + 1
                    queue.append(n_k)            
            
t = int(input())
for _ in range(t):
    result = 'impossible'
    a, b = map(str, input().split())
    a = list(a)
    b = list(b)
    visited = [-1] * 10000
    visited[int(''.join(a))] = 0
    bfs(a)
    print(result)