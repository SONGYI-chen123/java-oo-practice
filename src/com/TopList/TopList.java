package com.TopList;

public class TopList {
    String content ;//热搜词条的具体内容
    int hotnum=0;//热搜词条的热度
    int ranking ;//热搜词条的排名
    int price=0;//购买热搜所需金额
    boolean superSearch = false;

    //显示热搜词条内容
    public String showcontent(){
        return this.content;
    }

    //显示热搜词条热度
    public int getHot(){
        return this.hotnum;
    }

    //设置添加的热搜词条内容
    public void setContent(String content){
        this.content = content;
    }
    //实时热度
    public int nowhot(int nowhot){
        this.hotnum = nowhot;
        return  this.hotnum;
    }

    public int getPrice(){
        return this.price;
    }

    public int getRanking(){
        return this.ranking;
    }

    //设置初始排名
    public int setRanking(int ranking){
        this.ranking = ranking;
        return this.ranking;
    }

    public int setPrice(int price){
        this.price = price;
        return this.price;
    }

    public boolean setsuperSearch(){
        this.superSearch=true;
        return superSearch;
    }
}
