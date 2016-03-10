package com.home;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileSorter {
    private static final int MAX_SIZE = 10240;

    public static File sort(File source,String name) throws IOException {
        File result = new File(name);
        List<File> list = getSortedFiles(source);
        FileWriter writer = new FileWriter(result);
        List<Scanner> scanners = new ArrayList<>();
        for(File file:list){
            scanners.add(new Scanner(file));
        }
        boolean end = false;
        while (!end){
            end = true;
            Set<String> currentSet = new TreeSet<>();
            for(Scanner scanner:scanners){
                if(scanner.hasNextLine()){
                    end = false;
                    currentSet.add(scanner.nextLine());
                }
            }
            if(end) break;
            writer.write(currentSet.iterator().next()+"\n");
            currentSet.clear();
        }
        writer.close();
        return result;
    }

    public static List<File> getSortedFiles(File source) throws IOException {
        int fileIndex = 0;
        Set<String> set = new TreeSet<>();
        List<File> files = new ArrayList<>();
        Scanner scanner = new Scanner(source);
        while(scanner.hasNextLine()){
            if(set.size() > MAX_SIZE){
                files.add(createFile(set,fileIndex++));
                set.clear();
            }
            set.add(scanner.nextLine());
        }
        if(!set.isEmpty()){
            files.add(createFile(set,fileIndex++));
        }
        return files;
    }

    private static File createFile(Set<String> set,int index) throws IOException {
        File file = new File("File_"+index+".txt");
        FileWriter writer = new FileWriter(file);
        for(String line:set){
            writer.write(line+"\n");
        }
        writer.close();
        return file;
    }

}
