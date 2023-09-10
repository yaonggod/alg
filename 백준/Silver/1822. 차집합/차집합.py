na, nb = map(int, input().split())
a = list(map(int, input().split()))
b = list(map(int, input().split()))
res = set(a) - set(b)
result = sorted(res)
print(len(result))
if len(result) > 0:
    print(*result)
