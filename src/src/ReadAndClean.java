import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.*;

public class ReadAndClean {
    // Load a .tal and store it in to List
    public List<String> readUnxtal() throws FileNotFoundException {
        List<String> res = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        File fileName = new File("./UxntalCode/" + sc.next() + ".tal");
        try (Scanner sc2 = new Scanner(new FileReader(fileName))) {
            while (sc2.hasNextLine()) {  //按行读取字符串
                String line = sc2.nextLine();
                line = clearComment(line);
                if (!line.equals("")) {
                    res.add(line);
                }
            }
        }
//        String s = Files.readString(fileName);
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
                }
                // is there any more comment?
                left = context.indexOf('(');
                
            }
        }
        return context;
    }

    // Divide list into blocks base on their function
    public Map<String, List<String>> splitBlock(List<String> list) {
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
        map.put("zero-page",zeroPage);
        // zero-page done, now we do it for main program
        List<String> main = new ArrayList<>();
        String pre;
        String sub ="";
        while(i < list.size()){
            String cur = list.get(i);
            if(cur.contains("BRK")){
                int index = cur.indexOf("BRK");
                // if BRK is not a single line, then we need to break this String into two part
                pre = cur.substring(0,index+1);
                main.add(pre);
                sub = cur.substring(index+1);
                break;
            }
            i++;
            main.add(cur);
        }
        // so the part after BRK should be added to next line;
        i++;
        if(i<list.size() && !sub.equals("")){
            list.set(i,sub+list.get(i));
        }

        // then we deal with labels
        List<String> labels = new ArrayList<>();
        while(i<list.size()){
            String cur = list.get(i);

        }


        System.out.println("end of spliting blocks");
        return map;
    }

    public static void main(String[] args) {
        ReadAndClean read = new ReadAndClean();
        try {
            List<String> list = read.readUnxtal();
            read.splitBlock(list);
        } catch (FileNotFoundException e) {
            System.out.println("Error for reading: ");
            System.out.println("" + e.toString());
        }


    }
}
