package ru.mail.jira.plugins.myteam.protocol.events;

import lombok.Getter;
import ru.mail.jira.plugins.myteam.myteam.dto.InlineKeyboardMarkupButton;

import java.util.List;

@Getter
public class JiraNotifyEvent implements Event{
    private final String chatId;
    private final String message;
    private final List<List<InlineKeyboardMarkupButton>> buttons;

    public JiraNotifyEvent(String chatId, String message, List<List<InlineKeyboardMarkupButton>> buttons) {
        this.chatId = chatId;
        this.message = message;
        this.buttons = buttons;
    }
}