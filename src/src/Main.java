import Model.TokenClass.AddressLabel;
import Model.TokenClass.Operation;
import Model.TokenClass.TokenObject;
import utils.ReadAndClean;
import Model.*;
import utils.TokenObjectMapping;

import utils.WatCodeGenerate.*;
import java.util.List;
import java.util.Map;

public class Main {
    static ReadAndClean read = new ReadAndClean();
    static WatBlockWrite write = new WatBlockWrite();
    static ZeroPageConvert zeroPage = new ZeroPageConvert();
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        String path = System.getProperty("user.dir") + "/output/";
        Map<String, List<String>> tal = read.splitBlock(read.readUnxtalByWord());
        write.fileCreate(path, read.getName+".wat");

        System.out.println("Here is the result :");
        if (tal != null) {
            for (String s : tal.keySet()) {
                System.out.println(s + "   " + tal.get(s));
            }
        }

        write.write("(module\n");
        write.importLog();

        TokenObjectMapping tokenObjectMapping = new TokenObjectMapping();

        for (String s : tal.keySet()) {
            System.out.println("\n"+"The Analysis of " + s+":");
            List<TokenObject> tokenList = tokenObjectMapping.tokenObjectMapping(tal.get(s));
            tokenObjectMapping.FunctionConvert(tokenList);
            if (tokenObjectMapping.lableList != null && tokenObjectMapping.lableList.size()!=0) {
                System.out.println("  Here is your Addresslable object");
                for (AddressLabel a : tokenObjectMapping.lableList) {
                    if (a.isAboslute(a.getIndication())) {
                        System.out.println("    "+a.getIndication() + "" + a.getName());
//                System.out.println("here are "+a.getString()+"'s content:");
//                for(TokenObject token : a.getContentToken()){
//                    System.out.print(token.getString()+"   ");
//                }
                    }
                }
            }

            if (tokenList != null) {
                System.out.println();
                System.out.println(" TokenList :");
                for (TokenObject token : tokenList) {
//            if(!token.getType().equals("unknow"))
                    System.out.print("    "+token.getString() + "  " + token.getType());
                    System.out.println();
                }
            }

            if(tokenObjectMapping.functionList!=null && tokenObjectMapping.functionList.size()!=0){
                System.out.println(" Here are functions");
                for (Function func : tokenObjectMapping.functionList) {
                    System.out.println("    "+"@" + func.getName());
                }
            }

            if(s.equals("zero-page")){
                String zeroWrite =zeroPage.convert(tokenObjectMapping.lableList);
                write.write(zeroWrite);
            }
            else if(s.equals("main-program")){
                write.mainFunctionCreate(tokenList);
            }
            else{
                write.subFunctionsCreate(tokenObjectMapping.functionList);
            }

        }
        System.out.println("Successfully Analysis");
        write.write(")");
        write.pushAll();
        System.out.println("Recreate target file:");
        System.out.println(path+read.getName+".wat");
        System.out.println("Content fill up, all done");
        System.exit(0);
    }
}
