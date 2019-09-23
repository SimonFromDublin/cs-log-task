import org.junit.Assert;
import org.junit.Test;

public class Test1 {

    @Test
    public void mySimpleEqualsTest(){

        String expectedId = "zarabsvgra";
        long expextedTimestamp = 1491377495218L;
        JSONRecord jsonRecord = new JSONRecord(expectedId, expextedTimestamp);
        Assert.assertEquals(expectedId, jsonRecord.getId());
        Assert.assertEquals(expextedTimestamp, jsonRecord.getTimestamp());
    }
}