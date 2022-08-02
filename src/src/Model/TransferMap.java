package Model;


import Model.DescribeType.OpeartionType;
import Model.DescribeType.WatOperationType;

import java.util.*;


public class TransferMap {
    public static Map<String, String> map = new HashMap<>();

    static {
        map.put(OpeartionType.ADD.name(), WatOperationType.Add.toString());
        map.put(OpeartionType.SUB.name(), WatOperationType.Sub.toString());
        map.put(OpeartionType.MUL.name(), WatOperationType.Mul.toString());
        map.put(OpeartionType.DIV.name(), WatOperationType.Div.toString());
        map.put(OpeartionType.AND.name(), WatOperationType.Add.toString());
        map.put(OpeartionType.ORA.name(), WatOperationType.OR.toString());
        map.put(OpeartionType.EQU.name(), WatOperationType.Equal.toString());
        map.put(OpeartionType.NEQ.name(), WatOperationType.NotEqual.toString());
        map.put(OpeartionType.JSR.name(), WatOperationType.Call.toString());
        map.put(OpeartionType.GTH.name(), WatOperationType.GreaterThan.toString());
        map.put(OpeartionType.LTH.name(), WatOperationType.LessThan.toString());
        map.put(OpeartionType.POP.name(), WatOperationType.Drop.toString());
        map.put(OpeartionType.JMP.name(), WatOperationType.Return.toString());
        map.put(OpeartionType.SFT.name(), WatOperationType.LeftShift.toString());
        map.put(OpeartionType.DEO.name(), WatOperationType.Drop.toString() + " call $log ");
        map.put(OpeartionType.LDZ.name(), WatOperationType.GlobalGet.toString());
        map.put(OpeartionType.STZ.name(), WatOperationType.GLobalSet.toString());
        map.put(OpeartionType.DUP.name(), "local.tee $c local.get $c");
        map.put(OpeartionType.SWP.name(), "local.set $c local.set $b local.get $c local.get $b ");
        map.put(OpeartionType.NIP.name(), "i32.const 1 select");
        map.put(OpeartionType.ROT.name(), "local.set $c local.set $b local.set $a local.get $b local.get $c local.get $a");
        map.put(OpeartionType.OVR.name(), "local.set $c local.tee $b local.get $c local.get $b");
    }
}
