import sys
input = sys.stdin.readline
from collections import deque

n, a, b = map(int, input().split())
set_a = set()
set_a.add(a)
set_b = set()
set_b.add(b)

day = 0
while True:
    if len(set_a) == 0 or len(set_b) == 0:
        day = -1
        break
    
    new_set_a = set()
    new_set_b = set()
    
    for i in set_a:
        i_l = i - 2 ** day
        i_r = i + 2 ** day
        if 1 <= i_l <= n:
            new_set_a.add(i_l)
        if 1 <= i_r <= n:
            new_set_a.add(i_r)
            
    for i in set_b:
        i_l = i - 2 ** day
        i_r = i + 2 ** day
        if 1 <= i_l <= n:
            new_set_b.add(i_l)
        if 1 <= i_r <= n:
            new_set_b.add(i_r)
    
    set_a = new_set_a
    set_b = new_set_b
    day += 1
    if set_a & set_b:
        break
print(day)