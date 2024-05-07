def solution(cookie):
    #누적합
    max_cookie = 0
    N = len(cookie)
    # 누적합 계산
    co = [0] * (N + 1)
    for i in range(1, N + 1):
        co[i] = co[i - 1] + cookie[i - 1]
    co = co[1:]
    for m in range(0, N):
        l_start, l_end, r_start, r_end = m, m, m + 1, m + 1
        
        # 바구니 범위 조정
        while l_start >= 0 and r_end <= N-1:
            if l_start == 0:
                l_sum = co[l_end]
            else:
                l_sum = co[l_end] - co[l_start-1]
            r_sum = co[r_end] - co[r_start-1]
            
            if l_sum < r_sum:
                l_start -= 1
            elif l_sum > r_sum:
                r_end += 1
            else:
                max_cookie = max(max_cookie, l_sum)
                l_start -= 1
                r_end += 1
    return max_cookie