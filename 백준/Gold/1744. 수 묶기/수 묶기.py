from collections import deque
n = int(input())
lst = [int(input()) for _ in range(n)]
lst.sort()
lst_plus = deque()
lst_zero = deque()
lst_minus = deque()
for i in lst:
    if i > 0:
        lst_plus.append(i)
    elif i == 0:
        lst_zero.append(i)
    else:
        lst_minus.append(i)

answer = 0

while len(lst_plus) > 1:
    a = lst_plus.pop()
    b = lst_plus.pop()
    if a == 1 or b == 1:
        answer += (a + b)
    else:
        answer += a * b
while len(lst_minus) > 1:
    a = lst_minus.popleft()
    b = lst_minus.popleft()
    answer += a * b

x, y, z = len(lst_plus), len(lst_zero), len(lst_minus)
# 0 0 0
# 1 0 0
if x == 1 and y == 0 and z == 0:
    answer += lst_plus[0]
# 0 1 0
# 0 0 1
elif x == 0 and y == 0 and z == 1:
    answer += lst_minus[0]
# 1 1 0
elif x == 1 and y != 0  and z == 0:
    answer += lst_plus[0]
# 1 0 1
elif x == 1 and y == 0 and z == 1:
    answer += max(lst_plus[0] + lst_minus[0], lst_plus[0] * lst_minus[0])
# 0 1 1
# 1 1 1
elif x == 1 and y != 0 and z == 1:
    answer += lst_plus[0]

print(answer)