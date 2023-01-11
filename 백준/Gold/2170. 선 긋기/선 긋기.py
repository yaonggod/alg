import sys
input = sys.stdin.readline
n = int(input())
length = 0
lst = [[*map(int, input().split())] for _ in range(n)]
lst.sort(key = lambda x : (x[0], x[1]))
a, b = lst[0][0], lst[0][1] 
length += b - a

for i in range(1, n):
    x, y = lst[i][0], lst[i][1]
    if b <= x:
        length += y - x
        a, b = x, y
    elif b <= y:
        length += y - b
        a, b = x, y
    else:
        a = x
            
print(length)