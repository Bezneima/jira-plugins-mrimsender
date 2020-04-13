package ru.mail.jira.plugins.mrimsender.protocol;

import com.atlassian.jira.user.ApplicationUser;
import org.apache.commons.lang3.StringUtils;
import ru.mail.jira.plugins.mrimsender.configuration.UserData;

public class MrimServiceImpl implements MrimService {
    private final UserData userData;
    private final JiraMessageQueueProcessor jiraMessageQueueProcessor;

    public MrimServiceImpl(JiraMessageQueueProcessor jiraMessageQueueProcessor, UserData userData) {
        this.jiraMessageQueueProcessor = jiraMessageQueueProcessor;
        this.userData = userData;
    }

    @Override
    public boolean sendMessage(ApplicationUser user, String message) {
        if (user == null || StringUtils.isEmpty(message))
            throw new IllegalArgumentException("User and message must be specified");

        String mrimLogin = userData.getMrimLogin(user);
        if (user.isActive() && !StringUtils.isBlank(mrimLogin) && userData.isEnabled(user)) {
            sendMessage(mrimLogin, message);
            return true;
        }
        return false;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        jiraMessageQueueProcessor.sendMessage(chatId, message, null);
    }
}
