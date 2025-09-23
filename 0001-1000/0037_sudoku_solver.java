class Solution {

    private boolean[][] rows = new boolean[9][10];
    private boolean[][] cols = new boolean[9][10];
    private boolean[][] blocks = new boolean[9][10];
    public void solveSudoku(char[][] board) {
        
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                if(board[i][j]!='.') {
                    int num = board[i][j]-'0';
                    rows[i][num] = true;
                    cols[j][num] = true;
                    int blockIndex = (i/3)*3 + (j/3); // 0, 1, 2, 3
                    blocks[blockIndex][num] = true;
                }
            }
        }

        backtrack(board,0,0);

    }

    private boolean backtrack(char[][] board, int row, int col) {
        // base cases
        if(row==9) return true;
        if(col==9) return backtrack(board, row+1, 0);

        if(board[row][col]!='.')
            return backtrack(board, row, col+1);
        
        // backtracking

        for(int num=1; num<=9; num++) {
            int blockIndex = (row/3)*3 + (col/3);
            if(!rows[row][num] && !cols[col][num] && !blocks[blockIndex][num]) {
                board[row][col] = (char)(num+'0');
                rows[row][num] = cols[col][num] = blocks[blockIndex][num] = true;

                if(backtrack(board, row, col+1))
                    return true;
                
                // undo choices - backtrack
                board[row][col] = '.';
                rows[row][num] = cols[col][num] = blocks[blockIndex][num] = false;
            }
        }

        return false;
    }
}

// T.C. = 9^81
// S.C. = O(270) = O(1)
