t = int(input())
for _ in range(t):

    n = int(input())

    op = [' ', '+', '-']

    def cal(result):
        total = 0
        prev = (1, 1)
        for i in range(n - 1):
            if result[i] == '+':
                total += prev[0] * prev[1]
                prev = (1, i + 2)
            elif result[i] == '-':
                total += prev[0] * prev[1]
                prev = (-1, i + 2)
            else:
                x = prev[0]
                y = prev[1] * 10 + (i + 2)
                prev = (x, y)
        total += prev[0] * prev[1]
        return total

    def printcal(result):
        s = "1"
        for i in range(n - 1):
            s += result[i]
            s += str(i + 2) 
        print(s)

    def backtracking(result):
        if len(result) == n - 1:
            if cal(result) == 0:
                printcal(result)
            return

        for o in op:
            result.append(o)
            backtracking(result)
            result.pop()

    backtracking([])
    print()