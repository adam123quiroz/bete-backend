package bo.edu.ucb.betebackend.domain.service;

import java.nio.file.Paths;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import static spark.Spark.port;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

public class PaymentStripeService {
    private static Gson gson = new Gson();

    static class CreatePayment {
        @SerializedName("items")
        Object[] items;

        public Object[] getItems() {
            return items;
        }
    }

    static class CreatePaymentResponse {
        private String clientSecret;

        public CreatePaymentResponse(String clientSecret) {
            this.clientSecret = clientSecret;
        }
    }

    static int calculateOrderAmount(Object[] items) {
        // Replace this constant with a calculation of the order's amount
        // Calculate the order total on the server to prevent
        // users from directly manipulating the amount on the client
        return 1400;
    }

    public static void main(String[] args) {
        port(4242);
        staticFiles.externalLocation(Paths.get("").toAbsolutePath().toString());
        // This is a sample test API key. Sign in to see examples pre-filled with your key.
        Stripe.apiKey = "sk_test_4eC39HqLyjWDarjtT1zdp7dc";
        post("/create-payment-intent", (request, response) -> {
            response.type("application/json");
            // first step
            PaymentIntentCreateParams params =
                    PaymentIntentCreateParams.builder()
                            .setCurrency("usd")
                            .setAmount(1099L)
                            // Verify your integration in this guide by including this parameter
                            .putMetadata("integration_check", "accept_a_payment")
                            .build();

            PaymentIntent intent = PaymentIntent.create(params);
            CreatePaymentResponse paymentResponse = new CreatePaymentResponse(intent.getClientSecret());
            return gson.toJson(paymentResponse);
        });
    }
}