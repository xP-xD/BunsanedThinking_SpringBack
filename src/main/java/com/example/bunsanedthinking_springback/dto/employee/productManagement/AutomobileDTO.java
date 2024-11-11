package com.example.bunsanedthinking_springback.dto.employee.productManagement;

import java.util.List;

import com.example.bunsanedthinking_springback.entity.insurance.ServiceType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AutomobileDTO {
	private String name;
	private Integer maximumMoney;
	private Integer insuranceType;
	private Integer monthlyPremium;
	private Integer contractPeriod;
	private String coverage;
	private Integer vehicleType;
	private Integer accidentLimit;
	private List<ServiceType> serviceList;
}