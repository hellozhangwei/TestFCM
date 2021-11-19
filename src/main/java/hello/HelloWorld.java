package hello;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        String topic = "rhh-1492-newJob";

        /*Message message = Message.builder()
                .putData("score", "850")
                .putData("time", "2:45")
                .setTopic(topic)
                .build();*/

        Message message = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(sdf.format(date) + " : Test is message title ")
                        .setBody("This message body. Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
                        .build())
                .setTopic("rhh-1492-newJob")
                .putData("sendTime", date.getTime() + "")
                .setAndroidConfig(AndroidConfig.builder().setPriority(AndroidConfig.Priority.HIGH).build())
                //.setApnsConfig(ApnsConfig.builder().setAps(Aps.builder().setAlert("aps alert test").build()).putHeader("apns-priority", "10").build())
                .build();

        String response = FirebaseMessaging.getInstance().send(message);

        System.out.println("Message response: " + response);

    }
}

