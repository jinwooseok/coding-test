def solution(gems):
    #중복제거
    gem_dic = {}
    for i, gem in enumerate(gems):
        gem_dic[gem] = 0
    N = len(gem_dic.keys())
    gem_set = set()
    gem_set.add(gems[0])
    start, end = 0, 0
    min_size = [0, len(gems)]
    #gem_Set의 길이가 N이 되면 return
    while end<len(gems):
        #현재 젬셋에 해당 보석이 없으면 end 확장
        gem_dic[gems[end]]+=1
        if gems[end] not in gem_set:
            gem_set.add(gems[end])
        #반면에 존재한다면 존재하는 인덱스 파악. 끝부터 제거
        else:
            while end>start:
                if gem_dic[gems[start]] > 1:
                    gem_dic[gems[start]]-=1
                    start+=1
                else:
                    break
        
        if len(gem_set)==N:
            if min_size[1]-min_size[0] > end-start:
                min_size=[start,end]
            elif min_size[1]-min_size[0] == end-start and min_size[0] > start:
                min_size=[start,end]
        end+=1
    
    return [min_size[0]+1, min_size[1]+1]