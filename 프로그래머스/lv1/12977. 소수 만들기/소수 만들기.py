def solution(nums):
    results = []
    for i in range(len(nums)):
        for j in range(i + 1, len(nums)):
            for k in range(j + 1, len(nums)):
                results.append(nums[i] + nums[j] + nums[k])
    results.sort()
    sosu = []
    for i in results:
        count = 0
        for j in range(1, i + 1):
            if i % j == 0:
                count += 1
        if count == 2:
            sosu.append(i)
    answer = len(sosu)
    return answer