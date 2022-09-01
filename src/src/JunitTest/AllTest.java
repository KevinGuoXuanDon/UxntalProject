package JunitTest;

import Model.TokenClass.AddressLabel;
import Model.TokenClass.TokenObject;
import org.junit.Before;
import org.junit.Test;
import Model.*;
import utils.*;
import utils.WatCodeGenerate.WatBlockWrite;
import utils.WatCodeGenerate.ZeroPageConvert;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.LinkedList;

public class AllTest {
    static ReadAndClean read = new ReadAndClean();
    static TokenObjectMapping mapping = new TokenObjectMapping();
    static TransferMap transferMap = new TransferMap();
    static WatBlockWrite writer = new WatBlockWrite();
    static ZeroPageConvert zeroPageConvert = new ZeroPageConvert();
    @Before
    public void beforeTest() throws IOException {
        writer.writer = new FileWriter(System.getProperty("user.dir") + "/output/", true);
    }
    @Test
    public void commentClear(){
        read.clearComment(" (a)(b) ");
        read.clearComment(" ((ab)) ");
        read.clearComment(" (ab) ");
    }
    @Test
    public void readByWord(){
        read.readUnxtalByWord();
    }
    @Test
    public void readByLine(){
        read.readUnxtalByLine();
    }
    @Test
    public void splitBlock(){
        read.splitBlock(read.readUnxtalByWord());
    }
    @Test
    public void pathValid(){
        read.getPath();
    }
    @Test
    public void functionMappingTest(){
        List<TokenObject> list = new LinkedList<>();
        mapping.FunctionConvert(list);
    }
    @Test
    public void singleMapping() throws Exception {
        mapping.getSingleMapping("a");
    }
    @Test
    public void allMappingTest() throws Exception {
        mapping.tokenObjectMapping(new ArrayList<>());
    }
    @Test
    public void functionDetermine(){
        mapping.functionDetermin(new AddressLabel());
    }
    @Test
    public void emptyListTest(){
        List<Function> list =mapping.functionList;
    }
    @Test
    public void labelListTest(){
        List<AddressLabel> list = mapping.lableList;
    }
    @Test
    public void transfermapTest(){
        transferMap.map.get("ADD");
    }
    @Test
    public void writeFuncitontest() throws IOException {
        writer.functionCreateModel(new Function());
    }
    @Test
    public void fileCreateTest(){
        String path = System.getProperty("user.dir") + "/output/";
        writer.fileCreate(path, "test.wat");
    }
    @Test
    public void importLogTest() throws IOException {
        writer.importLog();
    }
    @Test
    public void mainFunctionCreateTest() throws IOException {
        writer.mainFunctionCreate(new ArrayList<>());
    }
    @Test
    public void subFunctionTest() throws IOException {
        writer.subFunctionsCreate(new ArrayList<>());
    }
    @Test
    public void convertZeroPageTest(){
        zeroPageConvert.convert(new ArrayList<>());
    }
}
