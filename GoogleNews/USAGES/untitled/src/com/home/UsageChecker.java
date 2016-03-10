package com.home;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UsageChecker {
    private File res;
    private FileWriter writer;

    public UsageChecker() throws IOException {
        res = new File("resres.txt");
        writer = new FileWriter(res);
    }

    public File findUsages(List<File> usages, File keys) throws IOException {
        Scanner scanner = new Scanner(keys);
        while (scanner.hasNextLine()) {
            String key = scanner.nextLine();
            boolean isUsed = false;
            for (File file : usages) {
                if (isUsed(file, key)) {
                    isUsed = true;
                    break;
                }
            }
            if(!isUsed) writer.write(key+"\n");
        }
        writer.close();
        return res;
    }

    public static boolean isUsed(File usages, String key) throws IOException {
        Scanner sUsages = new Scanner(usages);
        ArrayList<String> listUsages = new ArrayList<>();
        while (sUsages.hasNextLine()) {
            listUsages.add(sUsages.nextLine());
        }
        if (Arrays.binarySearch(listUsages.toArray(), key) >= 0) {
            return true;
        }
        return false;
    }
}
