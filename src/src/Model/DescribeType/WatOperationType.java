package Model.DescribeType;

import java.util.Locale;

public enum WatOperationType {
    // Declare const
    Constant("const",1,"declare numbers."),
    // Compare
    Equal("eq",2,""), NotEqual("ne",3,""), GreaterThan("gt_u",4,""), LessThan("ls_u",5,""), GreaterOrEqual("ge_u",6,""), LessOrEqual("le_u",7,""),
    // Operation
    Add("add",8,""), Sub("sub",9,""), Mul("mul",10,""), Div("div_u",11,""), Remainder("rem_u",12,"calculate mod"),
    // Type Convert
    Extend,Wrap,Promote,Demote,Convert,Truncate,
    // Math
    Min("min"),Max("max"),Nearest,Ceil,Floor,Abs("abs"),Negate("neg"),SquareRoot,CopySign,
    // logical
    AND("and"),OR("or"),LeftShift("shl"), RightShift("shr_u"),
    // variable
    Local("local",100,"(local $var i32)"),LocalGet("local.get",101,"local.get $var"),LocalSet("local.set",102,"(local.set $var (i32.const 10))"), Global("global",103,"(global $from_wasm i32 (i32.const 10))"),
    GlobalGet("global.get",104,"global.get $var "),GLobalSet("global.set",105,"global.set $var"),
    // memory
    Grow,Size,Load,Store,
    // Control Flow
    Block("block",200,""),BR("br",201,""),Call("call",202,""),Drop("drop",203,""),IFELSE,Loop("loop",204,"The loop statement creates a label that can later be branched to with a br"),Return("return",205,"returns from a function."),Select("select",206,"selects one of its first two operands based on whether its third operand is zero or not");

    private String command;
    private String explain;
    private int index;

    WatOperationType(String command, int index, String explain) {
        if(index<100)
            this.command = "i32."+command;
        else
            this.command = command;
        this.index= index;
        this.explain = explain;
    }
    WatOperationType(String command){
        this.command = command;
    }
    WatOperationType(){}

    @Override
    public String toString() {
        return command;
    }
}
