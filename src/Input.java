package at.markus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input {

    static BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
    static DB db = new DB();

    public static void console() throws IOException {
        String ip = new String();

        while(true){
            System.out.print("> ");
            ip = bufferRead.readLine();
            if(ip.equals("new")){
                Input.newMail();
            }else if(ip.equals("delete")){
                delete();
            }
            if(ip.equals("quit")){
                System.exit(0);
            }
        }
    }
    
    public static void delete(){
        String name = null;
        
        System.out.print("    name        : ");
        try {
            name = bufferRead.readLine();
        } catch (IOException e) {
        }
        name = "\""+name+"\"";
        
        DB.delete(name);
    }

    private static void newMail() throws IOException {
        System.out.print("    name        : ");
        String name = bufferRead.readLine();
        System.out.print("    startday    : ");
        String startday = bufferRead.readLine();
        System.out.print("    timespan    : ");
        String timespan = bufferRead.readLine();
        System.out.print("    email       : ");
        String email = bufferRead.readLine();
        System.out.print("    subject     : ");
        String subject = bufferRead.readLine();
        System.out.print("    text        : ");
        String text = bufferRead.readLine();

        db.JSONwrite(name,startday,timespan,email,subject,text);
    }
}

