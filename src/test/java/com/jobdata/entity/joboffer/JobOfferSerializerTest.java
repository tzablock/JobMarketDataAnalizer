package com.jobdata.entity.joboffer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JobOfferSerializerTest {
    private final JobOfferSerializer out = new JobOfferSerializer();

    @Test
    void serializedForJobOfferObjectContainingJobNameAndDescriptionShouldReturnJsonRepresentingThisObject() {
        String exampleJobName = "TestJobName";
        String exampleDescription = "TestJobDescription";
        JobOffer exampleJobOffer = JobOffer.builder().jobName(exampleJobName).description(exampleDescription).build();
        byte[] result = out.serialize("", exampleJobOffer);
        String jobOfferJson = new String(result);
        assertThat(jobOfferJson).isEqualTo(String.format("{\"jobName\":\"%s\",\"description\":\"%s\"}", exampleJobName, exampleDescription));
    }
}