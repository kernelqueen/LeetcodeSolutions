class Solution {
    public int[] stringIndices(String[] words, String[] query) {
        Trie trie = new Trie();
        int index = -1, smallest = Integer.MAX_VALUE;

        for (int i = 0; i < words.length; i++) {
            trie.insert(words[i], i);
            if (words[i].length() < smallest) {
                smallest = words[i].length();
                index = i;
            }
        }

        int[] ans = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            int ind = trie.prefix(query[i]);
            ans[i] = (ind == -1) ? index : ind;
        }
        return ans;
    }
}

class TrieNode {
    int index;
    int len;
    TrieNode[] children;

    TrieNode() {
        index = -1;
        len = Integer.MAX_VALUE;
        children = new TrieNode[26];
    }
}

class Trie {
    TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    void insert(String str, int ind) {
        TrieNode node = root;
        int strLen = str.length();

        for (int i = strLen - 1; i >= 0; i--) {         // ← right to left
            int ch = str.charAt(i) - 'a';
            if (node.children[ch] == null)
                node.children[ch] = new TrieNode();
            node = node.children[ch];

            if (strLen < node.len) {
                node.len = strLen;
                node.index = ind;
            }
        }
    }

    int prefix(String query) {
        TrieNode node = root;
        int result = -1;

        for (int i = query.length() - 1; i >= 0; i--) { // ← right to left
            int ch = query.charAt(i) - 'a';
            if (node.children[ch] == null) break;
            node = node.children[ch];
            result = node.index;
        }

        return result;
    }
}