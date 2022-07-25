import Model.TokenClass.AddressLabel;
import Model.TokenClass.TokenObject;
import utils.ReadAndClean;
import Model.*;
import utils.TokenObjectMapping;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    static ReadAndClean read = new ReadAndClean();


    public static void main(String[] args) throws Exception {
        Main main = new Main();
        Map<String,List<String>> tal = read.splitBlock(read.readUnxtalByWord());
        System.out.println("Here is the result :");
        if(tal!=null){
            for(String s:tal.keySet()){
                System.out.println(s+"   "+tal.get(s));
            }
        }
        System.out.println();
        TokenObjectMapping tokenObjectMapping = new TokenObjectMapping();
        List<TokenObject> tokenList = tokenObjectMapping.tokenObjectMapping(tal.get("main-program"));

        tokenObjectMapping.FunctionConvert(tokenList);
        System.out.println("Here is your Addresslable object");
        for(AddressLabel a : tokenObjectMapping.lableList){
            if(a.isAboslute(a.getIndication())){
                System.out.println(a.getIndication()+""+a.getName());
//                System.out.println("here are "+a.getString()+"'s content:");
//                for(TokenObject token : a.getContentToken()){
//                    System.out.print(token.getString()+"   ");
//                }
            }
        }
        System.out.println();
        System.out.println("TokenList :");
        for(TokenObject token:tokenList){
//            if(!token.getType().equals("unknow"))
                System.out.print(token.getString() + "  ");
        }
        System.out.println();
        System.out.println("Here are functions");
        for(Function func : tokenObjectMapping.functionList){
            System.out.println("@"+ func.getName());
        }

        System.exit(0);
    }
}
