package at.markus;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.time.LocalDate;

public class Checker {
    static DB db= new DB();

    public static boolean[] time(){
        JsonArray value = (JsonArray) db.JSONread();
        int  count = 0;
        int day = LocalDate.now().getDayOfYear();
        boolean[] objects = new boolean[100];
        for(int i=0;i<100;i++){
            objects[i]=false;
        }


        while(true) {
            try {
                JsonObject obj = value.getJsonObject(count);
                JsonValue tmpDayval  = obj.get("startday");
                int tmpDay = tmpDayval.hashCode();
                JsonValue tmpTimespanval  = obj.get("timespan");
                int tmpTimespan = tmpTimespanval.hashCode();
                for(int i=0;i<10000;i++){
                    if(tmpDay==0){
                        break;
                    }
                    if(tmpDay==day){
                        objects[count] = true;
                        break;
                    }else if(tmpDay+tmpTimespan%365==day){
                        objects[count] = true;
                        break;
                    }
                }
                count++;
            } catch (Exception e) {
                break;
            }
        }
        return objects;
    }

    public static void check(){
        while (true){
            boolean[] objects = time();

            for(int i=0;i<100;i++){
                if(objects[i]){
                    JsonArray value = (JsonArray) db.JSONread();
                    JsonObject obj = value.getJsonObject(i);
                    String email  = obj.get("email").toString();
                    String subject  = obj.get("subject").toString();
                    String text  = obj.get("text").toString();

                    Sending.sendMail(email,subject,text);
                }
            }

            try {
                Thread.sleep(1800000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
