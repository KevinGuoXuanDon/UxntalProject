package Model.TokenClass;

import java.util.ArrayList;
import java.util.List;

public class AddressLabel {
    char indication;

    public char getIndication() {
        return indication;
    }

    public void setIndication(char indication) {
        this.indication = indication;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public byte getByteCount() {
        return byteCount;
    }

    public void setByteCount(byte byteCount) {
        this.byteCount = byteCount;
    }

    List<String> content;
    byte byteCount;
    public AddressLabel(){
    }
    public AddressLabel(char indication, List<String> content){
        this.indication = indication;
        this.content = new ArrayList<>(content);
        if(isAboslute(this.indication)){
            byteCount = 2;
        }
        else{
            byteCount = 1;
        }
    }
    public AddressLabel(List<String> content){
        this.indication = content.get(0).charAt(0);
        this.content = new ArrayList<>(content);
        if(isAboslute(this.indication)){
            byteCount = 2;
        }
        else{
            byteCount = 1;
        }
    }

    public boolean isAddressLabel(char indication){
        return isAboslute(indication) || isRelative(indication);
    }
    public boolean isAboslute(char indication){
        return indication =='@';
    }
    public boolean isRelative(char indication){
        return indication =='&';
    }


}
