class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        
        StringBuilder sb  = new StringBuilder();

        for(String word : words) {
            int sum = 0;
            for(char ch : word.toCharArray()) {
                sum += weights[ch-'a'];
            }
            //System.out.println(sum);
            sum = sum%26;
            sb.append((char)('z' - sum));
        } 
        return sb.toString();

    }
}