package gol;

public class Game {

    private boolean[][] board = new boolean[15][20];

    public void markAlive(int x, int y) {
        board[y][x] = true;
    }

    public boolean isAlive(int x, int y) {
        return board[y][x];
    }

    public void toggle(int x, int y) {
        if (board[y][x]) {
            board[y][x] = false;
        }
        else {
            board[y][x] = true;
        }
    }

    public Integer getNeighbourCount(int x, int y) {
        Integer neighbourCount = 0;

        neighbourCount += countTopRow(x, y);
        neighbourCount += countLeftRight(x, y);
        neighbourCount += countBottomRow(x, y);

        return neighbourCount;
    }


    public void nextFrame() {
        int x = 0;
        int y = 0;

        boolean[][] currentFrameBoard = new boolean[15][20];

        for (boolean[] cell : board) {
            for (boolean value : cell) {
                if (y != 15) {
                    currentFrameBoard[y][x] = nextState(value, getNeighbourCount(x, y));
                    y++;
                }
            }
            x++;
            y = 0;
        }
        board = currentFrameBoard;
    }

    public void clear() {
        board = new boolean[15][20];
    }

    public boolean nextState(boolean isLiving, int neighborCount) {
        if (!isLiving && neighborCount == 3) {
            return true;
        }
        if (isLiving && neighborCount < 2 || neighborCount > 3) {
            return false;
        }
        return isLiving;
    }

    public Integer topRowExceptions(int x, int y) {
        Integer topRowCounter = 0;

        if (x == 0) {
            if (isAlive(x, y - 1)) {
                topRowCounter += 1;
            }
            if (isAlive(x + 1, y - 1)) {
                topRowCounter += 1;
            }
            return topRowCounter;
        }

        if (x == 19) {
            if (isAlive(x, y - 1)) {
                topRowCounter += 1;
            }
            if (isAlive(x - 1, y - 1)) {
                topRowCounter += 1;
            }
            return topRowCounter;
        }
        return 0;
    }

    public Integer countTopRow(int x, int y) {

        if (y == 0) {
            return 0;
        }

        Integer topRowCounter = 0;
        topRowCounter += topRowExceptions(x, y);
        if (topRowCounter != 0 || x == 0 || x == 19) {
            return topRowCounter;
        }

        if (isAlive(x - 1, y - 1)) {
            topRowCounter += 1;
        }
        if (isAlive(x, y - 1)) {
            topRowCounter += 1;
        }
        if (isAlive(x + 1, y - 1)) {
            topRowCounter += 1;
        }
        return topRowCounter;
    }

    public Integer bottomRowExceptions(int x, int y) {
        Integer counter = 0;

        if (x == 0) {
            if (isAlive(x, y + 1)) {
                counter += 1;
            }
            if (isAlive(x + 1, y + 1)) {
                counter += 1;
            }
            return counter;
        }

        if (x == 19) {
            if (isAlive(x, y + 1)) {
                counter += 1;
            }
            if (isAlive(x - 1, y + 1)) {
                counter += 1;
            }
            return counter;
        }
        return 0;
    }

    public Integer countLeftRight(int x, int y) {
        Integer counter = 0;

        if (x == 0 && isAlive(x + 1, y)) {
            counter++;
            return counter;
        } else if (x == 0) {
            return counter;
        }

        if (x == 19 && isAlive(x - 1, y)) {
            counter++;
            return counter;
        } else if (x == 19) {
            return counter;
        }

        if (isAlive(x + 1, y)) {
            counter++;
        }
        if (isAlive(x - 1, y)) {
            counter++;
        }
        return counter;
    }

    public Integer countBottomRow(int x, int y) {
        if (y == 14) {
            return 0;
        }

        Integer counter = 0;
        counter += bottomRowExceptions(x, y);
        if (counter != 0 || x == 0 || x == 19) {
            return counter;
        }

        if (isAlive(x - 1, y + 1)) {
            counter += 1;
        }
        if (isAlive(x, y + 1)) {
            counter += 1;
        }
        if (isAlive(x + 1, y + 1)) {
            counter += 1;
        }
        return counter;
    }
}
