package com.example.bunsanedthinking_springback.model.domain.fixedDeposit;

import com.example.bunsanedthinking_springback.entity.loan.FixedDeposit;
import com.example.bunsanedthinking_springback.repository.FixedDepositMapper;
import com.example.bunsanedthinking_springback.repository.LoanMapper;
import com.example.bunsanedthinking_springback.repository.ProductMapper;
import com.example.bunsanedthinking_springback.vo.FixedDepositVO;
import com.example.bunsanedthinking_springback.vo.LoanVO;
import com.example.bunsanedthinking_springback.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FixedDepositDModel {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private LoanMapper loanMapper;
    @Autowired
    private FixedDepositMapper fixedDepositMapper;
    public FixedDeposit getById(int id) {
        ProductVO productVO = productMapper.getById_Customer(id).orElse(null);
        LoanVO loanVO = loanMapper.findById_LoanManagement(id).orElse(null);
        FixedDepositVO fixedDepositVO = fixedDepositMapper.getById_Customer(id).orElse(null);
        int minimumAmount = fixedDepositVO.getMinimum_amount();
        return new FixedDeposit(productVO, loanVO, minimumAmount);
    }
    public List<FixedDeposit> getAll() {
        List<FixedDeposit> fixedDeposits = new ArrayList<FixedDeposit>();
        fixedDepositMapper.getAll_Customer().stream().forEach(e -> fixedDeposits.add(getById(e.getProduct_id())));
        return fixedDeposits;
    }
    public int getMaxId() {
        return fixedDepositMapper.getMaxId();
    }
    public void add(FixedDepositVO fixedDepositVO) {
        fixedDepositMapper.insert_LoanManagement(fixedDepositVO);
    }
    public void update(FixedDepositVO fixedDepositVO) {
        fixedDepositMapper.update_LoanManagement(fixedDepositVO);
    }
    public void delete(int id) {
        fixedDepositMapper.delete_LoanManagement(id);
    }
}