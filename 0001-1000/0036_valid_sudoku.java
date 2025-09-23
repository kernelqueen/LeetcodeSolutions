class Solution {
    public boolean isValidSudoku(char[][] board) {
        
        HashSet<String> seen = new HashSet<>();

        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                char ch = board[i][j];
                if(ch=='.')
                    continue;
                String rowval = ch + "row" + i;
                String colval = ch + "col" + j;
                String blockval = ch + "block" + (i/3) + "-" + (j/3);
                // 0-0 0-1 0-2
                // 1-0 1-1 1-2
                // 2-0 2-1 2-2

                if(!seen.add(rowval) || !seen.add(colval) || !seen.add(blockval))
                return false;
            }
        }

        return true;

    }
}
