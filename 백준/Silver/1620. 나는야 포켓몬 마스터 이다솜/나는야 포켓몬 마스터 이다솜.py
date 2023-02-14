m, n = map(int, input().split())
dict = {}
lst = []
for i in range(1, m + 1):
    dict[i] = str(input())
for i in range(n):
    lst.append(input())
    
dict2 = {v:k for k, v in dict.items()}
       
for i in lst:
    try:
        j = int(i)
        print(dict[j])
    except:
        print(dict2[i])