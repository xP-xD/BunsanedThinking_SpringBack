package com.example.bunsanedthinking_springback.dto.customer.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class AskInsuranceCounselDTO {
    private int customerId;
    private int insuranceId;
//    private String name;
//    private String phoneNumber;
    private Date counselDate;
//    private String job;
//    private int age;
//    private int gender;
}