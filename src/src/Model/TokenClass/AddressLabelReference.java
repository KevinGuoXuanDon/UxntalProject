package Model.TokenClass;


public class AddressLabelReference extends TokenObject{
    char indication;
    String content;
    public AddressLabelReference(char indication, String content){
        this.indication = indication;
        this.content = content;
    }

    public AddressLabelReference() {

    }
    // indication ';' call @ (abs), ',&' call '&'  in zero-page we use '.' call @

    public boolean isAddressLabelRefrence(char indication, String content){
        return indication ==';' || indication=='.' || (indication==',' && content.charAt(0)=='&');
    }

    public char getIndication() {
        return indication;
    }

    public void setIndication(char indication) {
        this.indication = indication;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getString() {
        return getIndication()+getContent();
    }
}
