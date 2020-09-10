package com.Admin;

import com.TopList.TopList;
import com.twu.Main;
import com.user.user;

import java.util.List;
import java.util.Scanner;

public class Adminaction {

    public static void loginFuncA(List<TopList> topLists) {
        System.out.println("请输入您的昵称：");
        Scanner sc = new Scanner(System.in);
        String Adname = sc.nextLine();
        System.out.println("请输入您的密码：");
        String Adpasswd = sc.nextLine();

        Admin admin = new Admin();

        if(Adname != admin.adminname || Adpasswd != admin.passwd){
            System.out.println("请输入正确的管理员名或密码：");
            loginFuncA(topLists);

            } else {
            //进入用户副界面并进行选择用户功能
             AdminChoose(topLists,admin);
        }

    }

    public static void AdminChoose(List<TopList> topLists, Admin admin){
        //显示管理员副界面
        System.out.println("你好，"+admin.adminname+"你可以：");
        System.out.println("1.查看热搜排行榜");
        System.out.println("2.添加热搜");
        System.out.println("3.添加超级热搜");
        System.out.println("4.退出");

        //对管理员副界面功能进行选择
        Scanner sc = new Scanner(System.in);
        int choose = sc.nextInt();
        switch(choose){
            case 1:
                //查看热搜排行榜
                check_hotSearch(topLists);
                //再次调用该函数，返回副界面
                AdminChoose(topLists,admin);
                break;
            case 2:
                //查看热搜排行榜
                add_hotSearch(topLists);
                //再次调用该函数，返回副界面
                AdminChoose(topLists,admin);
                break;
            case 3:
                //查看热搜排行榜
               add_superSearch(topLists);
                //再次调用该函数，返回副界面
                AdminChoose(topLists,admin);
                break;
            case 4:
                //退出
                Main.choose_function(topLists);
                break;
        }
    }


    private static void check_hotSearch(List<TopList>topLists){
        if(topLists!=null) {
            for(int i=0;i<topLists.size();i++) {
                System.out.println(String.format("%d   %s   %d",i+1,topLists.get(i).showcontent(),topLists.get(i).getHot()));
            }
        }
    }


    private static void add_hotSearch(List<TopList>topLists){
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


    private static void add_superSearch(List<TopList>topLists){
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

            //将该热搜设置为超级热搜
            topList.setsuperSearch();
            //将该热搜词条添加入热搜排行榜中
            topLists.add(topList);

            System.out.println("添加成功");
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
}
