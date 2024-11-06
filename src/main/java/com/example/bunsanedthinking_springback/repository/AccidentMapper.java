package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.AccidentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AccidentMapper {
    public Optional<AccidentVO> getById_Customer(int id);
    public List<AccidentVO> getAllByCustomerId_Customer(int id);

    public Optional<AccidentVO> getById_Compensation(int id);
    public void updateStatus_Compensation(int processStatus, int accidentId);
    Optional<AccidentVO> findByID_CustomerSupport(int id);

	void update_CustomerSupport(AccidentVO accidentVO);

	List<AccidentVO> getAll_CustomerSupport();

	List<AccidentVO> findByProcessStatus_CustomerSupport(int processStatus);

    public int getMaxId();
    public void insert(AccidentVO accidentVO);
    public void delete(int id);
}
