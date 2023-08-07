package com.jobdata.datastream;

import com.jobdata.entity.joboffer.JobOffer;
import com.jobdata.entity.joboffer.JobOfferDeserializer;
import com.jobdata.entity.joboffer.JobOfferSerializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JobOffersAnalytics {
    private static final Serde<JobOffer> JOB_SERDE = Serdes.serdeFrom(new JobOfferSerializer(), new JobOfferDeserializer());
    private static final Serde<String> STRING_SERDE = Serdes.String();
    private final String jobsTopic;

    @Autowired
    public JobOffersAnalytics(@Value("${topics.jobs-topic}") String jobsTopic) {
        this.jobsTopic = jobsTopic;
    }

    @Autowired
    void processIncomingJobs(StreamsBuilder streamsBuilder) {
        streamsBuilder.stream(jobsTopic, Consumed.with(STRING_SERDE, JOB_SERDE))
                .foreach((k,v) -> System.out.printf("%s=%s,%s",v.getClass(), v.getJobName(), v.getDescription()));
        //TODO add writing to database whole jobs
        //TODO add writing to database technologies grouped by amount of offers (I think in separate method)
    }
}
