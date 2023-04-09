import sys
input = sys.stdin.readline
n, m = map(int, input().split())
app = [0] + list(map(int, input(). split()))
cost = [0] + list(map(int, input(). split()))

memory = [0] * (sum(cost) + 1)

for i in range(1, n + 1):
    a = app[i]
    c = cost[i]
    newmemory = [0] * (len(memory))
    for j in range(len(memory)):
        if j >= c:
            newmemory[j] = max(memory[j - c] + a, memory[j])
        else:
            newmemory[j] = memory[j]
    memory = newmemory

for i in range(len(memory)):
    if memory[i] >= m:
        print(i)
        break