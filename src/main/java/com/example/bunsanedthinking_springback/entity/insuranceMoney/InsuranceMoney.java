package com.example.bunsanedthinking_springback.entity.insuranceMoney;

import com.example.bunsanedthinking_springback.global.constants.common.CommonConstants;
import com.example.bunsanedthinking_springback.vo.InsuranceMoneyVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Administrator
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:42
 */
@NoArgsConstructor
@Data
public class InsuranceMoney {

	private String bankName;
	private String bankAccount;
	private int contractID;
	private int id;
	private String medicalCertificate;
	private String receipt;
	private String residentRegistrationCard;
	private InsuranceMoneyStatus processStatus;
	private Date applyDate;

	public InsuranceMoney(int contractId, String bankName, String bankAccount, String medicalCertificate, String receipt, String residentRegistrationCard){
		this.contractID = contractId;
		this.bankName = bankName;
		this.bankAccount = bankAccount;
		this.medicalCertificate = medicalCertificate;
		this.receipt = receipt;
		this.residentRegistrationCard = residentRegistrationCard;
		this.processStatus = InsuranceMoneyStatus.Unprocessed;
		this.applyDate = new Date();
	}
	
	private InsuranceMoney(InsuranceMoney insuranceMoney) {
		this.id = insuranceMoney.getId();
		this.contractID = insuranceMoney.getContractID();
		this.bankAccount = insuranceMoney.getBankAccount();
		this.bankName = insuranceMoney.getBankName();
		this.medicalCertificate = insuranceMoney.getMedicalCertificate();
		this.receipt = insuranceMoney.getReceipt();
		this.residentRegistrationCard = insuranceMoney.getResidentRegistrationCard();
		this.processStatus = insuranceMoney.getProcessStatus();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstants.DATE_FORMAT);
		try {
			this.applyDate = dateFormat.parse(insuranceMoney.getApplyDateStr());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public InsuranceMoneyVO findVO() {
		LocalDate lApplyDate = new java.util.Date(applyDate.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return new InsuranceMoneyVO(id, bankAccount,
				bankName, residentRegistrationCard,
				receipt, medicalCertificate,
				contractID, processStatus.ordinal(),
				lApplyDate);
	}
	
	public InsuranceMoney clone() {
		return new InsuranceMoney(this);
	}

	public void handle(){
		this.processStatus = InsuranceMoneyStatus.Completed;
	}

	public String getApplyDateStr() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstants.DATE_FORMAT);
        return dateFormat.format(this.applyDate);
	}

}