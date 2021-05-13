package com.example.chatapp;

import java.util.Date;

public class Message {
    public String userName;
    public String txtMsg;
    private long msgTime;

    public Message(){

    }

    public Message(String userName, String txtMsg){
        this.userName = userName;
        this.txtMsg = txtMsg;
        this.msgTime = new Date().getTime();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTxtMsg() {
        return txtMsg;
    }

    public void setTxtMsg(String txtMsg) {
        this.txtMsg = txtMsg;
    }

    public long getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(long msgTime) {
        this.msgTime = msgTime;
    }


}

