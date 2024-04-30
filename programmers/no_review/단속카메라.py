def solution(routes):
    answer = 1
    routes.sort()
    camera = routes[0][1]
    for i in range(1, len(routes)):
        if routes[i][0] <= camera:
            camera = min(camera, routes[i][1])
            continue
        else:
            camera = routes[i][1]
            answer+=1
    return answer