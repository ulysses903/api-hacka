package br.com.payment.application.service;

import br.com.payment.domain.core.domain.entities.VideoProcessing;
import br.com.payment.infra.repository.VideoProcessingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoProcessingApplicationService {

    private final VideoProcessingRepository videoProcessingRepository;

    public String createVideoProcessing(CreateVideoProcessingDTO createVideoProcessingDTO) {
        VideoProcessing videoProcessing = videoProcessingRepository.save(new VideoProcessing(createVideoProcessingDTO.getUsuarioId(), createVideoProcessingDTO.getUrlDoVideo()));
        return videoProcessing.getId();
    }

    public List<VideoProcessingDTO> buscarTodosPorIdDoUsuario(String usuarioId) {
        List<VideoProcessing> videosProcessing = videoProcessingRepository.findAllByUsuarioId(usuarioId);
        return videosProcessing.stream().map(videoProcessing -> new VideoProcessingDTO(videoProcessing.getUrlDoVideo(), videoProcessing.getUrlDoZip(), videoProcessing.getProcessingStatus())).toList();
    }
}
