package Model;

import Model.TokenClass.TokenObject;

import java.util.ArrayList;
import java.util.List;

public class FunctionDetermine {
    String name;
    final char start = '@';
    String end;
    List<String> content;
    List<TokenObject> tokenContent;
    public FunctionDetermine(){

    }
    public FunctionDetermine(String name, String end, List<String> content){
        this.name = name;
        this.end = end;
        this.content = new ArrayList<>(content);
    }

    public boolean isFunction(char start, String end){
        return (this.start == start && (end.equals("JMP2") || end.equals("JMP2r") || end.equals("JMP")));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = new ArrayList<>(content);
    }
}
