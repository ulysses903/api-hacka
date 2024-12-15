package br.com.payment.application.infra;

import br.com.payment.application.infra.GeneralConfiguration;
import org.junit.jupiter.api.Test;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class GeneralConfigurationTest {

    @Test
    void testModelMapperConfiguration() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(GeneralConfiguration.class);

        ModelMapper modelMapper = context.getBean(ModelMapper.class);

        assertNotNull(modelMapper, "ModelMapper bean should not be null");

        Configuration config = modelMapper.getConfiguration();
        assertEquals(MatchingStrategies.STRICT, config.getMatchingStrategy(), "Matching strategy should be STRICT");

        assertEquals(Conditions.isNotNull(), config.getPropertyCondition(), "Property condition should be isNotNull");

        context.close();
    }
}
