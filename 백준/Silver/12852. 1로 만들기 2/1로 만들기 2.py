n = int(input())

if n == 1:
    print(0)
    print(1)

else:
    dp = [[] for _ in range(n + 1)]
    if n >= 3:   
        dp[3].append(3)
        dp[2].append(2)
    elif n >= 2:
        dp[2].append(2)
    # print(dp)

    for i in range(2, n + 1):
        if len(dp[i]):
            if i * 3 < n + 1 and (len(dp[i * 3]) == 0 or len(dp[i * 3]) > len(dp[i] + [3])):
                dp[i * 3] = dp[i] + [3]
            if i * 2 < n + 1 and (len(dp[i * 2]) == 0 or len(dp[i * 2]) > len(dp[i] + [2])):
                dp[i * 2] = dp[i] + [2]
            if i + 1 < n + 1 and (len(dp[i + 1]) == 0 or len(dp[i + 1]) > len(dp[i] + [1])):
                dp[i + 1] = dp[i] + [1]     

    # print(dp)
    print(len(dp[n]))
    print(n, end = " ")
    x = n
    for i in range(len(dp[n]) - 1, -1, -1):
        if dp[n][i] == 1:
            x -= 1
            print(x, end = " ")
        elif dp[n][i] == 2:
            x //= 2
            print(x, end = " ")
        elif dp[n][i] == 3:
            x //= 3
            print(x, end = " ")