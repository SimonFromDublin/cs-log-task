import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    private static ArrayList<JSONRecord> logListJSON = new ArrayList<>();

    public static void main(String[] args) {
        readFromTextFile();
    }

    private static void readFromTextFile(){

        String fileName = "src/main/java/logfile.txt";
        String line = null;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                parseJsonFromString(line);
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Cannot open the file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println("File Error '" + fileName + "'");
        }
    }

    private static void parseJsonFromString(String s){

        try {
            String type="";
            String host="";
            int timestampDifference = 0;
            boolean flag;

            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(s);

            //we do not need to have status if id is unique
            String id = (String) json.get("id");
            long timestamp = (Long) json.get("timestamp");
            JSONRecord jsonRecord = new JSONRecord(id, timestamp);

            //First Element always to Array
            if(logListJSON.isEmpty()){
                logListJSON.add(jsonRecord);
            }
            else{
                //the next id needs to be compared to the elements in the list
                for(JSONRecord rdb : logListJSON){
                    //if ids are matched, it means that duration can be calculated as absolute value from (t1 - t2)
                    if(rdb.getId().equals(id)){
                        timestampDifference = Math.abs((int)(timestamp - rdb.getTimestamp()));

                        //adding the flag
                        flag = false;
                        if(timestampDifference>4){
                            flag = true;
                        }
                        if(json.containsKey("type")){
                            type = (String) json.get("type");
                        }
                        if(json.containsKey("host")){
                            host = (String) json.get("host");
                        }
                        Database db = new Database();
                        db.createTable();
                        db.insert(id, timestampDifference, type, host, flag);

                        //completed process can be removed from the list
                        logListJSON.remove(rdb);
                        break;
                    }
                }
                logListJSON.add(jsonRecord);
            }
        }
        catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }
}
