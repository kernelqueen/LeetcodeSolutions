class Solution {
    public String decodeCiphertext(String encoded, int rows) {
        // O(n) + O(n)
        // O(n)
        int cols = encoded.length()/rows;

        char mat[][] = new char[rows][cols];
        int i=0, j=0;
        
        for(int ind=0; ind<encoded.length(); ind++) {
            mat[i][j] = encoded.charAt(ind);
            if(j < cols-1) {
                j++;
            }else {
                i++;
                j=0;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(j=0; j<cols; j++) {
            int slant = 0;
            for(i=0; i<rows; i++) {
                if(j+slant < cols) {
                    sb.append(mat[i][j+slant]);
                    slant++;
                }
            }
        }
        // trailing spaces remove
        return sb.toString().stripTrailing();
        
    }
}