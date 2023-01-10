import sys
input = sys.stdin.readline
t = int(input())
for _ in range(t):
    n = int(input())
    lst = list(map(int, input().split()))
    max_price = lst[-1]
    sell = 0
    for i in range(-2, -(n + 1), -1):
        if lst[i] > max_price:
            max_price = lst[i]
        else:
            sell += max_price - lst[i]
    print(sell)