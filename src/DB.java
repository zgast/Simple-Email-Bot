package at.markus;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.json.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class DB {


    public static void JSONwrite(String name, String startday, String timespan, String email, String subject, String text) {
        JsonArray value = JSONread();
        int i = 0;

        Map<String, ?> config = null;
        JsonBuilderFactory factory = Json.createBuilderFactory(config);
        JsonArrayBuilder array = factory.createArrayBuilder();
        ((JsonArrayBuilder) array).add(factory.createObjectBuilder()
                .add("name", name)
                .add("stardate", startday)
                .add("timespan", timespan)
                .add("email", email)
                .add("subject", subject)
                .add("text", text));

        while (true) {
            try {
                JsonObject objtmp = value.getJsonObject(i);
                i++;
                ((JsonArrayBuilder) array).add(objtmp);
            } catch (Exception e) {
                break;
            }
        }
        JsonArray arr = array.build();


        try {
            FileWriter fw = new FileWriter("data.json");
            JsonWriter jsonWriter = Json.createWriter(fw);
            jsonWriter.writeArray(arr);
            jsonWriter.close();
            fw.close();
        } catch (IOException e) {
        }
    }

    public static void delete(String name){
        JsonArray value = JSONread();

        int i = 0;

        Map<String, ?> config = null;
        JsonBuilderFactory factory = Json.createBuilderFactory(config);
        JsonArrayBuilder array = factory.createArrayBuilder();
        while (true) {
            JsonObject objtmp;
            try {
                objtmp = value.getJsonObject(i);
                i++;
                if(!objtmp.get("name").toString().equals(name)){
                    ((JsonArrayBuilder) array).add(objtmp);
                }
            } catch (Exception e) {
                break;
            }

        }
        JsonArray arr = array.build();


        try {
            FileWriter fw = new FileWriter("data.json");
            JsonWriter jsonWriter = Json.createWriter(fw);
            jsonWriter.writeArray(arr);
            jsonWriter.close();
            fw.close();
        } catch (IOException e) {
        }
    }

    public static JsonArray JSONread() {
        FileReader fr;
        JsonStructure struct;
        JsonArray value = null;
        try {
            fr = new FileReader("data.json");
            JsonReader reader = Json.createReader(fr);
            struct = reader.read();
            value = (JsonArray) struct;
            reader.close();
            fr.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return value;
    }
}