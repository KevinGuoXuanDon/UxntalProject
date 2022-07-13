package Model.TokenClass;


public class RawConstant extends TokenObject{
    String content;
    public RawConstant(String content){
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public RawConstant() {

    }

    // is there any special character?
    public boolean isRawContent(String content){
        for(char c:content.toCharArray()){
            if(!Character.isLetterOrDigit(c)){
                return false;
            }
        }
        return true;
    }

    @Override
    public String getString() {
        return getContent();
    }
}
