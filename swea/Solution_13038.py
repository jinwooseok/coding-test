T = int(input())
for t in range(T):
    n = int(input())
    dateArr = list(map(int, input().split()))
    
    classDate = []
    classCnt = 0

    for i in range(7):
        if dateArr[i] == 1:
            classCnt += 1
            classDate.append(i)
    
    min_days = float('inf')
    
    for start_day in range(7):
        if dateArr[start_day] == 1:
            days = 0
            classes_taken = 0
            day = start_day
            
            while classes_taken < n:
                if dateArr[day % 7] == 1:
                    classes_taken += 1
                days += 1
                day += 1
            
            min_days = min(min_days, days)
    
    print(f"#{t+1} {min_days}")