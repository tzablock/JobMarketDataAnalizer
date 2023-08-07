package com.jobdata.entity.joboffer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;


public class JobOfferSerializer implements Serializer<JobOffer> {
    private final ObjectMapper objectMapper;

    public JobOfferSerializer() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public byte[] serialize(String s, JobOffer jobOffer) {
        try {
            return objectMapper.writeValueAsBytes(jobOffer);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
