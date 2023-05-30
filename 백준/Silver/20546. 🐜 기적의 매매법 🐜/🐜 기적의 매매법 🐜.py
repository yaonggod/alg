import sys
cash = int(input())
price = list(map(int, input().split()))
jh = 0
sm = 0
jh_cash = cash
sm_cash = cash

# 준현
for i in range(14):
    if jh_cash >= price[i]:
        jh += jh_cash // price[i]
        jh_cash = jh_cash - jh * price[i]

jh_cash += jh * price[13]

# 성민
prev = 0
count = 0
for i in range(1, 14):
    # 상승중
    if price[i - 1] < price[i]:
        # 상승 유지중
        if prev == 1:
            count += 1
        # 초기화
        else:
            prev = 1
            count = 1
    # 하락중
    elif price[i - 1] > price[i]:
        if prev == -1:
            count += 1
        else:
            prev = -1
            count = 1
    # 가격 동일
    else:
        prev = 0
        count = 1
    # 3일연속
    if count >= 3:
        # 하락
        if prev == -1:
            # 전량 매수
            add = sm_cash // price[i]
            sm += add
            sm_cash = sm_cash - add * price[i]
        elif prev == 1:
            # 전량 매도
            sm_cash += sm * price[i]
            sm = 0
   

sm_cash += sm * price[13]
 
if jh_cash > sm_cash:
    print('BNP')
elif jh_cash < sm_cash:
    print('TIMING')
else:
    print('SAMESAME') 
