package com.jobdata.controller;

import com.jobdata.entity.joboffer.JobOffer;
import com.jobdata.producer.JobOfferProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/job-offer-data")
class StreamManagementController {
    final private StreamsBuilderFactoryBean factoryBean;
    final private JobOfferProducer jobOfferProducer;

    @Autowired
    public StreamManagementController(StreamsBuilderFactoryBean factoryBean, JobOfferProducer jobOfferProducer) {
        this.factoryBean = factoryBean;
        this.jobOfferProducer = jobOfferProducer;
    }

    @GetMapping("/get-offers")
    List<JobOffer> getJobOffers(@RequestParam(name = "number", required = false) int n){
        return Arrays.asList(JobOffer.builder().jobName("Job1").description("sss").build(), JobOffer.builder().jobName("Job2").description("sss").build());
    }

    @PostMapping("/add-offer")
    String addOffer(@RequestBody JobOffer offer){
        jobOfferProducer.addJobOffer(offer);
        return String.format("Job offer %s added.", offer.toString());
    }
}
