from collections import deque
import sys
# sys.stdin = open('AC.txt')
input = sys.stdin.readline
t = int(input())
for _ in range(t):
    error = False
    func = input().strip()
    n = int(input())
    lst_original = list(input().strip().strip('[').strip(']').split(','))
    lst = []
    for i in lst_original:
        try:
            lst.append(int(i))
        except:
            pass

    queue = deque(lst)
    answer = []
    dir = 1
    for letter in func:
        if letter == 'R':
            dir *= -1
        elif letter == 'D' and len(queue) == 0:
            error = True
            break
        else:
            if dir == 1:
                queue.popleft()
            elif dir == -1:
                queue.pop()
    if dir == 1:
        answer = list(queue)
    if dir == -1:
        answer = list(queue)[::-1]
                
    if error:
        print('error')
    else:
        if answer == []:
            print('[]')
        else:
            print('[', end = '')
            for i in range(len(answer) - 1):
                print(answer[i], ',', sep = '', end = '')
            print(answer[-1], ']', sep = '')