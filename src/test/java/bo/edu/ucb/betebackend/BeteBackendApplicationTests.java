package bo.edu.ucb.betebackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

@SpringBootTest
class BeteBackendApplicationTests {

    @Test
    void contextLoads() {

        try {
            String url = "https://api.sandbox.paypal.com/v1/oauth2/token";
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("accept", "application/json");
            con.setRequestProperty("accept-language", "en_US");
            con.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            con.setRequestProperty("authorization", "basic QVZp**********RFY=");
            String body = "grant_type=client_credentials";

            // Send request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(body);
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Print the response
            System.out.println(response.toString());
        } catch (
                Exception e) {
            e.printStackTrace();
        }

    }

}
