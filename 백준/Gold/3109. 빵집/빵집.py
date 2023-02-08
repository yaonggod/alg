from time import time
t1 = time()
import sys
from copy import deepcopy
input = sys.stdin.readline
r, c = map(int, input().split())
matrix = []
for _ in range(r):
    matrix.append(list(input().strip()))
success = 0
visited = [[False] * c for _ in range(r)]
route = [0] * r
def backtracking(lst):
    # print(lst)
    if len(lst) == c:
        # print("success")
        return True
    else:
        if lst[-1] - 1 >= 0 and matrix[lst[-1] - 1][len(lst)] == "." and not visited[lst[-1] - 1][len(lst)]:
            visited[lst[-1] - 1][len(lst)] = True
            lst.append(lst[-1] - 1)
            if backtracking(lst):
                return True
            lst.pop()
        if matrix[lst[-1]][len(lst)] == "." and not visited[lst[-1]][len(lst)]:
            visited[lst[-1]][len(lst)] = True
            lst.append(lst[-1])
            if backtracking(lst):
                return True
            lst.pop()
        if lst[-1] + 1 < r and matrix[lst[-1] + 1][len(lst)] == "." and not visited[lst[-1] + 1][len(lst)]:
            visited[lst[-1] + 1][len(lst)] = True
            lst.append(lst[-1] + 1)
            if backtracking(lst):
                return True
            lst.pop()
    return False
for i in range(r):
    visited[i][0] = True
    if backtracking([i]):
        success += 1

t2 = time()
print(success)
# print(t2 - t1)