n = int(input())
meeting = [list(map(int, input().split())) for _ in range(n)]
meeting.sort(key = lambda x : (x[1], x[0]))

meetings = 0
end = 0
for i in range(n):
    if meeting[i][0] >= end:
        end = meeting[i][1]
        meetings += 1
print(meetings)