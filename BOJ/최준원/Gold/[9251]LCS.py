a = input()
b = input()

dp = [[0]*(len(b)+1) for _ in range(len(a)+1)]

for i in range(len(b)):
    for j in range(len(a)):
        if a[j] == b[i]:
            dp[i+1][j+1] += dp[i][j]+1
        else:
            dp[i+1][j+1] = max(dp[i][j+1],dp[i+1][j])
for i in range(len(b)):
    for j in range(len(a)):
        print(dp[i][j], end=' ')
    print("")
'''
abcdaf
acbcf
'''