word = input()
answer = ''
for l in word:
    if ord(l) >= 97:
        answer += chr(ord(l) - 32)
    else:
        answer += chr(ord(l) + 32)
print(answer)