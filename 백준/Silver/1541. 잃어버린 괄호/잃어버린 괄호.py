n = input()
n = n.split('-')
total = 0

n[0] = n[0].split('+')
for i in range(len(n[0])):
    total += int(n[0][i])

for i in n[1:]:
    i = i.split('+')
    for j in range(len(i)):
        total -= int(i[j])

print(total)