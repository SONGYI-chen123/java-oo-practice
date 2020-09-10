package com.twu;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.TopList.*;
import com.user.*;
import com.Admin.*;


public class Main {
    public static void main(String[] args) {
        List<TopList>topList;
        topList = new ArrayList<TopList>();
        choose_function(topList);
    }

    //显示主界面并能进行选择进入副界面
    public static void choose_function(List<TopList> topList) {
        useraction ua = new useraction();
        Adminaction aa = new Adminaction();
        MainMenu mainMenu = new MainMenu();

        //显示主界面
        mainMenu.showMenu();

        //获取选择
        Scanner sc = new Scanner(System.in);
        int chosenTag = sc.nextInt();

        switch(chosenTag){
            case 1:
                ua.loginFunc(topList);
                break;
            case 2:
                aa.loginFuncA(topList);
                break;
            case 3:
                break;
        }


    }


}