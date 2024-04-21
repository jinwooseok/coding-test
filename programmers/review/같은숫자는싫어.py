'''
last : 바로 이전의 원소
바로 이전의 원소와 현재 원소의 값이 같을 경우 연속적으로 나타나는 값이므로 last의 값을 변경하는 경우를 생략할 수 있다.
last와 현재의 원소 값이 다른 경우 이전의 원소가 연속적인게 아니기 때문에 answer에 추가 후 last 값 변경
arr의 원소가 자연수인점을 활용해 -1을 초기값으로 설정함. 초기값을 정해둬서 추가 코드를 줄일 수 있었음
'''
def solution(arr):
    answer = []
    last = -1
    for i in range(len(arr)):
        if last != arr[i]:
            answer.append(arr[i])
            last = arr[i]
    return answer