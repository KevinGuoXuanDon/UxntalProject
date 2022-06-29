package Model.TokenClass;

public class Literal {
    String indication;
    String followingContent;
    public Literal(String indication, String content){
        this.indication = indication;
        this.followingContent = content;
    }
    public Literal(String indication){
        this.indication = indication;
    }
    public String divideAndConvert(){
        if(!followingContent.equals("")){
            return '#' +" "+ Integer.valueOf(followingContent,16);
        }
        else
            return "#";
    }

    public boolean isLiteral(String indication){
        return indication.equals("#") || indication.equals("LIT");
    }
}
