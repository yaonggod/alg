import sys
input = sys.stdin.readline
word = list(input().strip())
explode = list(input().strip())
n = len(explode)

stack = []
for letter in word:
    stack.append(letter)
    if stack[-n:] == explode:
        for _ in range(n):
            stack.pop()
                        
if stack == []:
    print('FRULA')
else:
    print(*stack, sep='')