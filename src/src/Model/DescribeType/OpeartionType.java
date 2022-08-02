package Model.DescribeType;

public enum OpeartionType {
    ADD("add",1),SUB("sub",2),MUL("mul",3),DIV("div",4),AND("and",5),ORA("ora",6),EOR,SFT,LNC,POP,
    NIP,SWP,ROT,DUP,OVR,LDZ,STZ,LDR,STR,LDA,
    STA,DEI,DEO,EQU,NEQ,GTH,LTH,JMP,JCN,JSR,STH;

    private String ope;
    private int index;

    OpeartionType(String ope, int index) {
        this.ope=ope;
        this.index= index;
    }
    OpeartionType(){

    }

    public String getOpe() {
        return ope;
    }

    public void setOpe(String ope) {
        this.ope = ope;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    @Override
    public String toString(){
        return this.name();
    }
}
