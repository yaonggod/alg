n = int(input())
sanggeun = set(map(int, input().split()))
m = int(input())
cards = list(map(int, input().split()))

for i in range(m):
    if cards[i] in sanggeun:
        print(1, end = " ")
    else:
        print(0, end = " ")