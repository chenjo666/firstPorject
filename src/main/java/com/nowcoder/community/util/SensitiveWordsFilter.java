package com.nowcoder.community.util;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class SensitiveWordsFilter {

    private static final Logger logger = LoggerFactory.getLogger(SensitiveWordsFilter.class);
    // 准备的敏感词文件名称
    private static final String SENSITIVE_WORDS_NAME = "sensitive-words.txt";
    // 准备替换敏感词的内容
    private static final String SENSITIVE_WORDS_REPLACE = "***";

    // 前缀树根节点
    private TrieNode root = new TrieNode();

    // 初始化前缀树，使用 @PostConstruct 注解在类注入完成时便调用 init 方法
    @PostConstruct
    public void init() {
        try (
                InputStream is = this.getClass().getClassLoader().getResourceAsStream(SENSITIVE_WORDS_NAME);
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        ) {
            String sensitiveWord = "";
            while ((sensitiveWord = reader.readLine()) != null) {
                // 添加到前缀树
                this.addSensitiveWord(sensitiveWord);
            }
        } catch (IOException e) {
            logger.error("加载敏感词文件失败：" + e.getMessage());
        }
    }

    // 将敏感词加入进前缀树中
    private void addSensitiveWord(String sensitiveWord) {
        // 当前字节
        TrieNode curNode = root;
        for (int i = 0; i < sensitiveWord.length(); i++) {
            // 获取子节点
            TrieNode subNode = curNode.getSubNode(sensitiveWord.charAt(i));
            // 子节点为空，则新建子节点
            if (subNode == null) {
                Character c = sensitiveWord.charAt(i);
                subNode = new TrieNode();
                curNode.addSubNode(c, subNode);
            }
            // 指向子节点
            curNode = subNode;
            // 叶子节点
            if (i == sensitiveWord.length() - 1) {
                curNode.setLeafNode(true);
            }
        }
    }
    // 文本过滤
    public String filterText(String text) {
        if (StringUtils.isBlank(text)) {
            return null;
        }

        // 指向当前节点
        TrieNode curNode = root;
        // 指向文本子串开始
        int start = 0;
        // 指向文本子窜末尾
        int end = 0;
        // 最终结果
        StringBuilder sb = new StringBuilder();
        while (start < text.length()) {
            char c = text.charAt(end);
            // 特殊符号处理
            if (!CharUtils.isAsciiAlphanumeric(c) && (c < 0x2E80 || c > 0x9FFF)) {
                if (curNode == root) {
                    sb.append(c);
                    start++;
                }
                end++;
                continue;
            }
            // 获取下级节点
            curNode = curNode.getSubNode(c);
            if (curNode == null) { // 当前 start 不为敏感词
                sb.append(text.charAt(start));
                start = ++end;
                curNode = root;
            } else if (curNode.isLeafNode()) { // 当前 text[start, ..., end] 为敏感词
                sb.append(SENSITIVE_WORDS_REPLACE);
                start = ++end;
                curNode = root;
            } else { // 为中间节点
                if (end == text.length() - 1) {
                    sb.append(text.charAt(start));
                    end = ++start;
                    curNode = root;
                } else {
                    end++;
                }
            }
        }
        return sb.toString();
    }
    // 前缀树节点
    private class TrieNode {
        // 子节点
        private Map<Character, TrieNode> subNodes = new HashMap<>();
        // 子节点是否为前缀树的叶子节点
        private boolean isLeafNode = false;

        public boolean isLeafNode() {
            return isLeafNode;
        }

        public void setLeafNode(boolean leafNode) {
            isLeafNode = leafNode;
        }

        public void addSubNode(Character c, TrieNode node) {
            subNodes.put(c, node);
        }

        public TrieNode getSubNode(Character c) {
            return subNodes.get(c);
        }
    }
}
