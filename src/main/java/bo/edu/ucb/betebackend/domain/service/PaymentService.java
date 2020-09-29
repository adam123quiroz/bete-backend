package bo.edu.ucb.betebackend.domain.service;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.Order;
import com.paypal.orders.OrdersCaptureRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PayPalEnvironment environment = new PayPalEnvironment.Sandbox(
            "AeYZrSqhx9iBNgawQ_oQls3K2fDMyaPUcWXTdOwDModnaqhy7UtHN3G2VnfJjBW9fogUyRNEJfbrZUbl",
            "ENc9LhRVZ6mddxL5-62LAEPfPxd71qrhjRZ-fzQaa5gbfYs0qIcqUH1e22C0l00afx2WIEdDloyX9qgG");

    private final PayPalHttpClient client = new PayPalHttpClient(environment);

    public void getOrder(String orderId) {
        Order order;
        OrdersCaptureRequest captureRequest = new OrdersCaptureRequest(orderId);
        try {
            HttpResponse<Order> response = client.execute(captureRequest);
            order = response.result();
            System.out.println("Capture ID: " + order.purchaseUnits().get(0).payments().captures().get(0).id());
            order.purchaseUnits().get(0).payments().captures().get(0).links()
                    .forEach(link -> System.out.println(link.rel() + " => " + link.method() + ":" + link.href()));

        } catch (Exception ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}
