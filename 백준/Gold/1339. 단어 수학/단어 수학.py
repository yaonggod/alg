import sys
input = sys.stdin.readline

n = int(input())
alphabet = [0] * 26
for _ in range(n):
    s = input().strip()
    l = len(s) - 1
    for a in s:
        alphabet[ord(a) - 65] += 10 ** l
        l -= 1
alphabet.sort(reverse=True)
result = 0
for i in range(9, -1, -1):
    result += alphabet[9 - i] * i
print(result)
