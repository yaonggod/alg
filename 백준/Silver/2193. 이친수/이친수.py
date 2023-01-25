n = int(input())
a = 2
b = n - 3
lst = [0] * (n + 1)
if n == 1 or n == 2:
    print(1)
else:
    lst[1] = 1
    lst[2] = 1
    for i in range(3, n + 1):
        lst[i] = lst[i - 1] + lst[i - 2]
    print(lst[n])