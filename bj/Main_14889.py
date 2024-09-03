'''
n/2의 팀으로 사람들을 나눔
i,j는 인접행렬에서 시너지가 있음. 더해지는 능력치.
팀의 능력치 = 모든 쌍의 능력치의 합. ij와 ji는 다름

햄버거처럼 풀면 될듯
비트마스킹 1팀과 0팀으로 구분
능력치 차이가 최소. 
메모리제이션? 팀의 번호는 상관이 없긴하니까 해두자
일단 팀은 2개로 나누고 인원도 고정이므로 조합도 1/2만 하면 됨.
능력치를 구할땐 순열
'''

#입력 부분
import sys
N = int(sys.stdin.readline())
adj_arr = [list(map(int, input().split())) for _ in range(N)]

#절반씩 나눔+1팀과 2팀은 상관없기 때문에 조합의 1/2만큼만 구해도 똑같음
comb_cnt_div_2 = 1
for i in range(N//2+1,N+1):
    comb_cnt_div_2*=i
for i in range(1, N//2+1):
    comb_cnt_div_2//=i
#print(comb_cnt_div_2)
#조합 함수. result에 add.(set임)
def comb(adj_arr, result, visited, start, depth, R):
    if len(result)>=comb_cnt_div_2//2: #딱 절반 조합돌렸다는 뜻
        return
    if depth == R:
        result.add(visited)
        return
    if start>=N:
        return
    comb(adj_arr, result, visited | (1<<start), start+1, depth+1, R)
    comb(adj_arr, result, visited, start+1, depth, R)
#팀 별 차이를 구하는 함수. team1과 team2를 각각 구함.
def getDiff(adj_arr, visited):
    team1 = [0]*(N//2)
    team2 = [0]*(N//2)
    team1_i, team2_i = 0, 0
    for i in range(N):
        #1인 팀
        if (visited & (1<<i)):
            team1[team1_i]=i
            team1_i+=1
        #0인 팀
        else:
            team2[team2_i]=i
            team2_i+=1
    
    return team_sum(adj_arr, team1, team2)
#팀별로 합 구하는 함수
def team_sum(adj_arr, team1, team2):
    sum1, sum2 = 0, 0
    for i in range(N//2):
        for j in range(i+1,N//2):
            sum1+=adj_arr[team1[i]][team1[j]]+adj_arr[team1[j]][team1[i]]
            sum2+=adj_arr[team2[i]][team2[j]]+adj_arr[team2[j]][team2[i]]
    return abs(sum1-sum2)

result = set()
comb(adj_arr, result, 0, 0, 0, N/2)
minDiff = float('inf')
for res in result:
    #print(bin(res))
    minDiff=min(minDiff,getDiff(adj_arr, res))

print(minDiff)
    