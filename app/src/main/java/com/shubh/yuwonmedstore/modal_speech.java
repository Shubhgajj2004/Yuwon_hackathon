package com.shubh.yuwonmedstore;

public class modal_speech {

    String conversation , type;

    public modal_speech(String conversation, String type) {
        this.conversation = conversation;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConversation() {
        return conversation;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }
}
