from itertools import combinations
n, k = map(int, input().split())
n_list = list(str(n))
visited = []
answer = -1

def backtracking(c):
    global answer
    if c == k:
        # print(c, n_list)
        if int(''.join(n_list)) > answer:
            answer = int(''.join(n_list))
    else:
        for comb in list(combinations(range(len(n_list)), 2)):
            # print(c, n_list)
            n_list[comb[0]], n_list[comb[1]] = n_list[comb[1]], n_list[comb[0]]
            if n_list[0] != '0':
                if (c, int(''.join(n_list))) not in visited:
                    backtracking(c + 1)
                    visited.append((c, int(''.join(n_list))))
            n_list[comb[0]], n_list[comb[1]] = n_list[comb[1]], n_list[comb[0]]

backtracking(0)
print(answer)
    