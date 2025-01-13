package br.com.payment.application.service;

import br.com.payment.domain.core.domain.entities.ProcessingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VideoProcessingDTO {

    private String urlDoVideo;
    private String urlDoZip;
    private ProcessingStatus processingStatus;
}
