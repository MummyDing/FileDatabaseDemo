package com.mummyding.javacourse;

import java.util.Scanner;

/**
 * Created by mummyding on 15-11-25.
 */
public class Menu {
    public Menu() {
        System.out.println("***********    File Database System    ***********");
        System.out.println("***********    Author : MummyDing      ***********");
        System.out.println("***********    Student ID: 1308094004  ***********");
        System.out.println("***********    Course: Java Programming***********");
        System.out.println("***********    Date : 15-Nov-2015      ***********");
        showMenu();
    }

    public void showMenu(){
        System.out.println("\n\n------------------Menu------------------\n\n");
        System.out.println("1. Add a student record  ");
        System.out.println("    Format Example: 1308094004 | MummyDing | 1995/04/20 | Compute Science");
        System.out.println("2. Update a student record  ");
        System.out.println("    Format Example: 1308094004 | 1308094004 | MummyDing | 1995/04/20 | Internet of Things");
        System.out.println("3. Delete a student record  ");
        System.out.println("    Format Example: 1308094004 ");
        System.out.println("4. Print Database data");
        System.out.println("5. Show Menu");
        System.out.println("6. Exit");
        System.out.println("\n\n------------------Menu------------------\n\n");
        System.out.println("\n\nInput Your Choice [1-5] : ");


        int selectID;
        Scanner sc = new Scanner(System.in);
        while (true){
            selectID = Integer.parseInt(sc.nextLine());
            switch (selectID){
                case 1:
                    StudentBean studentBean = new StudentBean();
                    studentBean.readData();
                    if(DatabaseHelper.addData(studentBean)){
                        System.out.println("Successful");
                    }else {
                        System.out.println("Failed");
                    }
                    break;
                case 2:
                    System.out.println("Please Input Student ID ");
                    String studentID = sc.nextLine().trim();
                    studentBean = new StudentBean();
                    studentBean.readData();
                    if(DatabaseHelper.updateData(studentID, studentBean)){
                        System.out.println("Successful");
                    }else {
                        System.out.println("Failed");
                    }
                    break;
                case 3:
                    System.out.println("Please Input Student ID ");
                    studentID = sc.nextLine().trim();
                    if(DatabaseHelper.deleteData(studentID)){
                        System.out.println("Successful");
                    }else {
                        System.out.println("Failed");
                    }
                    break;
                case 4:
                    DatabaseHelper.printAllData();
                    break;
                case 5:
                    showMenu();
                    return;
                case 6:
                    return;
            }
            System.out.println("\n\nInput Your Choice [1-5] : ");
        }

    }
}
