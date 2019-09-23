import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;


// I did not finish this as it take more that 2 hours to complete.
// I would write a test to check JSON strings

public class Test2 {
    @Test
    public void mySimpleJSONTest(){

        String s = "";

        try{
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(s);
            //...
        }
        catch(org.json.simple.parser.ParseException e){
            e.printStackTrace();
        }


    }

}
