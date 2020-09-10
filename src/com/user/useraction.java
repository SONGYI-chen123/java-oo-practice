package com.user;

import java.util.*;

import com.TopList.TopList;
import com.twu.*;
import java.util.Collections;


public class useraction {

    //用户登入
    public static void loginFunc(List<TopList> topList) {
        System.out.println("请输入您的昵称：");
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();

        //创建一个新的用户对象，该对象的username为设置的username
        user user = new user(username);

        //进入用户副界面并进行选择用户功能
        userChoose(topList,user);
    }


    private static void userChoose(List<TopList> topList, user user) {
        //显示用户副界面
        System.out.println("你好，"+user.username+"你可以：");
        System.out.println("1.查看热搜排行榜");
        System.out.println("2.给热搜事件投票");
        System.out.println("3.购买热搜");
        System.out.println("4.添加热搜");
        System.out.println("5.退出");

        //对用户副界面功能进行选择
        Scanner sc = new Scanner(System.in);
        int choose = sc.nextInt();
            switch(choose){
                case 1:
                    //查看热搜排行榜
                    check_hotSearch(topList);
                    //再次调用该函数，返回副界面
                    userChoose(topList,user);
                    break;
                case 2:
                    //给热搜事件投票
                    vote_hotSearch(topList,user);
                    userChoose(topList,user);
                    break;
                case 3:
                    //购买热搜
                    buy_hotSearch(topList);
                    userChoose(topList,user);
                    break;
                case 4:
                    //添加热搜
                    add_hotSearch(topList);
                    userChoose(topList,user);
                    break;
                case 5:
                    //退出
                    Main.choose_function(topList);
                    break;
            }
    }


    private static void check_hotSearch(List<TopList> topLists) {
        if(topLists!=null) {
            for(int i=0;i<topLists.size();i++) {
                System.out.println(String.format("%d   %s   %d",i+1,topLists.get(i).showcontent(),topLists.get(i).getHot()));
            }
        }
    }


    private static void add_hotSearch(List<TopList> topLists) {
        System.out.println("请输入你要添加的热搜事件的名字：");
        Scanner sc = new Scanner(System.in);
        String content = sc.nextLine();
        if(isExist(topLists,content)) {
            System.out.println("这条热搜已存在，请添加另外的热搜词条");
        }else {
            TopList topList = new TopList();
            topList.setContent(content);

            //给该热搜设置排名
            while (true) {
                int ranking = sc.nextInt();
                if (topLists.get(ranking + 1).getPrice() > 0) {
                    System.out.println("对不起，该热搜不能位于这个排名，请重新设置排名：");
                }else {
                    topList.setRanking(ranking);
                    break;
                }
            }
         //将该热搜词条添加入热搜排行榜中
            topLists.add(topList);

            System.out.println("添加成功");
        }
    }




    private static void vote_hotSearch(List<TopList> topLists, user user) {
        System.out.println("请输入你要投票的热搜名称:");
        Scanner sc = new Scanner(System.in);
        String content=sc.nextLine();

        if(isExist(topLists,content)) {
            System.out.println(String.format("请输入你要投票的热搜票数：(你目前还有%d)",user.getVotenum()));
            int vote = sc.nextInt();//本次投出的票数
            if(vote>user.getVotenum()) {
                System.out.println("投票失败！");
            }else {
                user.nowVotenum(user.getVotenum()-vote);
                topLists.get(Integer.parseInt(content)).nowhot(vote);
                System.out.println("投票成功！");
            }
        }else {
            System.out.println("抱歉，您所输入的热搜不存在！\n投票失败！");
        }

    }


    private static void buy_hotSearch(List<TopList> topLists) {
        //输入需要购买的热搜事件词条
        System.out.println("请输入你要购买的热搜名称:");
        Scanner sc = new Scanner(System.in);
        String content=sc.nextLine();

        //如果热搜存在可以购买
        if(isExist(topLists,content)){
            System.out.println("请输入你要购买的热搜排名：");
            int rank = sc.nextInt();

            System.out.println("请输入你要购买的热搜金额：");
            int price = sc.nextInt();

            if(price<1||topLists.get(rank-1).getPrice()>price) {
                System.out.println("购买失败");
            }else if(topLists.get(rank-1).getPrice()>0) {
                topLists.get(Integer.parseInt(content)).setPrice(price);
                topLists.set(rank-1, topLists.get(Integer.parseInt(content)));
                topLists.remove(Integer.parseInt(content));
                System.out.println("购买成功");
            }else {
                topLists.get(Integer.parseInt(content)).setPrice(price);
                eventSort(topLists);
                System.out.println("购买成功");
            }
        }else {
            System.out.println("抱歉，您输入的热搜名称不存在！\n购买失败！");
        }
    }






//判断热搜是否已经存在
    static boolean isExist(List<TopList> topLists, String content) {
        boolean isExist = false;
        if(topLists!=null) {
            for(int i=0;i<topLists.size();i++) {
                if(topLists.get(i).showcontent().equals(content)) {
                    isExist=true;
                }
            }
        }
        return isExist;
    }

    public  static void eventSort(List<TopList> topLists) {
        //对TopLists中的热搜词条进行排序

        Collections.sort(topLists, new Comparator<TopList>() {

            @Override
            public int compare(TopList o1, TopList o2) {
                if(o1.getPrice()>o2.getPrice()) {
                    return -1;
                }else if(o1.getPrice()<o2.getPrice()) {
                    return 1;
                }else {
                    if(o1.getHot()>o2.getHot()) {
                        return -1;
                    }else if(o1.getHot()<o2.getHot()) {
                        return 1;
                    }else {
                        return 0;
                    }
                }
            }
        });

    }
}
