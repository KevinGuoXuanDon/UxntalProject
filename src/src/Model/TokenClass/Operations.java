package Model.TokenClass;

public class Operations extends TokenObject{

    String capital;
    char followed;
    public Operations(String capital, char followed){
        this.capital = capital;
        this.followed = followed;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public char getFollowed() {
        return followed;
    }

    public void setFollowed(char followed) {
        this.followed = followed;
    }

    public Operations() {

    }

    public boolean isOperations(String capital, char followed){
        for(char c : capital.toCharArray()){
            if(Character.isLowerCase(c)){
                return false;
            }
        }
        if(followed!='2' || followed!='k' || followed!='r'|| followed!='\0'){
            return false;
        }
        return true;
    }

    @Override
    public String getString() {
        return getCapital()+getFollowed();
    }
}
