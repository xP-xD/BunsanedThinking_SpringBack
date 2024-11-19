package com.example.bunsanedthinking_springback.controller.employee;

import com.example.bunsanedthinking_springback.dto.employee.contractManagement.response.*;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.endorsment.Endorsement;
import com.example.bunsanedthinking_springback.entity.recontract.Recontract;
import com.example.bunsanedthinking_springback.entity.revival.Revival;
import com.example.bunsanedthinking_springback.entity.termination.Termination;
import com.example.bunsanedthinking_springback.global.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.global.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.service.employee.contractManagement.ContractManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/contractManagement")
public class ContractManagementController {
	@Autowired
	private ContractManagementService contractManagementSModel;
	@PatchMapping("/requestTerminationFee")
	public void requestTerminationFee(@RequestParam int tercontractId, @RequestParam int customerId)
		throws NotExistContractException, AlreadyProcessedException, NotExistException {
		// 예시URL - http://localhost:8080/employee/contractManagement/requestTerminationFee?tercontractId=1002&customerId=2002
		contractManagementSModel.requestTerminationFee(tercontractId, customerId);
	}

	@PatchMapping("/reviewEndorsement")
	public void reviewEndorsement(@RequestParam int endorsementId, @RequestParam int index) throws NotExistException {
		// 예시URL - http://localhost:8080/employee/contractManagement/reviewEndorsement?endorsementId=1002&index=1
		contractManagementSModel.reviewEndorsement(endorsementId, index);
	}

	@PatchMapping("/reviewRecontract")
	public void reviewRecontract(@RequestParam int recontractId, @RequestParam int index) throws
		NotExistContractException,
		NotExistException {
		// 예시URL - http://localhost:8080/employee/contractManagement/reviewRecontract?recontractId=1001&index=1
		contractManagementSModel.reviewRecontract(recontractId, index);
	}

	@PatchMapping("/reviewRevival")
	public void reviewRevival(@RequestParam int revivalId, @RequestParam int index) throws NotExistContractException {
		// 예시URL - http://localhost:8080/employee/contractManagement/reviewRevival?revivalId=1001&index=1
		contractManagementSModel.reviewRevival(revivalId, index);
	}
	@GetMapping("/getAllDefaultContract")
	public List<GetAllDefaultContractResponse> getAllDefaultContract() throws NotExistContractException, NotExistException {
		return contractManagementSModel.getAllDefaultContract();
	}

	@GetMapping("/getCustomerById")
	public Customer getCustomerById(@RequestParam int id) throws NotExistException, NotExistContractException {
		return contractManagementSModel.getCustomerById(id);
	}

	@GetMapping("/getContractById")
	public Contract getContractById(@RequestParam int id) throws NotExistContractException, NotExistException {
		return contractManagementSModel.getContractById(id);
	}

	@GetMapping("/getContractRowById")
	public GetAllDefaultContractResponse getContractRowById(@RequestParam int id) throws NotExistContractException, NotExistException {
		return contractManagementSModel.getContractRowById(id);
	}

	@GetMapping("/getTerminationById")
	public Termination getTerminationById(@RequestParam int id) throws NotExistContractException, NotExistException {
		return contractManagementSModel.getTerminationById(id);
	}

	@GetMapping("/getTerminationRowById")
	public GetAllTerminatingContractResponse getTerminationRowById(@RequestParam int id) throws NotExistContractException, NotExistException {
		return contractManagementSModel.getTerminationRowById(id);
	}

	@GetMapping("/getTerminatingContractById")
	public Termination getTerminatingContractById(@RequestParam int id) throws
			NotExistContractException,
			NotExistException {
		// ## getTerminationById랑 중복!!
		return contractManagementSModel.getTerminatingContractById(id);
	}

	@GetMapping("/getAllTerminatingContract")
	public List<GetAllTerminatingContractResponse> getAllTerminatingContract() throws NotExistContractException, NotExistException {
		return contractManagementSModel.getAllTerminatingContract();
	}

	@GetMapping("/getAllUnprocessedTerminatingContract")
	public List<GetAllTerminatingContractResponse> getAllUnprocessedTerminatingContract() throws NotExistContractException, NotExistException {
		return contractManagementSModel.getAllUnprocessedTerminatingContract();
	}

	@GetMapping("/getAllProcessedTerminatingContract")
	public List<GetAllTerminatingContractResponse> getAllProcessedTerminatingContract() throws NotExistContractException, NotExistException {
		return contractManagementSModel.getAllProcessedTerminatingContract();
	}

	@GetMapping("/getEndorsementById")
	public Endorsement getEndorsementById(@RequestParam int id) throws NotExistContractException, NotExistException {
		return contractManagementSModel.getEndorsementById(id);
	}

	@GetMapping("/getEndorsementRowById")
	public GetAllEndorsementContractResponse getEndorsementRowById(@RequestParam int id) throws NotExistContractException, NotExistException {
		return contractManagementSModel.getEndorsementRowById(id);
	}

	@GetMapping("/getAllEndorsementContract")
	public List<GetAllEndorsementContractResponse> getAllEndorsementContract() throws NotExistContractException, NotExistException {
		return contractManagementSModel.getAllEndorsementContract();
	}

	@GetMapping("/getAllUnprocessedEndorsementContract")
	public List<GetAllEndorsementContractResponse> getAllUnprocessedEndorsementContract() throws NotExistContractException, NotExistException {
		return contractManagementSModel.getAllUnprocessedEndorsementContract();
	}

	@GetMapping("/getAllProcessedEndorsementContract")
	public List<GetAllEndorsementContractResponse> getAllProcessedEndorsementContract() throws NotExistContractException, NotExistException {
		return contractManagementSModel.getAllProcessedEndorsementContract();
	}

	@GetMapping("/getReContractById")
	public Recontract getReContractById(@RequestParam int id) throws NotExistContractException, NotExistException {
		return contractManagementSModel.getReContractById(id);
	}

	@GetMapping("/getReContractRowById")
	public GetAllReContractResponse getReContractRowById(@RequestParam int id) throws NotExistContractException, NotExistException {
		return contractManagementSModel.getReContractRowById(id);
	}

	@GetMapping("/getAllReContract")
	public List<GetAllReContractResponse> getAllReContract() throws NotExistContractException, NotExistException {
		return contractManagementSModel.getAllReContract();
	}

	@GetMapping("/getAllUnprocessedReContract")
	public List<GetAllReContractResponse> getAllUnprocessedReContract() throws NotExistContractException, NotExistException {
		return contractManagementSModel.getAllUnprocessedReContract();
	}

	@GetMapping("/getAllProcessedReContract")
	public List<GetAllReContractResponse> getAllProcessedReContract() throws NotExistContractException, NotExistException {
		return contractManagementSModel.getAllProcessedReContract();
	}

	@GetMapping("/getRevivalById")
	public Revival getRevivalById(@RequestParam int id) throws NotExistContractException, NotExistException {
		return contractManagementSModel.getRevivalById(id);
	}

	@GetMapping("/getRevivalRowById")
	public GetAllRevivalContractResponse getRevivalRowById(@RequestParam int id) throws NotExistContractException, NotExistException {
		return contractManagementSModel.getRevivalRowById(id);
	}

	@GetMapping("/getAllRevivalContract")
	public List<GetAllRevivalContractResponse> getAllRevivalContract() throws NotExistContractException, NotExistException {
		return contractManagementSModel.getAllRevivalContract();
	}

	@GetMapping("/getAllUnprocessedRevival")
	public List<GetAllRevivalContractResponse> getAllUnprocessedRevival() throws NotExistContractException, NotExistException {
		return contractManagementSModel.getAllUnprocessedRevival();
	}

	@GetMapping("/getAllProcessedRevival")
	public List<GetAllRevivalContractResponse> getAllProcessedRevival() throws NotExistContractException, NotExistException {
		return contractManagementSModel.getAllProcessedRevival();
	}
}
