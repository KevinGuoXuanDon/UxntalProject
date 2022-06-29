package Model.TokenClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RawConstant {
    String content;
    public RawConstant(String content){
        this.content = content;
    }

    // is there any special character?
    public boolean isRawContent(String content){
        String regEx = "[ _`[email protected]#$%^&*()+=|{}‘:;‘,\\[\\].<>/?~！@#￥%……&*()——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(content);
        return m.find();
    }
}
