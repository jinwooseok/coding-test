from collections import defaultdict

# 방향 벡터 (상, 하, 좌, 우)
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def is_valid(x, y, direction, grid, n):
    while 0 <= x < n and 0 <= y < n:
        if grid[x][y] != 0:
            return False
        x += dx[direction]
        y += dy[direction]
    return True

def place_wire(x, y, direction, grid, n):
    length = 0
    while 0 <= x < n and 0 <= y < n:
        grid[x][y] = 2
        length += 1
        x += dx[direction]
        y += dy[direction]
    return length

def remove_wire(x, y, direction, grid, n):
    while 0 <= x < n and 0 <= y < n:
        grid[x][y] = 0
        x += dx[direction]
        y += dy[direction]

def backtrack(index, core_count, wire_length, cores, grid, n):
    global max_cores_connected, min_wire_length
    if index == len(cores):
        if core_count > max_cores_connected:
            max_cores_connected = core_count
            min_wire_length = wire_length
        elif core_count == max_cores_connected:
            min_wire_length = min(min_wire_length, wire_length)
        return

    x, y = cores[index]
    connected = False

    for direction in range(4):
        nx, ny = x + dx[direction], y + dy[direction]
        if is_valid(nx, ny, direction, grid, n):
            length = place_wire(nx, ny, direction, grid, n)
            backtrack(index + 1, core_count + 1, wire_length + length, cores, grid, n)
            remove_wire(nx, ny, direction, grid, n)
            connected = True
    
    if not connected:
        backtrack(index + 1, core_count, wire_length, cores, grid, n)

def solve_exynos():
    input = '''3  
7    
0 0 1 0 0 0 0
0 0 1 0 0 0 0
0 0 0 0 0 1 0
0 0 0 0 0 0 0
1 1 0 1 0 0 0
0 1 0 0 0 0 0
0 0 0 0 0 0 0
9  
0 0 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0 1
1 0 0 0 0 0 0 0 0
0 0 0 1 0 0 0 0 0
0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 1 0 0
0 0 0 1 0 0 0 0 0
0 0 0 0 0 0 0 1 0
0 0 0 0 0 0 0 0 1
11 
0 0 1 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 1
0 0 0 1 0 0 0 0 1 0 0
0 1 0 1 1 0 0 0 1 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 1 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 1 0 0
0 0 0 0 0 0 1 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0'''

    data = input.split()
    
    index = 0
    T = int(data[index])
    index += 1
    
    results = []
    for test_case in range(1, T + 1):
        N = int(data[index])
        index += 1
        grid = []
        cores = []
        
        for i in range(N):
            row = list(map(int, data[index:index + N]))
            index += N
            grid.append(row)
            for j in range(N):
                if grid[i][j] == 1:
                    if i == 0 or i == N-1 or j == 0 or j == N-1:
                        continue
                    cores.append((i, j))
        
        global max_cores_connected, min_wire_length
        max_cores_connected = 0
        min_wire_length = float('inf')
        
        backtrack(0, 0, 0, cores, grid, N)
        
        results.append(f"#{test_case} {min_wire_length}")
    
    for result in results:
        print(result)

if __name__ == "__main__":
    solve_exynos()