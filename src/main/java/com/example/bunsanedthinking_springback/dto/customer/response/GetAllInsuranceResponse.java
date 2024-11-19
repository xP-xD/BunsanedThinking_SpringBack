package com.example.bunsanedthinking_springback.dto.customer.response;

import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllInsuranceResponse {
    private String name;
    private String insuranceType;
    private int id;
    private int ageRange;
    private int monthlyPremium;

    public static GetAllInsuranceResponse of(Insurance insurance) {
        String name = insurance.getName();
        String insuranceType = insurance.getInsuranceType().getName();
        int id = insurance.getId();
        int ageRange = insurance.getAgeRange();
        int monthlyPremium = insurance.getMonthlyPremium();
        return new GetAllInsuranceResponse(name,
                insuranceType, id, ageRange, monthlyPremium);
    }
}
