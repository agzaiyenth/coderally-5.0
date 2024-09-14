def tallest_tower(cubes):
    n = len(cubes)
    dp = [0] * n
    parent = [-1] * n

    for i in range(n):
        dp[i] = 1  # Initialize with the height of one cube
        for j in range(i):
            if cubes[j][5] == cubes[i][4] and dp[j] + 1 > dp[i]:
                dp[i] = dp[j] + 1
                parent[i] = j

    max_height = max(dp)
    index = dp.index(max_height)
    tower = []

    while index != -1:
        tower.append(index + 1)
        index = parent[index]

    tower.reverse()
    return max_height, tower

def process_input(input_data):
    lines = input_data.strip().split('\n')
    i = 0
    test_case = 1
    results = []

    while i < len(lines):
        n = int(lines[i])
        if n == 0:
            break
        i += 1
        cubes = []
        for _ in range(n):
            cube = list(map(int, lines[i].split()))
            cubes.append(cube)
            i += 1
        height, tower = tallest_tower(cubes)
        results.append(f"Case #{test_case}")
        results.append(str(height))
        for cube in tower:
            results.append(f"{cube} {['front', 'back', 'left', 'right', 'top', 'bottom'][cubes[cube-1].index(cubes[cube-1][4])]}")
        results.append("")
        test_case += 1

    return "\n".join(results)

# Sample input
input_data = """1
1 2 2 2 1 2
5
1 5 1 0 3 6 5
5 7 3 2 1 9
6 6 2 2 4 4
10 9 8 7 6 5
1 2 3 3 2 1
0"""

# Function call
output = process_input(input_data)
print(output)
