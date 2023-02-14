import sys
input = sys.stdin.readline

n = int(input())
file = {}
for _ in range(n):
    file_name = list(input().split('.'))
    file_name[1] = file_name[1].strip()
    if file_name[1] not in file:
        file[file_name[1]] = 1
    else:
        file[file_name[1]] += 1

file_ext = list(file.keys())
file_ext.sort()

for i in range(len(file_ext)):
    print(file_ext[i], file[file_ext[i]])