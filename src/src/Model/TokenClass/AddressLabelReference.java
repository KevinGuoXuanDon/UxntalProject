package Model.TokenClass;

public class AddressLabelReference {
    char indication;
    String content;
    public AddressLabelReference(char indication, String content){
        this.indication = indication;
        this.content = content;
    }
    // indication ';' call @ (abs), ',&' call '&'  in zero-page we use '.' call @

    public boolean isAddressLabelRefrence(char indication, String content){
        return indication ==';' || indication=='.' || (content.charAt(0)==',' && content.charAt(1)=='&');
    }

    // not sure if we need it
    public void call(){

    }
}
