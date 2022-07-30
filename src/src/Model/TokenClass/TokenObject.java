package Model.TokenClass;

public class TokenObject {
    String str;
    String type;
    byte byteCount;

    public String getString(){
        if(str==null)
            return "default";
        return str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public byte getByteCount() {
        return byteCount;
    }

    public void setByteCount(byte byteCount) {
        this.byteCount = byteCount;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
