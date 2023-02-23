import sys
input = sys.stdin.readline

n = int(input())
arr = []
for _ in range(n):
    arr.append(int(input()))

stack = []
ans = 0
for i in range(n - 1, -1, -1):
    while len(stack) and stack[-1][0] < arr[i]:
        ans += stack.pop()[1]
    if not len(stack):
        stack.append([arr[i], 1])
    elif stack[-1][0] > arr[i]:
        ans += 1
        stack.append([arr[i], 1])
    elif stack[-1][0] == arr[i]:
        x = stack.pop()[1]
        ans += x
        if len(stack):
            ans += 1
        stack.append([arr[i], x + 1])

print(ans)