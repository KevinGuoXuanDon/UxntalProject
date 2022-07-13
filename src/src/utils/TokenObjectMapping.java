package utils;

import Model.TokenClass.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TokenObjectMapping {
    List<AddressLabel> lableList;
    public List<TokenObject> tokenObjectMapping(List<String> list) throws Exception {
        if(list==null && list.size()==0){
            throw new Exception("You just input nothing here, plz try again");
        }
        lableList = new ArrayList<>();
        List<TokenObject> res = new ArrayList<>();
        int idx=0;
        while(idx<list.size()){
            TokenObject instance = getSingleMapping(list.get(idx));
            if (instance.getType()==null) instance.setType(""+instance.getClass());
            res.add(instance);
            // only do content fill when it's a address label, it's a bit long
            // but time complexity is O(n)
            if(instance instanceof AddressLabel){
                lableList.add((AddressLabel) instance);
                // Tokenclass is generated while adding content and child labels to the parent Label
                if(((AddressLabel) instance).isAboslute(((AddressLabel) instance).getIndication())){
                    List<TokenObject> contentToken = new ArrayList<>();
                    List<AddressLabel> subLabel = new ArrayList<>();
                    idx++;
                    while(idx<list.size()){
                        String cur = list.get(idx);
                        // end with special content
                        if(cur.equals("BRK") || cur.charAt(0)=='@' || cur.charAt(0)=='|'){
                            idx--;
                            break;
                        }
                        // add this object to res, and it's also the content of our abs Addresslable:instance
                        TokenObject content = getSingleMapping(list.get(idx));
                        if (content.getType()==null) content.setType(""+content.getClass());
                        res.add(content);
                        contentToken.add(content);
                        // if it is sub lables
                        if(content instanceof AddressLabel && ((AddressLabel) content).isRelative(((AddressLabel) content).getIndication())){
                            subLabel.add((AddressLabel) content);
                            lableList.add((AddressLabel) content);
                        }
                        idx++;
                    }
                    // AddressLabel add sub lables and content token class
                    ((AddressLabel) instance).setSubLabel(subLabel);
                    ((AddressLabel) instance).setContentToken(contentToken);
                }
            }
            // as for other labels, we directly add them
            idx++;
        }
        return res;
    }

    public TokenObject getSingleMapping(String str) throws Exception {
        if(str.trim().equals("")){
            throw new Exception("You just input nothing here, plz try again");
        }
        AddressLabel addressLabel = new AddressLabel();
        AddressLabelReference addressLabelReference = new AddressLabelReference();
        Literal literal = new Literal();
        Operations operations = new Operations();
        Padding padding = new Padding();
        RawConstant rawConstant = new RawConstant();
        char indication = str.charAt(0);
        String followContent = str.substring(1);
        if(addressLabel.isAddressLabel(indication)){
            addressLabel.setIndication(indication);
            addressLabel.setName(followContent);
            return addressLabel;
        }
        if(addressLabelReference.isAddressLabelRefrence(indication,followContent)){
            addressLabelReference.setIndication(indication);
            addressLabelReference.setContent(followContent);
            return addressLabelReference;
        }
        if(literal.isLiteral(indication+"")){
            return new Literal(""+indication,followContent);
        }
        else if(str.length()>=3 && literal.isLiteral(str.substring(0,3))){
            try {
                return new Literal(str.substring(0, 3), str.substring(3));
            }
            catch (Exception e){
                return new Literal(str.substring(0,3));
            }
        }
        if(str.length()==4 && operations.isOperations(str.substring(0,3),str.charAt(3))){
            operations.setCapital(str.substring(0,3));
            operations.setFollowed(str.charAt(3));
            return operations;
        }
        if(rawConstant.isRawContent(str)){
            rawConstant.setContent(str);
            return rawConstant;
        }
        TokenObject tokenObject = new TokenObject();
        tokenObject.setType("unknow");
        return tokenObject;
    }

    // test:
    // |00 @System [ &vector $2 &pad $6 &r $2 &g $2 &b $2 ] |20 @Screen [ &vector $2 &width $2 &height $2 &pad $2 &x $2 &y $2 &addr $2 &pixel $1 &sprite $1 ]
    public static void main(String[] args) throws Exception {
        TokenObjectMapping t = new TokenObjectMapping();
        TokenObject instance = t.getSingleMapping("@Controller");
        if(instance instanceof Literal){
            System.out.println(((Literal) instance).getIndicationLit()+((Literal) instance).getFollowingContent());
        }
        if(instance instanceof AddressLabel){
            System.out.println(((AddressLabel) instance).getIndication()+""+((AddressLabel) instance).getName());
        }

        List<String> test = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        test.addAll(Arrays.asList(sc.nextLine().trim().split("\s+")));
        List<TokenObject> res = t.tokenObjectMapping(test);
        System.out.println("Here is your Addresslable object");
        for(AddressLabel a : t.lableList){
            if(a.isAboslute(a.getIndication())){
                System.out.println("here are "+a.getString()+"'s content:");
                for(TokenObject token : a.getContentToken()){
                    System.out.print(token.getString()+"   ");
                }
            }
            else
                System.out.println(a.getIndication()+""+a.getName());
        }

//        System.out.println("res is");
//        for(TokenObject token:res){
//            if(!token.getType().equals("unknow"))
//                System.out.println(token.getString() + "  "+ token.getType());
//        }

    }
}
