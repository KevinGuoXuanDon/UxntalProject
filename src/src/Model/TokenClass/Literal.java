package Model.TokenClass;

public class Literal extends TokenObject{
    String indication;
    String followingContent;
    public String getIndicationLit() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public String getFollowingContent() {
        return followingContent;
    }

    public void setFollowingContent(String followingContent) {
        this.followingContent = followingContent;
    }


    public Literal(){}
    public Literal(String indication, String content) throws Exception {
        this.indication = Convert(indication);
        this.followingContent = divideAndConvert(content);
    }
    public Literal(String indication){
        this.indication = indication;
    }

    public String Convert(String indication) throws Exception {
        if(indication.equals("LIT") || indication.equals("#")){
            return "#";
        }
        else throw new Exception("invalid indication");
    }

    public String divideAndConvert(String followingContent){
        if(!followingContent.equals("")){
            return Integer.valueOf(followingContent,16)+"";
        }
        else
            return null;
    }

    public boolean isLiteral(String indication){
        return indication.equals("#") || indication.equals("LIT");
    }

    @Override
    public String getString() {
        return getIndicationLit()+getFollowingContent();
    }
}
