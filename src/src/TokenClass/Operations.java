package TokenClass;

public class Operations {
    String capital;
    char followed;
    public Operations(String capital, char followed){
        this.capital = capital;
        this.followed = followed;
    }

    public boolean isOperations(String capital, char followed){
        for(char c : capital.toCharArray()){
            if(Character.isLowerCase(c)){
                return false;
            }
        }
        if(followed!='2' || followed!='k' || followed!='r'){
            return false;
        }
        return true;
    }
}
