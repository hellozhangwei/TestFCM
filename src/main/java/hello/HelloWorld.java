package hello;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import java.io.FileInputStream;
import java.io.IOException;

//export GOOGLE_APPLICATION_CREDENTIALS="~/Downloads/rhh-pda-4479b-firebase-adminsdk-gfujz-95df79aeaa.json"
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

        String topic = "rhh-1492-newJob";

        Message message = Message.builder()
                .putData("score", "850")
                .putData("time", "2:45")
                .setTopic(topic)
                .build();

        String response = FirebaseMessaging.getInstance().send(message);

        System.out.println("Sent message response: " + response);

    }
}

