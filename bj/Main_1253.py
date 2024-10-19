# N = int(input())
# arr = list(map(int, input().split()))
# arr.sort()

# visited = [False] * N  # "좋은 수"를 체크할 배열
# idx_dict = {}
# idx_dict[arr[0]] = 0

# # 정렬 후 숫자의 시작 인덱스를 기록
# for i in range(1, N):
#     if arr[i - 1] != arr[i]:
#         idx_dict[arr[i]] = i

# unique_list = list(idx_dict.keys())
# goods = set()

# # 두 수를 더한 후, 시작 부분부터 그 수들의 visited 배열에 true 표시
# for i in range(len(unique_list)):
#     for j in range(i, len(unique_list)):
#         key1 = unique_list[i]
#         key2 = unique_list[j]
        
#         # i == j인데 해당 값이 1개인 경우
#         if i == j and (idx_dict[key1] + 1 >= N or arr[idx_dict[key1]] != arr[idx_dict[key1] + 1]):
#             continue

#         ijSum = key1 + key2
        
#         # 이미 처리한 합이면 건너뜀
#         if ijSum in goods:
#             continue
        
#         goods.add(ijSum)

#         # 0 + 0일 때 처리
#         if key1 == 0 and key2 == 0:
#             startIdx = idx_dict[key2]
#             idx = startIdx
#             while idx < N and arr[idx] == key2:
#                 visited[idx] = True
#                 idx += 1
#             if idx - startIdx <= 2:
#                 visited[startIdx] = False
#                 visited[startIdx+1] = False
#             # 0이 2개 이상이어야 합이 0으로 좋은 수가 될 수 있음
#             continue

#         # 0 + key2 처리 (key1이 0일 때)
#         elif key1 == 0:
#             startIdx = idx_dict[key2]
#             idx = startIdx
#             while idx < N and arr[idx] == key2:
#                 visited[idx] = True
#                 idx += 1
#             # 만약 key2가 배열에 1개만 있으면 "좋은 수"가 아님
#             if idx - startIdx == 1:
#                 visited[startIdx] = False
#             continue

#         # key1 + key2 합이 arr에 존재하는 경우 처리
#         if ijSum in idx_dict.keys():
#             startIdx = idx_dict[ijSum]
#             while startIdx < N and arr[startIdx] == ijSum:
#                 visited[startIdx] = True
#                 startIdx += 1

# # "좋은 수"의 개수 계산
# cnt = sum(visited)
# print(cnt)

def count_good_numbers(N, A):
    A.sort()  # 수를 정렬
    good_count = 0  # 좋은 수의 개수
    
    for i in range(N):
        target = A[i]
        left, right = 0, N - 1  # 이분 탐색을 위한 두 포인터
        
        while left < right:
            if left == i:  # 왼쪽 포인터가 타겟 인덱스일 경우 건너뜀
                left += 1
                continue
            if right == i:  # 오른쪽 포인터가 타겟 인덱스일 경우 건너뜀
                right -= 1
                continue
            
            current_sum = A[left] + A[right]  # 현재 두 수의 합
            
            if current_sum == target:  # 현재 수가 두 수의 합과 같을 경우
                good_count += 1
                break  # 더 이상 체크할 필요 없음
            elif current_sum < target:
                left += 1  # 합이 작으면 왼쪽 포인터 증가
            else:
                right -= 1  # 합이 크면 오른쪽 포인터 감소
    
    return good_count

# 입력 받기
N = int(input())
A = list(map(int, input().split()))

# 좋은 수의 개수 출력
print(count_good_numbers(N, A))
