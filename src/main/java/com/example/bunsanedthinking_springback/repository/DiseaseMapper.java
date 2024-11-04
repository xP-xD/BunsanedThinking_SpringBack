package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.DiseaseVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface DiseaseMapper {
    public Optional<DiseaseVO> getDiseaseById_Customer(int productID);

    public List<DiseaseVO> getAll_Customer();
}
