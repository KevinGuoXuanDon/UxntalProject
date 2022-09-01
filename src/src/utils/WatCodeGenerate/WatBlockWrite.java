package utils.WatCodeGenerate;

import Model.Function;
import Model.TokenClass.*;
import Model.TransferMap;

import java.io.*;
import java.util.List;

public class WatBlockWrite {
    public File file;
    public Writer writer;
    private static final String manipulationVariables = "(local $_a i32) (local $_b i32) (local $_c i32)";
    private boolean complexFlag;
    private static final String changeLine = "\r";

    public void fileCreate(String path, String name) {
        try {
            file = new File(path + name);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            writer = new FileWriter(file, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void importLog() throws IOException {
        String log = "(import  \"console\" \"log\" (func  $log (param  i32 )))" + changeLine;
        writer.write(log);
    }

    public void mainFunctionCreate(List<TokenObject> content) throws IOException {
        String start = "(func (export  \"main\") " + changeLine;
        writer.write(start);
        writer.write(processStream(content));
        String end = ")" + changeLine;
        writer.write(end);
    }

    public void subFunctionsCreate(List<Function> content) throws IOException {
        for (Function f : content) {
            functionCreateModel(f);
        }
    }

    public void functionCreateModel(Function func) throws IOException {
        complexFlag = false;
        String name = func.getName();
        List<TokenObject> contentList = func.getTokenContent();
        if (contentList != null) {
            writer.write("(func $" + name + " (param $x i32) (param $y i32) (result i32)" + changeLine);
            writer.write("local.get $x local.get $y");
            StringBuilder sb = new StringBuilder(processStream(contentList));
            if (complexFlag) sb.insert(0, manipulationVariables + changeLine);
            complexFlag = false;
            writer.write(sb.toString() + changeLine);
            writer.write(")" + changeLine);
        }
    }

    public String processStream(List<TokenObject> contentList) {
        if (contentList == null) {
            return "";
        }
        int n = contentList.size();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < contentList.size(); i++) {
            TokenObject object = contentList.get(i);
            if(object ==null){
                continue;
            }
            // Lit translate
            if (object instanceof Literal) {
                // special statement #18 DEO
                if (i + 1 < n && contentList.get(i + 1).getString().equals("DEO")) {
                    i++;
                    sb.append(TransferMap.map.get("DEO") + " ");
                    continue;
                }
                sb.append("i32.const" + " ");
                if (((Literal) object).getFollowingContent() != null) {
                    sb.append("" + ((Literal) object).getFollowingContent());
                } else {
                    if (i + 1 >= n) {
                        System.out.println("Code hase problems, # should get a following constant");
                        System.exit(0);
                    }
                    TokenObject next = contentList.get(i + 1);
                    if (next instanceof RawConstant) {
                        sb.append(next.getString() + " ");
                    }
                    i++;
                }
            }
            // function call or variable call.`
            else if (object instanceof AddressLabelReference) {
                AddressLabelReference refer = (AddressLabelReference) object;
                String referName = refer.getContent();
                if (i + 1 >= n) {
                    System.out.println("variable lack of operation");
                    System.exit(0);
                }
                Operation next = (Operation) contentList.get(i + 1);
                sb.append(TransferMap.map.get(next.getCapital()) + " ");
                i++;
                sb.append("$" + referName + " ");
            }
            // through HashMap to get target Operations
            else if (object instanceof Operation) {
                String originalOpe = ((Operation) object).getCapital();
                String targetOpe = TransferMap.map.get(originalOpe);
                if (originalOpe.equals("DUP") || originalOpe.equals("SWP") || originalOpe.equals("ROT") || originalOpe.equals("OVR")) {
                    complexFlag = true;
                }
                sb.append(targetOpe + " ");
            }
            else if (object instanceof AddressLabel) {
                AddressLabel add = (AddressLabel) object;
                List<TokenObject> content = add.getContentToken();
                sb.append(" $" + add.getName() + " ");
                sb.append(processStream(content) + " ");
            }
            else{
                continue;
            }
            sb.append(changeLine);
        }

        return sb.toString().replace("null","");
    }

    public void write(String s) throws IOException {
        writer.write(s);
    }

    public void pushAll() throws IOException {
        writer.flush();
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        WatBlockWrite write = new WatBlockWrite();
        String path = System.getProperty("user.dir") + "/output/";
        write.fileCreate(path, "test1.wat");
    }
}
