package com.jobdata.producer;

import com.jobdata.entity.joboffer.JobOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class JobOfferProducer {
    private final KafkaTemplate<String, JobOffer> kafkaTemplate;
    private final String jobsTopic;

    @Autowired
    public JobOfferProducer(KafkaTemplate<String, JobOffer> kafkaTemplate,
                            @Value("${topics.jobs-topic}") String jobsTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.jobsTopic = jobsTopic;
    }

    public void addJobOffer(JobOffer jobOffer){
        kafkaTemplate.send(jobsTopic, jobOffer);
    }
}
