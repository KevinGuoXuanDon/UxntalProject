package utils.WatCodeGenerate;

import Model.DescribeType.WatOperationType;
import Model.TokenClass.AddressLabel;
import Model.TokenClass.Padding;
import java.util.List;

public class ZeroPageConvert {
    int countForLabels = 0;
    public String convert(List<AddressLabel> list){
        if(list ==null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String changeLine = "\n";
        for(AddressLabel object : list){
            String name = " $"+object.getName();
            /**
             * if it need count by Padding
             */
//            for(TokenObject token : object.getContentToken()){
//                if(token instanceof Padding){
//                    Padding judge = (Padding) token;
//                    if(judge.isRelative(judge.getIndication())){
//                        countForLabels += Integer.valueOf(judge.getContent())/2;
//                    }
//                }
//            }
            String command = "("+WatOperationType.Global.toString() + name+" (mut i32) (i32.const "+countForLabels+"))";
            countForLabels++;
            sb.append(command);
            sb.append(changeLine);
        }
        return sb.toString();
    }

}

