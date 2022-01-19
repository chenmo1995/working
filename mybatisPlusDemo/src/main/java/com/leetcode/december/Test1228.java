package com.leetcode.december;

/**
 * @author fdn
 * @since 2021-12-28 14:25
 */
public class Test1228 {

    class Trie {
        private Trie[] children;
        private boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie node = this;
            int n = word.length();
            for (int i = 0; i < n; i++) {
                char c = word.charAt(i);
                int index = c - 'a';
                if (node.children[index] == null) {
                    // 子节点不存在，创建一个新的子节点
                    node.children[index] = new Trie();
                }
                // 指向子节点
                node = node.children[index];
            }
            // 把这条路径的 isEnd 设置为 true,这是尾部节点的 isEnd 属性 ，其他非尾部节点的 isEnd 属性还是 false
            node.isEnd = true;
        }

        public boolean search(String word) {
            // 找到 Trie 的符合他前缀的尾部节点
            // Trie 中不存在这个前缀串，或者存在这个串，但这个串只是一条路径中的一部分，还没到尾部节点
            // 找到这个串尾部节点
            Trie node = searchPrefix(word);
            return node != null && node.isEnd;
        }

        /**
         * 寻找字符串的尾部节点
         *
         * @param word
         * @return
         */
        private Trie searchPrefix(String word) {
            Trie node = this;
            int n = word.length();
            for (int i = 0; i < n; i++) {
                char c = word.charAt(i);
                int index = c - 'a';
                if (node.children[index] == null) {
                    return null;
                }
                // 这一个字符找到了对应的子节点，继续找下一个，指针移到子节点
                node = node.children[index];
            }
            // 找到字符串的尾部节点，返回
            return node;
        }

        // 存在节点即可
        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != null;
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
}
