package utils;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class ReadAndClean {
    public String getName;
    public String getPath() {

        FileDialog dialog = new FileDialog(new Frame(), "Select your file", FileDialog.LOAD);
        dialog.setVisible(true);
        String path = dialog.getDirectory() + dialog.getFile();
        dialog.dispose();
        return path;
    }

    public List<String> readUnxtalByWord() {
        String fileName = getPath();
        String[] args = fileName.split("/");
        String getName = args[args.length-1];
        this.getName = getName.substring(0,getName.indexOf("."));
        System.out.println("Program start to read " +"\n"+ fileName + " by every word");
        String s = null;
        try {
            s = Files.readString(Paths.get(fileName));
        } catch (IOException e) {
            System.out.println("ops! file not exist!");
            return null;
        }
        s = clearComment(s);
        s = s.replaceAll("\\n", " ");
        // we split by multiple spaces, for a   b c(3 spaces) we want a,b,c instead of a, ,b,c
        String[] arr = s.split("\s+");
        List<String> list = new ArrayList<>();
        for (String str : arr) {
            String trimed = str.trim();
            if (!trimed.equals("")) {
                list.add(trimed);
            }
        }
        // print
//        System.out.println("Here is your reading result:  ");
//        list.stream().forEach(System.out::println);
        return list;
    }

    // Load a .tal and store it in to List
    public List<String> readUnxtalByLine() {
        List<String> res = new ArrayList<>();
        String path = getPath();
        File fileName = new File(path);
        System.out.println("Program start to read " + fileName + " by every line");
        try (Scanner sc2 = new Scanner(new FileReader(fileName))) {
            while (sc2.hasNextLine()) {  //按行读取字符串
                String line = sc2.nextLine();
                line = clearComment(line);
                if (!line.equals("")) {
                    res.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ops! file not exist!");
            return null;
        }
//        System.out.println("Here is your reading result: ");
//        res.stream().forEach(System.out::println);
        return res;
    }

    // a little algorithm to clear all kinds of comment: ( ( comment ) ), ( comment )( comment )
    public String clearComment(String context) {
        context = context.trim();
        int left = context.indexOf("(");
        // If this context doesn't contains comment, then return
        if (left == -1) {
            return context;
        } else {
            int right = left + 1;
            int count = 1;
            while (left != -1 && right < context.length()) {
                // use count to prevent nexted parentheses. ( ( comment ) )
                if (context.charAt(right) == '(') {
                    count++;
                } else if (context.charAt(right) == ')') {
                    count--;
                }
                right++;
                // find a valid parentheses, then we remove it.
                if (count == 0) {
                    String comment = context.substring(left, right);
                    context = context.replace(comment, "");
                    // is there any more comment?
                    left = context.indexOf('(');
                    right = left + 1;
                    if (left != -1) count = 1;
                }
            }
        }
        System.out.println("Comment cleared");
        return context;
    }

    // Divide list into blocks base on their function
    public Map<String, List<String>> splitBlock(List<String> list) {
        // avoid empty list which may happen when failed to read a file.
        if (list == null) {
            System.out.println("No file detected, stop spliting");
            return null;
        }
        // We want a ordered map
        Map<String, List<String>> map = new LinkedHashMap<>();
        List<String> zeroPage = new ArrayList<>();
        int i = 0;
        // record zero-page
        while (i < list.size()) {
            // curent code line
            String cur = list.get(i);
            if (cur.contains("|0100")) {
                break;
            }
            zeroPage.add(cur);
            i++;
        }
        map.put("zero-page", zeroPage);
        // zero-page done, now we do it for main program
        List<String> main = new ArrayList<>();
        String pre;
        String sub = "";
        while (i < list.size()) {
            String cur = list.get(i);
            if (cur.contains("BRK")) {
                int index = cur.indexOf("BRK") + 2;
                // if BRK is not a single line, then we need to break this String into two part
                // Actually if we use readByWord function, this situation will not happen.
                pre = cur.substring(0, index + 1);
                main.add(pre);
                sub = cur.substring(index + 1);
                break;
            }
            i++;
            main.add(cur);
        }
        map.put("main-program", main);
        // so the part after BRK should be added to next line;
        i++;
        if (i < list.size() && !sub.equals("")) {
            list.set(i, sub + list.get(i));
        }
        // then we deal with labels
        List<String> labels = new ArrayList<>();
        while (i < list.size()) {
            String cur = list.get(i);
            labels.add(cur);
            i++;
        }
        map.put("rest-lables", labels);
        System.out.println();
        System.out.println("end of splitting blocks");
        return map;
    }

    public static void main(String[] args) {
        ReadAndClean read = new ReadAndClean();
        List<String> list = read.readUnxtalByWord();
        Map<String, List<String>> map = read.splitBlock(list);
        System.out.println("Here is the result :");
        if (map != null) {
            for (String s : map.keySet()) {
                System.out.println(s + "   " + map.get(s));
            }
        }
    }
}
