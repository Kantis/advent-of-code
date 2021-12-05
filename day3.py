import numpy as np

input = np.transpose(np.array(
    [
        [ 0,0,1,0,0, ],
        [ 1,1,1,1,0, ],
        [ 1,0,1,1,0, ],
        [ 1,0,1,1,1, ],
        [ 1,0,1,0,1, ],
        [ 0,1,1,1,1, ],
        [ 0,0,1,1,1, ],
        [ 1,1,1,0,0, ],
        [ 1,0,0,0,0, ],
        [ 1,1,0,0,1, ],
        [ 0,0,0,1,0, ],
        [ 0,1,0,1,0, ],
    ]
))

inverted = np.absolute(np.subtract(input, np.ones((5,12), dtype=int)))

ones = np.ones((12,1), int)

count_ones = np.matmul(input, ones)
count_zeroes = np.matmul(inverted, ones)


print(count_ones)
print(count_zeroes)

gamma = [1 if count_ones[x] > count_zeroes[x] else 0 for x in range(len(count_ones))]
epsilon = [0 if gamma[x] == 1 else 1 for x in range(len(gamma))]
print(gamma, epsilon)