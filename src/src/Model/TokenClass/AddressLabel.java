package TokenClass;

public class AddressLabel {
    char indication;
    String content;
    byte byteCount;
    public AddressLabel(){
    }
    public AddressLabel(char indication, String content){
        this.indication = indication;
        this.content = content;
        if(isAboslute(this.indication)){
            byteCount = 2;
        }
        else{
            byteCount = 1;
        }
    }
    public AddressLabel(String content){
        this.indication = content.charAt(0);
        this.content = content;
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
