package br.com.payment.application.controllers;

import br.com.payment.application.facade.PaymentFacade;
import br.com.payment.application.inout.input.FilterInput;
import br.com.payment.application.inout.input.PaymentUpdateInput;
import br.com.payment.application.inout.output.PaymentBalanceOutput;
import br.com.payment.application.inout.output.PaymentOutput;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/payment")
@AllArgsConstructor
public class PaymentController {

    private final PaymentFacade paymentFacade;

    @PutMapping
    public ResponseEntity<PaymentOutput> updatePayment(@RequestBody @Valid PaymentUpdateInput request) {
        return ResponseEntity.ok(paymentFacade.updateProcessPayment(request));
    }

    @GetMapping("/{identifierPayment}")
    public ResponseEntity<PaymentBalanceOutput> getPayment(@PathVariable String identifierPayment) {
        return ResponseEntity.ok(paymentFacade.getPayment(identifierPayment));
    }

    @GetMapping
    public ResponseEntity<Page<PaymentBalanceOutput>> getListPayments(@RequestParam(required = false) final Map<String, String> filter) {
        return ResponseEntity.ok(paymentFacade.filter(new FilterInput(filter)));
    }
}
