def solution(n, t, m, timetable):
    answer = ''
    #못타면 안됨. 더 일찍 가서라도 타야함.
    bus_map = {}
    start = 9*60+0
    for i in range(n):        
        bus_map[start+i*t]=[]

    timetable.sort()
    while timetable:
        time = timetable.pop(0)
        crew=int(time[:2])*60+int(time[3:])
        for bus in bus_map.keys():
            if bus>=crew and len(bus_map[bus]) < m:
                bus_map[bus].append(crew)
                break
                
    last_time=list(bus_map.keys())[-1]
    if len(bus_map[last_time])<m:
        return "{:02d}:{:02d}".format(last_time//60,last_time%60)
    else:
        return "{:02d}:{:02d}".format((bus_map[last_time][-1]-1)//60,(bus_map[last_time][-1]-1)%60)