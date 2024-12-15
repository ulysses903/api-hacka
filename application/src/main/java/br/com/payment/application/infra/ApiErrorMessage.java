package br.com.payment.application.infra;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiErrorMessage {

    private HttpStatus status;
    private List<String> errors;
}
