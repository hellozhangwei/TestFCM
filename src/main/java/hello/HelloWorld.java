package hello;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

//export GOOGLE_APPLICATION_CREDENTIALS="~/Downloads/rhh-pda-4479b-firebase-adminsdk-gfujz-95df79aeaa.json"
//export GOOGLE_APPLICATION_CREDENTIALS="/home/centos/weizhang/TestFCM/rhh-pda-4479b-firebase-adminsdk-gfujz-95df79aeaa.json"
public class HelloWorld {
    public static void main(String[] args) throws Exception{

        System.out.println("Init...");
        init();
        System.out.println("Init done!");
        sendMessage();
    }
    public static void init() throws IOException{

        FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.getApplicationDefault())
                    //.setDatabaseUrl("https://<DATABASE_NAME>.firebaseio.com/")
                    .build();

        FirebaseApp.initializeApp(options);
    }

    public static void sendMessage() throws FirebaseMessagingException{
        System.out.println("Sending message... ");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        TimeZone timeZone = TimeZone.getDefault();

        String topic = "rhh-1492-newJob";

        Lorem lorem = LoremIpsum.getInstance();

        Message message = Message.builder()
                .setTopic("rhh-1492-newJob")
                .putData("title","[Title] " + lorem.getTitle(5))
                .putData("body","[Title] " + "Server Time " + sdf.format(date) + " " + timeZone.getID() + "\r\n" + "[Body] "  + lorem.getWords(10))
                .putData("sendTime", date.getTime() + "")
                .setAndroidConfig(AndroidConfig.builder().setPriority(AndroidConfig.Priority.HIGH).build())
                //.setApnsConfig(ApnsConfig.builder().setAps(Aps.builder().setAlert("aps alert test").build()).putHeader("apns-priority", "10").build())
                .build();

        String response = FirebaseMessaging.getInstance().send(message);

        System.out.println("Message response: " + response);

    }
}