package com.example.bunsanedthinking_springback.model.entityModel.partnerCompany;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompanyType;
import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.model.entityModel.report.ReportEntityModel;
import com.example.bunsanedthinking_springback.repository.PartnerCompanyMapper;
import com.example.bunsanedthinking_springback.vo.PartnerCompanyVO;

@Service
public class PartnerCompanyEntityModel {
	@Autowired
	private PartnerCompanyMapper partnerCompanyMapper;
	@Autowired
	private ReportEntityModel reportEntityModel;

	public PartnerCompany getById(int id) {
		PartnerCompanyVO partnerCompanyVO = partnerCompanyMapper.getById(id).orElse(null);
		if (partnerCompanyVO == null) return null;
		ArrayList<Report> reports = new ArrayList<Report>();
		if (PartnerCompanyType.values()[partnerCompanyVO.getPartner_company_type()] ==
				PartnerCompanyType.RoadsideAssistanceCompany)
			reports = reportEntityModel.getAllByRoadSideAssistanceCId(id);
		else if (PartnerCompanyType.values()[partnerCompanyVO.getPartner_company_type()] ==
				PartnerCompanyType.DamageAssessmentCompany)
			reports = reportEntityModel.getAllByDamageAssessmentCId(id);
		return partnerCompanyVO.getEntity(reports);
	}
	public List<PartnerCompany> getAll() {
		// 이 메소드도 마찬가지 - 아래 둘을 안쓰는데 PartnerCompany 쓰시는 분은 저에게 말씀을...!
		List<PartnerCompany> partnerCompanies = new ArrayList<PartnerCompany>();
		partnerCompanyMapper.getAll()
				.forEach(e -> partnerCompanies.add(getById(e.getId())));
		return partnerCompanies;
	}

	public List<PartnerCompany> getAll_RoadsideCompany() {
		List<PartnerCompany> partnerCompanies = new ArrayList<PartnerCompany>();
		for (PartnerCompanyVO partnerCompanyVO : partnerCompanyMapper.getAll()) {
			ArrayList<Report> reports = reportEntityModel.getAllByRoadSideAssistanceCId(partnerCompanyVO.getId());
			partnerCompanies.add(partnerCompanyVO.getEntity(reports));
		}
		return partnerCompanies;
	}

	public List<PartnerCompany> getAll_DamageAssesmentCompany() {
		List<PartnerCompany> partnerCompanies = new ArrayList<PartnerCompany>();
		for (PartnerCompanyVO partnerCompanyVO : partnerCompanyMapper.getAll()) {
			ArrayList<Report> reports = reportEntityModel.getAllByDamageAssessmentCId(partnerCompanyVO.getId());
			partnerCompanies.add(partnerCompanyVO.getEntity(reports));
		}
		return partnerCompanies;
	}

	public Integer getMaxId() {
		return partnerCompanyMapper.getMaxId();
	}

	public void add(PartnerCompany partnerCompany) {
		if (partnerCompany == null) return;
		if (partnerCompanyMapper.getById(partnerCompany.getId()).isPresent()) return;
		partnerCompanyMapper.insert(partnerCompany.findVO());

		List<Report> reports = partnerCompany.getReportList();
		if (reports != null) reports.forEach(e -> reportEntityModel.add(e));
	}

	public void update(PartnerCompany partnerCompany) {
		if (partnerCompany == null) return;
		if (partnerCompanyMapper.getById(partnerCompany.getId()).isEmpty()) return;

		List<Report> reports = partnerCompany.getReportList();
		if (reports != null) reports.forEach(e -> reportEntityModel.update(e));

		partnerCompanyMapper.update(partnerCompany.findVO());
	}
	public void delete(int id) {
		if (partnerCompanyMapper.getById(id).isEmpty()) return;
		PartnerCompany partnerCompany = getById(id);

		List<Report> reports = partnerCompany.getReportList();
		if (reports != null) reports.forEach(e -> reportEntityModel.delete(id));

		partnerCompanyMapper.deleteById(id);
	}
}