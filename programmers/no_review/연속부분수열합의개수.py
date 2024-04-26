def solution(elements):
    answer = []
    circle_elements=elements+elements
    for i in range(1,len(elements)+1):
        for j in range(len(elements)):
            answer.append(sum(circle_elements[j:j+i]))
    return len(set(answer))
