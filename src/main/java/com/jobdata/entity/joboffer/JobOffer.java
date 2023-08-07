package com.jobdata.entity.joboffer;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOffer { //TODO change according to other service
    private String jobName;
    private String description;
}
