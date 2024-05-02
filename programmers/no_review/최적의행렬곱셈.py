def solution(matrix_sizes):
    answer = 0
    n = len(matrix_sizes)
    matrix = [[0] * n for _ in range(n)]

    for diagonal in range(0, n):  # 대각선
        for j in range(0, n - diagonal):  # 행
            i = j + diagonal  # 열
            if i == j:
                matrix[j][i]=0
            else:
                matrix[j][i] = float('inf')
                for k in range(j,i):
                    matrix[j][i] = min(matrix[j][i], matrix[j][k] + matrix[k+1][i] + matrix_sizes[j][0] * matrix_sizes[k][1] * matrix_sizes[i][1])

    answer = matrix[0][-1]
    return answer