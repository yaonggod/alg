def fibonacci(n):
    if n == 0:
        return [1, 0]
    elif n == 1:
        return [0, 1]
    else:
        fibo = [[] for _ in range(n + 1)]
        fibo[0] = [1, 0]
        fibo[1] = [0, 1]
        for i in range(2, n + 1):
            fibo[i] = [fibo[i - 1][0] + fibo[i - 2][0], fibo[i - 1][1] + fibo[i - 2][1]]
    
    return fibo[n]
    
t = int(input())
for _ in range(t):
    n = int(input())
    print(fibonacci(n)[0], fibonacci(n)[1])