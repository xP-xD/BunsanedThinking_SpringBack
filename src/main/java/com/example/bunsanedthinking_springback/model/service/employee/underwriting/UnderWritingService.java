package com.example.bunsanedthinking_springback.model.service.employee.underwriting;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.dto.employee.underwriting.response.GetAllRequestingInsuranceResponse;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractStatus;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.global.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.customer.CustomerEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.product.ProductEntityModel;

@Service
public class UnderWritingService {

	@Autowired
	private ContractEntityModel contractEntityModel;
	@Autowired
	private CustomerEntityModel customerEntityModel;
	@Autowired
	private ProductEntityModel productEntityModel;

	public void applyCoperation() {

	}

	public void applyReinsurance() {

	}

	public boolean reviewAcquisition(int contractId, boolean result) throws AlreadyProcessedException {
		Contract contract = contractEntityModel.getById(contractId);
		if (contract.getContractStatus() != ContractStatus.ContractRequesting) {
			throw new AlreadyProcessedException();
		}
		if (result) {
			Product product = productEntityModel.getById(contract.getProductId());
			if (product != null) {
				contract.setExpirationDate(Date.from(LocalDate.now()
					.plusYears(((Insurance)product).getContractPeriod())
					.atStartOfDay(ZoneId.systemDefault())
					.toInstant()));
			}
			contract.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			contract.setContractStatus(ContractStatus.Maintaining);
		} else {
			contract.setContractStatus(ContractStatus.Terminating);
		}
		contractEntityModel.update(contract);
		return result;
	}

	public List<GetAllRequestingInsuranceResponse> getAllRequestingInsurance() {
		List<Contract> requestingInsurances = contractEntityModel.getAllRequestingInsurance();
		return requestingInsurances.stream()
			.map(e -> GetAllRequestingInsuranceResponse.from(customerEntityModel.getById(e.getCustomerID()),e))
			.collect(Collectors.toList());
	}

	public List<Contract> getAllNotRequestingInsurance() {
		return contractEntityModel.getAllNotRequestingInsurance();
	}

	public Customer getCustomer(int id) {
		return customerEntityModel.getById(id);
	}

	public Contract getContract(int id) {
		return contractEntityModel.getById(id);
	}

}
