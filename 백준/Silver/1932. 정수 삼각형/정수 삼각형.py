n = int(input())
lst = [[]]
for _ in range(n):
    lst.append(list(map(int, input().split())))

if n == 1:
    print(lst[1][0])
elif n == 2:
    print(max(lst[2][0], lst[2][1]) + lst[1][0])
else:
    for i in range(2, n + 1):
        for j in range(i):
            if j == 0:
                lst[i][j] += lst[i - 1][j]
            elif j == i - 1:
                lst[i][j] += lst[i - 1][j - 1]
            else:
                lst[i][j] += max(lst[i - 1][j - 1], lst[i - 1][j])

    # print(lst)
    print(max(lst[n]))
        