package br.com.payment.infra.repository;

import br.com.payment.domain.core.domain.entities.VideoProcessing;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VideoProcessingRepository extends MongoRepository<VideoProcessing, String> {

    public List<VideoProcessing> findAllByUsuarioId(String usuarioId);
}
