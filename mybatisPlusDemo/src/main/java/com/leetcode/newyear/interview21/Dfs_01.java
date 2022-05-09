package com.leetcode.newyear.interview21;

/**
 * @author fdn
 * @since 2022-01-28 22:08
 */
public class Dfs_01 {

    /**
     * 211. 添加与搜索单词 - 数据结构设计
     */
    class WordDictionary {

        Trie root;

        public WordDictionary() {
            root = new Trie();
        }

        public void addWord(String word) {
            root.insert(word);
        }

        public boolean search(String word) {
            return dfs(word, 0, root);
        }

        // 因为中间会遍历到 "." 这个字符，当遍历到 "." 时，需要对所有的 children 都进行继续匹配，所以用到深搜
        private boolean dfs(String word, int index, Trie node) {
            // 递归结束条件，遍历到了最后一个字符
            if (index == word.length()) {
                return node.isEnd();
            }

            //
            char c = word.charAt(index);
            if (c == '.') {
                for (int i = 0; i < 26; i++) {
                    Trie child  = node.getChildren()[i];
                    if (child != null && dfs(word, index + 1, child)) {
                        return true;
                    }
                    // 如果没匹配上，就匹配下一个 children (在下一个 for 循环中匹配)
                }
                return false;
            } else {
                int cIndex = c - 'a';
                Trie child = node.getChildren()[cIndex];
                return child != null && dfs(word, index + 1, child);
                // 如果没匹配上，返回 false
            }
        }
    }

    class Trie {
        private Trie[] children;
        private boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        // 只要有节点就成，并不需要往节点中具体插入什么字符，节点的 index 就代表了他所指代的字符
        public void insert(String s) {
            Trie node = this;
            int n = s.length();
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        public Trie[] getChildren() {
            return children;
        }

        public boolean isEnd() {
            return isEnd;
        }

    }
}
