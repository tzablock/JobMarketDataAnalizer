package com.jobdata.entity.joboffer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class JobOfferDeserializer implements Deserializer<JobOffer> {
    private final ObjectMapper objectMapper;

    public JobOfferDeserializer() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public JobOffer deserialize(String s, byte[] bytes) {
        try {
            return objectMapper.readValue(bytes, JobOffer.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
