package utils.WatCodeGenerate;

import Model.DescribeType.WatOperationType;
import Model.TokenClass.AddressLabel;
import java.util.List;

public class ZeroPageConvert {

    public String convert(List<AddressLabel> list){
        StringBuilder sb = new StringBuilder();
        String changeLine = " \r\n ";
        for(AddressLabel object : list){
            String name = " $"+object.getName();
            String command = "( "+WatOperationType.Global.toString() + name+" (mut i32) (i32.const 0))";
            sb.append(command);
            sb.append(changeLine);
        }
        return sb.toString();
    }

}

