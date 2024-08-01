T=int(input())
for t in range(T):
    st, term = map(int,input().split())
    print(f"#{t+1} {(st + term) % 24}")