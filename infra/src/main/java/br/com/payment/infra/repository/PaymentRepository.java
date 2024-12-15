package br.com.payment.infra.repository;

import br.com.payment.infra.entity.PaymentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<PaymentEntity, String> {

}
