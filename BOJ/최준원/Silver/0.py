board = [[11, 9, 8, 12], [2, 15, 4, 14], [1, 10, 16, 3], [13, 7, 5, 6]]

def solution(n, board):
    map = {}
    result = 0
    for i in range(n):
        for j in range(n):
            map[board[i][j]] = (i, j)

    x1, y1 = 0, 0
    result = []
    for number in range(1, n*n + 1):
        (x2, y2) = map.get(number)
        tmp = []
        tmp.append(abs(x1 - x2) + abs(y1 - y2))
        tmp.append(abs(x1 - (x2 - n)) + abs(y1 - y2))
        tmp.append(abs(x1 - (x2 - n)) + abs(y1 - (y2 + n)))
        tmp.append(abs(x1 - x2) + abs(y1 - (y2 - n)))
        tmp.append(abs(x1 - (x2 - n)) + abs(y1 - (y2 - n)))
        tmp.append(abs(x1 - (x2 + n)) + abs(y1 - (y2 - n)))
        tmp.append(abs(x1 - (x2 + n)) + abs(y1 - y2))
        tmp.append(abs(x1 - x2) + abs(y1 - (y2 + n)))
        tmp.append(abs(x1 - (x2 + n)) + abs(y1 - (y2 + n)))
        result.append(min(tmp)+1)
        x1, y1 = x2, y2
    print(result)
    print(sum(result))
        
solution(4, board)