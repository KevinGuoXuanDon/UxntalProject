package Model.TokenClass;

public class Padding {
    char indication;
    String content;
    public Padding(char indication, String content){
        this.indication = indication;
        this.content = content;
    }

    public boolean isPadding(char indication){
        return isAbsolute(indication) || isRelative(indication);
    }

    public boolean isAbsolute(char indication){
        return indication =='|';
    }

    public boolean isRelative(char indication){
        return indication == '$';
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
}
