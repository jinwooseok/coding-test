def solution(lines):
    answer = 0
    #응답완료시간, 처리시간, 최대처리개수(동시에 처리할 수 있는 개수)를구해라 - 최대 2000개 N^2 시간복잡도까지는 가능하륻ㅅ
    #2.002 ~ 4.001 5.001~7.000
    #마지막시간부터 1초 사이에 체크하기
    max_traffic = 0
    #경우의 수 > 시작지점이 사이에 있기 or 끝지점이 사이에 있기, 시작지점이 사이전+끝지점이 사이후
    pointer=0
    for i, log in enumerate(lines):
        date1, time1, term1=log.split(" ")
        start_term=to_second(time1)
        end_term=start_term+1
        traffic=0
        for log2 in lines[i:]:
            date2, time2, term2=log2.split(" ")
            complete_time=to_second(time2)
            start_time=complete_time-float(term2[:-1])+0.001
            if start_term<=start_time<end_term:
                traffic+=1
                continue
            if start_term<=complete_time<end_term:
                traffic+=1
                continue
            if start_time<=start_term and complete_time>end_term:
                traffic+=1
                continue
        if traffic > max_traffic:
            max_traffic = traffic 
    return max_traffic

def to_second(time_string):
    return int(time_string[:2])*3600+int(time_string[3:5])*60+float(time_string[6:])
