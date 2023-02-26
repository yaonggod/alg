import sys
input = sys.stdin.readline

cal = input()
stack = []
result = ""

for c in cal:
    if ord(c) - 65 >= 0 and ord(c) - 65 <= 26:
        result += c
    elif c == "+" or c == "-":
        if not stack:
            stack.append(c)
        elif stack[-1] == "(":
            stack.append(c)
        elif stack[-1] == "+" or stack[-1] == "-" or stack[-1] == "*" or stack[-1] == "/":
            while stack:
                if stack[-1] == "(":
                    break
                else:
                    result += stack.pop()
            stack.append(c)
    elif c == "*" or c == "/":
        if not stack:
            stack.append(c)
        elif stack[-1] == "(":
            stack.append(c)
        elif stack[-1] == "+" or stack[-1] == "-":
            stack.append(c)
        elif stack[-1] == "*" or stack[-1] == "/":
            while stack:
                if stack[-1] == "(" or stack[-1] == "+" or stack[-1] == "-":
                    break
                else:
                    result += stack.pop()
            stack.append(c)
    elif c == "(":
        stack.append(c)
    elif c == ")":
        while True:
            if stack[-1] == "(":
                stack.pop()
                break
            else:
                result += stack.pop()
while stack:
    result += stack.pop()
print(result)