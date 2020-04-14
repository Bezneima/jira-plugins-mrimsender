package ru.mail.jira.plugins.mrimsender.icq.dto.events;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

@JsonTypeInfo(
        defaultImpl = JsonNode.class,
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CallbackQueryEvent.class, name = "callbackQuery"),
        @JsonSubTypes.Type(value = NewMessageEvent.class, name = "newMessage")
})
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Event<T> {
    private long eventId;
    private T payload;
}
