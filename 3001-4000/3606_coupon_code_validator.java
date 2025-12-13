class Solution {
    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        HashSet<String> bizlines = new HashSet<>();
        Collections.addAll(bizlines, "electronics", "grocery", "pharmacy", "restaurant");
        List<String[]> temp = new ArrayList<>(); // [businessline, code]

        for(int i=0; i<code.length; i++) {
            // base case
            if(!isActive[i] || code[i].isEmpty())
                continue;
            if(isAlphanumeric(code[i]) && bizlines.contains(businessLine[i]) && isActive[i] ) {
                temp.add(new String[]{businessLine[i], code[i]});
            }
        }

        // sorting
        Collections.sort(temp, (a,b)-> {
            if(!a[0].equals(b[0])) {
                return a[0].compareTo(b[0]);
            } else {
                return a[1].compareTo(b[1]);
            }
        });

        List<String> ans = new ArrayList<>();

        for(String[] item : temp) {
            ans.add(item[1]);
        }

        return ans;

        
    }

    boolean isAlphanumeric(String str) {
        for(char ch : str.toCharArray()) {
            if(Character.isDigit(ch) || Character.isLetter(ch) || ch=='_')
                continue;
            return false;
        }
        return str.length() > 0;
        /*
        String pattern = "^[a-zA-Z0-9_]+$";
        return str.matches(pattern);
        */
    }
}