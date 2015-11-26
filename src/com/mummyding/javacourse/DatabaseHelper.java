package com.mummyding.javacourse;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mummyding on 15-11-25.
 */
public class DatabaseHelper {
    public static boolean isFileCreateSuccessful = true;
    public static final String fileName = "FileDatabase.txt";
    private static File file;
    private static BufferedWriter writer;
    private static FileWriter fileWriter;
    private static BufferedReader reader;
    private static FileReader fileReader;

    /*
        initialize basic data in static scope.
        create database file (singleton)
     */
    static {
        file = new File(fileName);
        /*
            check file is exist or not,create if it not exist.
            set isFileCreateSuccessful to false if create fail.
         */
        if(file.exists() == false){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                isFileCreateSuccessful = false;
                System.out.println("Database initialize fail");
            }
        }

    }


    /*
       the following is basic operating of database methods.
       addData updateData and deleteData.
     */

    /*
        insert  a record to file database.
     */
    public static boolean addData(StudentBean data){
        if(dataCheck(data) == false) return false;

        try {
            initWriter();
            writer.write(data.toString());
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                writer.close();
                fileWriter.close();
            }catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    /*
        update a record by student id.
     */
    public static boolean updateData(String studentID,StudentBean data){
        if(dataCheck(data) == false) return false;
        if(deleteData(studentID)){
            addData(data);
            return true;
        }
        return false;
    }
    /*
        delete a record by student id.
     */
    public static boolean deleteData(String studentID){
        int lineNumber = findDataByStudent(studentID);
        if(lineNumber != -1){
            deleteDataByLineNumber(lineNumber);
            return true;
        }
        return false;
    }



    /*
        the following is basic utils of class
        initWriter initReader findDataByStudent and deleteDataByLineNumber
     */

    private static boolean dataCheck(StudentBean data){
        if(isFileCreateSuccessful == false){
            System.out.println("Database doesn't exist, insert fail");
            return false;
        }
        if(data.isDataComplete() == false){
            System.out.println("Record is not complete, insert fail");
            return false;
        }
        return true;
    }


    /*
        initialize BufferedWriter FileWriter.
     */
    private static boolean  initWriter(){
        /*
            create FileWriter and BufferWriter.
         */
        try {
            writer = new BufferedWriter(fileWriter =new FileWriter(file,true));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
        initialize BufferedReader FileReader.
     */
    private static boolean  initReader(){
        /*
            create FileReader and BufferReader.
         */
        try {
            reader = new BufferedReader(fileReader = new FileReader(file));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /*
        find record linenumber in file database by student id.
     */
    private static int findDataByStudent(String studentID){
        initReader();
        String tmpStr;
        boolean isFind =false;
        int lineNumber = -1;
        int currentLineNumber =0;
        try {
            initReader();
            while ((tmpStr = reader.readLine()) != null && isFind == false){
                int pos = tmpStr.indexOf(studentID);
                if(pos>=0&&tmpStr.charAt(studentID.length())==','){
                    lineNumber = currentLineNumber;
                    isFind = true;
                }
                currentLineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            try {
                reader.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  lineNumber;
    }
    /*
        delete a  record from  file database by linenumber.
     */
    private static boolean deleteDataByLineNumber(int lineNumber){
        List<String> lines = new ArrayList<>();
        RandomAccessFile oldFile = null,newFile = null;

        try {
            oldFile = new RandomAccessFile(file,"r");
            newFile = new RandomAccessFile(file,"rw");
            oldFile.seek(0);
            String tmpStr;
            while((tmpStr = oldFile.readLine()) != null){
                lines.add(tmpStr);
            }
            FileOutputStream a=new FileOutputStream(file, false);
            newFile.seek(0);
            lines.remove(lineNumber);
            for(int i =0 ; i<lines.size();i++){
                newFile.writeBytes(lines.get(i));
                newFile.writeBytes("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                oldFile.close();
                newFile.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static void printAllData(){
        int currentLineNumber =-1;
        try {
            initReader();
            String tmpStr;
            while ((tmpStr = reader.readLine()) != null){
                System.out.println(tmpStr);
                currentLineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            try {
                reader.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(currentLineNumber == -1){
            System.out.println("Database has no data");
        }
    }

}
