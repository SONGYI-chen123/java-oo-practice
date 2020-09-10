package com.user;

public class user {
    public String username;//用户名
    public int votenum = 10;//用户拥有的初始票数

    public user(String username) {
        this.username=username;
    }

    public String getname(){
        return username;
    }

    public int getVotenum() {
        return votenum;
    }

    public int nowVotenum(int nowVotenum){
        this.votenum = nowVotenum;
        return  votenum;
    }
}
