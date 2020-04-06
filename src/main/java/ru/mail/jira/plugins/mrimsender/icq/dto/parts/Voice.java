package ru.mail.jira.plugins.mrimsender.icq.dto.parts;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

@JsonDeserialize(as = Voice.class)
public class Voice extends Part<Voice.Data> {
    public static class Data {
        public String fileId;

        @Override
        public String toString() {
            return "Data{" +
                    "fileId='" + fileId + '\'' +
                    '}';
        }
    }

    public String getFileId() {
        return this.getPayload().fileId;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
