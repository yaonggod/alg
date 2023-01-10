s = input()

change = 0

for i in range(1, len(s)):
    if s[i - 1] != s[i]:
        change += 1
        
if change == 0:
    print(change)
else:
    if s[0] != s[-1]:
        print(change // 2 + 1)
    else:
        print(change // 2)