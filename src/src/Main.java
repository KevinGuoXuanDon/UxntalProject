import Model.TokenClass.AddressLabel;
import utils.ReadAndClean;
import Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    static ReadAndClean read = new ReadAndClean();
    static Function functionModel = new Function();
    static List<Function> functionList = new ArrayList<>();
    static List<AddressLabel> AddressLabelList = new ArrayList<>();


    public static void main(String[] args) {
        Main main = new Main();
        Map<String,List<String>> tal = read.splitBlock(read.readUnxtalByWord());
        System.out.println("Here is the result :");
        if(tal!=null){
            for(String s:tal.keySet()){
                System.out.println(s+"   "+tal.get(s));
            }
        }
        // deal with rest-lables
        final char start = '@';
        int mark = 0;
        List<String> lables = tal.get("rest-lables");
        List<String> recorder = new ArrayList<>();
        for(int i =0; i<lables.size(); i++){
            String str =  lables.get(i);
            if(str.charAt(0)==start){
                mark++;
            }
            if(mark==1){
                recorder.add(str);
                if(functionModel.isFunction(start,str)){
                    mark--;
                    String name = recorder.get(0).substring(1);
                    String end = str;
                    Function newfunction = new Function(name,end,recorder);
                    recorder.clear();
                    functionList.add(newfunction);
                    System.out.println("function added "+name);
                }
                else if(i==lables.size()-1){
                    AddressLabel newAddress = new AddressLabel(start,recorder);
                    AddressLabelList.add(newAddress);
                    recorder.clear();
                    mark--;
                }
            }
            else if(mark==2){
                AddressLabel newAddress = new AddressLabel(start,recorder);
                AddressLabelList.add(newAddress);
                recorder.clear();
                mark--;
                recorder.add(str);
            }
        }
        System.out.println();
        System.out.println("Here is your functions");
        for(Function f :functionList){
            System.out.println("function's name: "+f.getName());
        }
        System.out.println("Here is your AddressLable");
        for(AddressLabel al: AddressLabelList){
            System.out.println("AddressLabel :"+al.getContent());
        }
    }
}
