    
import sys
n, m = map(int, sys.stdin.readline().split())
lst = list(map(int, sys.stdin.readline().split()))
sum_list = [0]
for x in range(n):
    sum_list.append(sum_list[-1] + lst[x])
for _ in range(m):
    i, j = map(int, sys.stdin.readline().split())
    print(sum_list[j] - sum_list[i - 1])