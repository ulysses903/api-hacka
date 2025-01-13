package br.com.payment.application.controllers;

import br.com.payment.application.service.CreateVideoProcessingDTO;
import br.com.payment.application.service.VideoProcessingApplicationService;
import br.com.payment.application.service.VideoProcessingDTO;
import br.com.payment.domain.core.domain.entities.VideoProcessing;
import br.com.payment.infra.repository.VideoProcessingRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/videoProcessing")
@AllArgsConstructor
public class VideoProcessingController {

    private final VideoProcessingRepository videoProcessingRepository;
    private final VideoProcessingApplicationService videoProcessingApplicationService;

    @GetMapping()
    public ResponseEntity<List<VideoProcessing>> getVideosProcessing() {
        return ResponseEntity.ok(videoProcessingRepository.findAll());
    }

    @PostMapping()
    public ResponseEntity<String> createVideoProcessing(@RequestBody @Valid CreateVideoProcessingDTO createVideoProcessingDTO) {
        return ResponseEntity.ok(videoProcessingApplicationService.createVideoProcessing(createVideoProcessingDTO));
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<VideoProcessingDTO>> getVideosProcessingByUsuario(@PathVariable String usuarioId) {
        return ResponseEntity.ok(videoProcessingApplicationService.buscarTodosPorIdDoUsuario(usuarioId));
    }
}
