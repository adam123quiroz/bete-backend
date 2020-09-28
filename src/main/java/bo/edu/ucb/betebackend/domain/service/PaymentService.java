package bo.edu.ucb.betebackend.domain.service;

import com.braintreepayments.http.HttpResponse;
import com.braintreepayments.http.serializer.Json;
import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import com.paypal.orders.Order;
import com.paypal.orders.OrdersGetRequest;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PaymentService {
    private final PayPalEnvironment environment = new PayPalEnvironment.Sandbox(
            "AeYZrSqhx9iBNgawQ_oQls3K2fDMyaPUcWXTdOwDModnaqhy7UtHN3G2VnfJjBW9fogUyRNEJfbrZUbl",
            "ENc9LhRVZ6mddxL5-62LAEPfPxd71qrhjRZ-fzQaa5gbfYs0qIcqUH1e22C0l00afx2WIEdDloyX9qgG");

    /**
     *PayPal HTTP client instance with environment that has access
     *credentials context. Use to invoke PayPal APIs.
     */
    PayPalHttpClient client = new PayPalHttpClient(environment);

    public void getOrder(String orderId) throws IOException {
        OrdersGetRequest request = new OrdersGetRequest(orderId);
        //3. Call PayPal to get the transaction
        HttpResponse<Order> response = client.execute(request);
        //4. Save the transaction in your database. Implement logic to save transaction to your database for future reference.
        System.out.println("Full response body:");
        System.out.println(new JSONObject(new Json().serialize(response.result())).toString(4));
    }
}
