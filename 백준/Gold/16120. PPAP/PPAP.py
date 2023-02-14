import sys
input = sys.stdin.readline
word = list(input().strip())

stack = []
for letter in word:
    stack.append(letter)
    try:
        if stack[-4:] == ['P', 'P', 'A', 'P']:
            for _ in range(3):
                stack.pop()
    except:
        pass

if stack == ['P']:
    print('PPAP')
else:
    print('NP')      
