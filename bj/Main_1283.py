#코드를 입력하세요.
'''
1. 왼쪽에서부터 오른쪽 순서로 단어의 첫 글자가
   이미 단축키로 지정되었는지 살펴보기
2. 모든 단어의 첫 글자가 이미 지정되어 있다면 왼쪽부터 차례대로 알파벳 보면서 단축키 지정
3. 어떠한 것도 단축키로 지정할 수 없다면 그냥 놔두기
4. 위의 규칙을 모든 옵션에 적용
'''
from collections import defaultdict
from collections import deque

N = int(input())
options = []
registered_key = set()
word_key = defaultdict(deque)
option_cnt = defaultdict(int)
for i in range(N):
    options.append(input().split())

# 단축키 사전 : 맵 2개 사용
# 단어의 첫번째 글자로 단축키 지정 가능한지 확인
for option in options:
    joined_option = " ".join(option)
    option_cnt[joined_option] += 1
    for i, word in enumerate(option):
        low_word = str.lower(word)
        if low_word[0] not in registered_key:
            registered_key.add(low_word[0])
            word_key[joined_option].append((i, 0))  # 이 옵션의 단축키는 이것입니다.
            option_cnt[joined_option] -= 1
            break
        # print(word_key)
# print(options)

    joined_option = " ".join(option)
    if option_cnt[joined_option] > 0:
        for j, word in enumerate(option):
            low_word = str.lower(word)
            flag = False
            for i in range(len(low_word)):
                if low_word[i] not in registered_key:
                    registered_key.add(low_word[i])
                    word_key[joined_option].append((j, i))
                    option_cnt[joined_option] -= 1
                    flag = True
                    break
            if flag:
                break
# print(word_key)
# print(registered_key)

for option in options:
    joined_option = " ".join(option)
    if len(word_key[joined_option]) == 0:
        print(joined_option)
        continue
    word_idx, char_idx = word_key[joined_option].popleft()
    char_list = list(option[word_idx])
    char_list[char_idx] = "[" + char_list[char_idx] + "]"
    option[word_idx] = "".join(char_list)
    print(" ".join(option))

