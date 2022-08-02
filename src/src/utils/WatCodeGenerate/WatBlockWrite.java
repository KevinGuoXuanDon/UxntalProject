package utils.WatCodeGenerate;
import Model.TokenClass.TokenObject;

import java.io.*;
import java.util.List;
public class WatBlockWrite {
    private File file;
    private Writer writer;
    public void fileCreate(String path){
        try{
            file = new File(path);
            if(!file.exists()){
                try{
                    file.createNewFile();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            writer = new FileWriter(file,true);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void importLog() throws IOException {
        String log = "(import  \"console\" \"log\" (func  $log (param  i32 )))";
        writer.write(log);
    }

    public void mainFunctionCreate(List<TokenObject> content){
        String start = "(func (export  \"main\") " + "\r";
        StringBuilder sb = new StringBuilder();



        String end = "\r"+")";
    }
    public void subFunctionCreate(List<TokenObject> content){

    }

    public static void main(String[] args) {

    }
}
