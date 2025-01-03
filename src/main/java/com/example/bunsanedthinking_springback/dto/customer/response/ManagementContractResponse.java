package com.example.bunsanedthinking_springback.dto.customer.response;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagementContractResponse {
    private int id;
    private String name;
    private String type;
    private int insuranceId;
    private int ageRange;
    private int monthlyPremium;
    private String expirationDate;
    private String date;
    private Integer paymentDate;
    private String status;

    public static ManagementContractResponse of(Contract contract, Insurance insurance) {
        int id = contract.getId();
        String name = insurance.getName();
        String type = insurance.getInsuranceType().getName();
        int insuranceId = insurance.getId();
        int ageRange = insurance.getAgeRange();
        int monthlyPremium = insurance.getMonthlyPremium();
        String expirationDate = contract.getExpirationDate();
        String date = contract.getDate();
        Integer paymentDate = contract.getPaymentDate();
        String status = contract.getContractStatus().getText();
        return new ManagementContractResponse(id, name,
                type, insuranceId, ageRange, monthlyPremium,
                expirationDate, date, paymentDate, status);
    }
}
