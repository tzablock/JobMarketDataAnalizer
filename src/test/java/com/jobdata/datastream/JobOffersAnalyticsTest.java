package com.jobdata.datastream;

import com.jobdata.entity.joboffer.JobOffer;
import com.jobdata.entity.joboffer.JobOfferDeserializer;
import com.jobdata.entity.joboffer.JobOfferSerializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JobOffersAnalyticsTest {
    private final String inputTopicName = "TestTopic";
    private final String outputTopicName = "output-topic";
    private final JobOffersAnalytics out = new JobOffersAnalytics(inputTopicName);

    @Test
    void exampleTest() {
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        out.processIncomingJobs(streamsBuilder);
        Topology topology = streamsBuilder.build();

        try (TopologyTestDriver topologyTestDriver = new TopologyTestDriver(topology)){
            final String testJobName = "testName";
            final String testDescriptionName = "testDescription";
            final JobOffer testJobOffer = JobOffer.builder().jobName(testJobName).description(testDescriptionName).build();
            TestInputTopic<String, JobOffer> inputTopic = topologyTestDriver.createInputTopic(inputTopicName, new StringSerializer(), new JobOfferSerializer());
            TestOutputTopic<String, JobOffer> outputTopic = topologyTestDriver.createOutputTopic(outputTopicName, new StringDeserializer(), new JobOfferDeserializer());
            inputTopic.pipeInput("key", testJobOffer);

            assertThat(outputTopic.readKeyValue())
                    .isEqualTo(KeyValue.pair("key", testJobOffer));
        }
    }
}