package com.example.bunsanedthinking_springback.dto.yoo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddPartnerCompanyDTO {
    private String name;
    private String phoneNumber;
    private int partnerCompanyType;
    private String headName;
    private String headPhoneNumber;
}
