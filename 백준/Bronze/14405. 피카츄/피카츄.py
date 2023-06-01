word = input()

i = 0
success = True
while i < len(word):
    if word[i:i + 2] == 'pi':
        i += 2
    elif word[i:i + 2] == 'ka':
        i += 2
    elif word[i:i + 3] == 'chu':
        i += 3
    else:
        success = False
        break

if success:
    print('YES')
else:
    print('NO')