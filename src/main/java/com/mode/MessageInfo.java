package com.mode;

import com.servers.Message;
import org.json.JSONObject;

public class MessageInfo {
    public static final String Service = "Service";
    public static final String Client = "Client";
    /**
     * tag :
     * info :
     */
    private String tag;
    private String content;
    private String name;

    public MessageInfo(){}

    public MessageInfo(String jsonString){
        JSONObject object = new JSONObject(jsonString);
        tag = object.getString("tag");
        content = object.getString("content");
        name = object.getString("name");
    }

    public MessageInfo(String tag,String name,String content){
        this.tag = tag;
        this.name = name;
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put("tag",tag);
        object.put("content",content);
        object.put("name",name);
        return object.toString();
    }
}
