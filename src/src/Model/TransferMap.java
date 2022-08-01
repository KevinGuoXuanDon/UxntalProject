package Model;


import Model.DescribeType.OpeartionType;
import Model.DescribeType.WatOperationType;

import java.util.*;


public class TransferMap {
    public static Map<String, String> map = Map.of(
            OpeartionType.ADD.getOpe(), WatOperationType.Add.toString(),
            OpeartionType.SUB.getOpe(), WatOperationType.Sub.toString(),
            OpeartionType.MUL.getOpe(), WatOperationType.Mul.toString(),
            OpeartionType.DIV.getOpe(), WatOperationType.Div.toString(),
            OpeartionType.AND.getOpe(), WatOperationType.Add.toString(),
            OpeartionType.ORA.getOpe(), WatOperationType.OR.toString(),
            OpeartionType.EQU.getOpe(), WatOperationType.Equal.toString(),
            OpeartionType.NEQ.getOpe(), WatOperationType.NotEqual.toString(),
            OpeartionType.JSR.getOpe(), WatOperationType.Call.toString(),
            OpeartionType.GTH.getOpe(), WatOperationType.GreaterThan.toString()
    );

    static {
        map.put(OpeartionType.LTH.getOpe(), WatOperationType.LessThan.toString());
        map.put(OpeartionType.POP.getOpe(), WatOperationType.Drop.toString());
        map.put(OpeartionType.JMP.getOpe(), WatOperationType.BR.toString());
        map.put(OpeartionType.SFT.getOpe(), WatOperationType.LeftShift.toString());
    }

}
