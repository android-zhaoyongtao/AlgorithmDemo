package com.zyt.algorithmdemo;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * author : zhaoyongtao
 * e-mail : 285929232@qq.com
 * time   : 2019/2/20
 * desc   : 字典树demo
 * https://www.jianshu.com/p/43528463d572
 */
public class TrieDemo {
    class TrieNode {
        char c;
        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        boolean hasWord;

        public TrieNode() {
        }

        public TrieNode(char c) {
            this.c = c;
        }
    }

    class Trie {
        TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        void insert(String word) {
            if (word == null) {
                return;
            }
            TrieNode cur = root;
            HashMap<Character, TrieNode> curChildren = root.children;

            char[] wordAwwry = word.toCharArray();
            for (int i = 0; i < wordAwwry.length; i++) {
                char wc = wordAwwry[i];
                if (curChildren.containsKey(wc)) {
                    cur = curChildren.get(wc);
                } else {
                    cur = new TrieNode(wc);
                    curChildren.put(wc, cur);
                }
                curChildren = cur.children;

                if (i == wordAwwry.length - 1) {
                    cur.hasWord = true;
                }
            }
        }

        //搜索所有带这个前缀的单词并返回
        List<String> searchKeyWord(String word) {
            ArrayList<String> words = new ArrayList<>();
            TrieNode cur = root;
            StringBuilder curWord = new StringBuilder(String.valueOf(root.c));//没用了
            char[] sArray = word.toCharArray();
            for (int i = 0; i < sArray.length; i++) {
                char c = sArray[i];
                if (cur.children.containsKey(c)) {
                    cur = cur.children.get(c);
                    curWord.append(String.valueOf(cur.c));
                    if (i == sArray.length - 1) {//后面有一堆单词的情况下
                        getAllWord(words, word, cur);
                    }
                }
            }
            return words;
        }

        private void getAllWord(ArrayList<String> words, String wordPer, TrieNode cur) {
            if (cur.hasWord) {
                words.add(wordPer);
            }
            HashMap<Character, TrieNode> children = cur.children;
            if (children == null) {
                return;
            } else {
                Set<Map.Entry<Character, TrieNode>> entrySet = children.entrySet();
                for (Map.Entry<Character, TrieNode> entry : entrySet) {
                    getAllWord(words, wordPer + entry.getKey(), entry.getValue());
                }
            }
        }

        //搜索是否有这个单词
        boolean search(String word) {
            TrieNode tailChar = searchWordNodePos(word);
            if (tailChar != null && tailChar.hasWord) {
                return true;
            }

            return false;
        }

        //搜索前缀
        boolean startsWith(String prefix) {
            return searchWordNodePos(prefix) != null;
        }

        //搜索单词在字典树中的最后一个字符对应的节点。这个方法是搜索的关键
        private TrieNode searchWordNodePos(String s) {
            TrieNode cur = root;
            char[] sArray = s.toCharArray();
            for (int i = 0; i < sArray.length; i++) {
                char c = sArray[i];
                if (cur.children.containsKey(c)) {
                    cur = cur.children.get(c);
                } else {
                    return null;
                }
            }

            return cur;
        }
    }

}
