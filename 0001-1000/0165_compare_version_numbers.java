class Solution {
    public int compareVersion(String version1, String version2) {
        // T.C. = O(Math.max(n, m))
        // S.C. = O(n+m)
        String arr1[] = version1.split("\\.");
        String arr2[] = version2.split("\\.");

        int n = Math.max(arr1.length, arr2.length);

        for(int i=0; i<n; i++) {
            int a = i<arr1.length ? Integer.parseInt(arr1[i]) : 0;
            int b = i<arr2.length ? Integer.parseInt(arr2[i]) : 0;

            if(a!=b) {
                return a>b ? 1 : -1;
            }
        }

        return 0;

    }
}

/*
Version String: 1.0.0 = 3
Version String: 1.0.0.3 = 4
Rules to compare:
 - compare from left to right
 - ignore leading zeros of revisions
 - compare revision value of two versions
 - missing revisions are considered 0s

v1 = "1.2"
v2 = "1.10"
return -1

v1 = "1.01"
v2 = "1.001"
return 0

v1 = "1.0" = 2
v2 = "1.0.0.0" = 4
return 0

v1 = "1.0.0.1"
v2 = "1.0.0.0"
return 1

*/ 