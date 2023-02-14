import sys
input = sys.stdin.readline

n = int(input())
car_in = [input() for _ in range(n)]
car_out = [input() for _ in range(n)]

count = 0
for i in range(1, n):
    for j in range(i - 1, -1, -1):
        if car_out.index(car_in[i]) < car_out.index(car_in[j]):
            count += 1
            break

print(count)