package br.com.videoprocessing.application.service;

import br.com.videoprocessing.application.infra.RabbitMQConfig;
import br.com.videoprocessing.domain.core.domain.entities.VideoProcessing;
import br.com.videoprocessing.infra.repository.VideoProcessingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoProcessingApplicationService {

    private final VideoProcessingRepository videoProcessingRepository;
    private final RabbitTemplate rabbitTemplate;

    public String createVideoProcessing(CreateVideoProcessingDTO createVideoProcessingDTO) {
        VideoProcessing videoProcessing = videoProcessingRepository.save(new VideoProcessing(createVideoProcessingDTO.getUsuarioId(), createVideoProcessingDTO.getUrlDoVideo()));
        String videoProcessingId = videoProcessing.getId();
        sendVideoProcessingToQueue(videoProcessingId);
        return videoProcessingId;
    }

    private void sendVideoProcessingToQueue(String id) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.KEY_NAME, id);
        System.out.println("Mensagem enviada: " + id);
    }

    public List<VideoProcessingDTO> buscarTodosPorIdDoUsuario(String usuarioId) {
        List<VideoProcessing> videosProcessing = videoProcessingRepository.findAllByUsuarioId(usuarioId);
        return videosProcessing.stream().map(videoProcessing -> new VideoProcessingDTO(videoProcessing.getUrlDoVideo(), videoProcessing.getUrlDoZip(), videoProcessing.getProcessingStatus())).toList();
    }

    //talvez isolar em um serviço a parte
    public String proccesVideo(String videoProcessingId) {
        VideoProcessing videoProcessing = videoProcessingRepository.findById(videoProcessingId).orElseThrow(() -> new RuntimeException("Invalid id to procces video."));
        //consultar no minIO o video pela URL
        //processar o video
        //zipar imagens
        //enviar zip para o minIO
        //envolver em um try catch, em caso de erro chamar notificador de email
        return videoProcessingId;
    }
}
