T=int(input())
for t in range(T):
    answer = 0
    N = int(input())
    print(f"#{t+1} {((-1)**(N+1))*((N-1)//2+1)}")