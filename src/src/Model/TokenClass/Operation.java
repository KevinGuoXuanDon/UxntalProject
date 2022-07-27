package Model.TokenClass;

import Model.DescribeType.OpeartionType;

public class Operation extends TokenObject {

    String capital;
    /**
     * Describe operation type.
     * Is ADD? SUB? MUL or other operations?  Check it from my OperationType enum class.
     */
    String operType;
    String followInfo;

    /** A Operation describe boolean arr.
     *  For ADD,    followedEffect = {false,false, false}
     *  For ADD2,   followedEffect = {true, false, false}
     *  For ADD2k,  followedEffect = {true, true, false}
     *  For ADD2kr, followedEffect = {true, true, true}
     */
    boolean[] followedEffect = new boolean[3];


    public Operation(String capital, String followInfo) {
        this.capital = capital;
        this.followInfo = followInfo;
    }
    public Operation() {

    }

    public boolean isOperations(String capital, String followInfo) {
        for (char c : capital.toCharArray()) {
            if (Character.isUpperCase(c)) {
                continue;
            }
            return false;
        }
        for(char c:followInfo.toCharArray()){
            if (c != '2' && c != 'k' && c != 'r' && c != '\0') {
                return false;
            }
        }

        return true;
    }

    @Override
    public String getString() {
        return getCapital() + getFollowInfo();
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getFollowInfo() {
        return followInfo;
    }

    public void setFollowInfo(String followInfo) {
        this.followInfo = followInfo;
        for(char c:followInfo.toCharArray()){
            if(c=='2'){
                this.followedEffect[0] = true;
            }
            else if(c=='k'){
                this.followedEffect[1] = true;
            }
            else if(c=='r'){
                this.followedEffect[2] = true;
            }
        }
    }

    public String getOperType(){ return operType; }

    public void setOperType(String type){
        try{
            this.operType = OpeartionType.valueOf(type).getOpe();
        }
        catch (IllegalArgumentException e){
            this.operType = "unknown";
        }
    }
    public boolean[] getFollowedEffect() {
        return followedEffect;
    }


}
