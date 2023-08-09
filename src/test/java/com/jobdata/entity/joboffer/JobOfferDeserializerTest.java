package com.jobdata.entity.joboffer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JobOfferDeserializerTest {
    private final JobOfferDeserializer out = new JobOfferDeserializer();

    @Test
    void deserializedForJsonContainingExampleJobNameAndDescriptionShouldReturnJobOfferObject() {
        String exampleJobName = "TestJobName";
        String exampleDescription = "TestJobDescription";
        String jobOfferJson = String.format("{\"jobName\": \"%s\",\"description\":\"%s\"}",exampleJobName, exampleDescription);
        JobOffer result = out.deserialize("", jobOfferJson.getBytes());
        assertThat(result.getJobName()).isEqualTo(exampleJobName);
        assertThat(result.getDescription()).isEqualTo(exampleDescription);
    }
}