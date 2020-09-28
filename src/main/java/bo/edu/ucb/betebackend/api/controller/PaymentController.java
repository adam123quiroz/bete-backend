package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.domain.dto.FormatResponse;
import bo.edu.ucb.betebackend.domain.dto.OrderId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/payment")
public class PaymentController {
/*    final private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }*/

    @CrossOrigin
    @PostMapping("/get-paypal-transaction")
    public ResponseEntity<FormatResponse<Map<String, String>>> createPayment(@RequestBody OrderId orderId) {
        System.out.println(orderId.getOrderID());
        Map<String, String> map = new HashMap<>();
        map.put("MRd", "fuck you");
        return new ResponseEntity<>(new FormatResponse<>(null, map), HttpStatus.OK);
    }
}
