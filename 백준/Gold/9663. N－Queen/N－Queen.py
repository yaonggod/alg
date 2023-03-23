n = int(input())

def promising(i):
    for k in range(i):
        if col[k] == col[i] or abs(col[i] - col[k]) == i - k:
            # print(k, i, col[k], col[i])
            return False
    return True

count = 0
def n_queens(i):
    global count
    if i == n:
        # print(col)
        count += 1
    else:
        for j in range(n):
            col[i] = j
            if promising(i):
                n_queens(i + 1)
                
col = [0] * n
n_queens(0)
print(count)