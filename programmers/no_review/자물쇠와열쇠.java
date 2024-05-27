import java.util.*;

class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int N = lock.length;
        int M = key.length;
        int newSize = M * 2 + N;

        int[][] newLock = new int[newSize][newSize];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newLock[i + M][j + M] = lock[i][j];
            }
        }

        for (int rotation = 0; rotation < 4; rotation++) {
            key = rotateArray(key);
            for (int x = 0; x < newSize - M; x++) {
                for (int y = 0; y < newSize - M; y++) {
                    if (compare(key, newLock, x, y, N, M)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // 90도씩 회전하는 메서드
    int[][] rotateArray(int[][] key) {
        int N = key.length;
        int[][] rotatedKey = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                rotatedKey[j][N - 1 - i] = key[i][j];
            }
        }
        return rotatedKey;
    }

    // 대조 메서드
    boolean compare(int[][] key, int[][] newLock, int startX, int startY, int lockSize, int keySize) {
        int[][] lockCopy = new int[newLock.length][newLock.length];
        for (int i = 0; i < newLock.length; i++) {
            for (int j = 0; j < newLock[i].length; j++) {
                lockCopy[i][j] = newLock[i][j];
            }
        }

        for (int i = 0; i < keySize; i++) {
            for (int j = 0; j < keySize; j++) {
                lockCopy[startX + i][startY + j] += key[i][j];
            }
        }

        for (int i = 0; i < lockSize; i++) {
            for (int j = 0; j < lockSize; j++) {
                if (lockCopy[i + keySize][j + keySize] != 1) {
                    return false;
                }
            }
        }

        return true;
    }
}
