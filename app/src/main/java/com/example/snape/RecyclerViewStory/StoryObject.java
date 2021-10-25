package com.example.snape.RecyclerViewStory;

import java.util.Objects;

public class StoryObject {
    private String email;
    private String uid;
    private String chatOrStory;

    public StoryObject(String email, String uid, String chatOrStory){
        this.email = email;
        this.uid = uid;
        this.chatOrStory = chatOrStory;
    }
    public String getUid(){
        return uid;
    }
    public void setUid(String uid){
        this.uid = uid;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getChatOrStory(){
        return chatOrStory;
    }
    public void setChatOrStory(String chatOrStory){
        this.chatOrStory = chatOrStory;
    }

    @Override
    public boolean equals(Object obj) {
       boolean same = false;
       if(obj != null && obj instanceof StoryObject){
           same = this.uid == ((StoryObject) obj ) .uid;
       }
       return same;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (this.uid == null ? 0 : this.uid.hashCode());
        return result;
    }
}
