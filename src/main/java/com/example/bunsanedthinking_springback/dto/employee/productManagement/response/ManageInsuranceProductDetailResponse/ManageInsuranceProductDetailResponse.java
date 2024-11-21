package com.example.bunsanedthinking_springback.dto.employee.productManagement.response.ManageInsuranceProductDetailResponse;

import com.example.bunsanedthinking_springback.entity.insurance.InsuranceType;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public abstract class ManageInsuranceProductDetailResponse {
	protected String name;
	protected InsuranceType insuranceType;
	protected Integer ageRange;
	protected String coverage;
	protected Integer monthlyPremium;
	protected Integer contractPeriod;
}