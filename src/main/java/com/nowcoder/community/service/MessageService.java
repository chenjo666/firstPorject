package com.nowcoder.community.service;

import com.nowcoder.community.dao.MessageMapper;
import com.nowcoder.community.pojo.Message;
import com.nowcoder.community.util.SensitiveWordsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private SensitiveWordsFilter sensitiveWordsFilter;
    public List<Message> getConversations(int userId, int offset, int limit) {
        return messageMapper.selectConversations(userId, offset, limit);
    }
    public int getConversationCount(int userId) {
        return messageMapper.selectConversationCount(userId);
    }
    public List<Message> getLetters(String conversationId, int offset, int limit) {
        return messageMapper.selectLetters(conversationId, offset, limit);
    }
    public int getLetterCount(String conversationId) {
        return messageMapper.selectLetterCount(conversationId);
    }
    public int getLetterUnreadCount(int userId, String conversationId) {
        return messageMapper.selectLetterUnreadCount(userId, conversationId);
    }

    public int addMessage(Message message) {
        message.setContent(HtmlUtils.htmlEscape(message.getContent()));
        message.setContent(sensitiveWordsFilter.filterText(message.getContent()));
        return messageMapper.insertMessage(message);
    }
    public int readMessage(List<Integer> ids) {
        return messageMapper.updateMessageStatus(ids, 1);
    }

    public Message getLatestNotice(int userId, String topic) {
        return messageMapper.selectLatestNotice(userId, topic);
    }
    public int getNoticeCount(int userId, String topic) {
        return messageMapper.selectNoticeCount(userId, topic);
    }
    public int getNoticeUnreadCount(int userId, String topic) {
        return messageMapper.selectNoticeUnreadCount(userId, topic);
    }

    public List<Message> getNotices(int userId, String topic, int offset, int limit) {
        return messageMapper.selectNotices(userId, topic, offset, limit);
    }
}
