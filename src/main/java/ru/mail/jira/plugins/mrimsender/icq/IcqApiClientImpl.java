package ru.mail.jira.plugins.mrimsender.icq;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.codehaus.jackson.map.ObjectMapper;
import ru.mail.jira.plugins.mrimsender.configuration.PluginData;
import ru.mail.jira.plugins.mrimsender.icq.dto.FetchResponseDto;
import ru.mail.jira.plugins.mrimsender.icq.dto.InlineKeyboardMarkupButton;
import ru.mail.jira.plugins.mrimsender.icq.dto.MessageResponse;

import java.io.IOException;
import java.util.List;

public class IcqApiClientImpl implements IcqApiClient {
    private String apiToken;
    private String botApiUrl;
    private final ObjectMapper objectMapper;
    private final PluginData pluginData;

    public IcqApiClientImpl(PluginData pluginData) {
        this.objectMapper = new ObjectMapper();
        this.pluginData = pluginData;
        this.apiToken = pluginData.getToken();
        this.botApiUrl = pluginData.getBotApiUrl();
    }

    @Override
    public void updateSettings() {
        this.apiToken = pluginData.getToken();
        this.botApiUrl = pluginData.getBotApiUrl();
    }

    @Override
    public HttpResponse<MessageResponse> sendMessageText(String chatId, String text, List<List<InlineKeyboardMarkupButton>> inlineKeyboardMarkup) throws UnirestException, IOException {
        if (inlineKeyboardMarkup == null)
            return Unirest.get(botApiUrl + "/messages/sendText")
                          .queryString("token", apiToken)
                          .queryString("chatId", chatId)
                          .queryString("text", text)
                          .asObject(MessageResponse.class);
        return Unirest.get(botApiUrl + "/messages/sendText")
                      .queryString("token", apiToken)
                      .queryString("chatId", chatId)
                      .queryString("text", text)
                      .queryString("inlineKeyboardMarkup", objectMapper.writeValueAsString(inlineKeyboardMarkup))
                      .asObject(MessageResponse.class);
    }

    @Override
    public HttpResponse<MessageResponse> sendMessageText(String chatId, String text) throws IOException, UnirestException {
        return sendMessageText(chatId, text, null);
    }

    @Override
    public HttpResponse<FetchResponseDto> getEvents(long lastEventId, long pollTime) throws UnirestException {
        return Unirest.get(botApiUrl + "/events/get")
                      .queryString("token", apiToken)
                      .queryString("lastEventId", lastEventId)
                      .queryString("pollTime", pollTime)
                      .asObject(FetchResponseDto.class);
    }

    @Override
    public HttpResponse<JsonNode> answerCallbackQuery(String queryId, String text, boolean showAlert, String url) throws UnirestException {
        return Unirest.get(botApiUrl + "/messages/answerCallbackQuery")
                      .queryString("token", apiToken)
                      .queryString("queryId", queryId)
                      .queryString("text", text)
                      .queryString("showAlert", showAlert)
                      .queryString("url", url)
                      .asJson();
    }

    @Override
    public HttpResponse<JsonNode> answerCallbackQuery(String queryId) throws UnirestException {
        return answerCallbackQuery(queryId, null, false, null);
    }

    @Override
    public HttpResponse<MessageResponse> editMessageText(String chatId, long messageId, String text, List<List<InlineKeyboardMarkupButton>> inlineKeyboardMarkup) throws UnirestException, IOException {
        if (inlineKeyboardMarkup == null)
            return Unirest.get(botApiUrl + "/messages/editText")
                          .queryString("token", apiToken)
                          .queryString("chatId", chatId)
                          .queryString("msgId", messageId)
                          .queryString("text", text)
                          .asObject(MessageResponse.class);
        return Unirest.get(botApiUrl + "/messages/editText")
                      .queryString("token", apiToken)
                      .queryString("chatId", chatId)
                      .queryString("msgId", messageId)
                      .queryString("text", text)
                      .queryString("inlineKeyboardMarkup", objectMapper.writeValueAsString(inlineKeyboardMarkup))
                      .asObject(MessageResponse.class);
    }

}
