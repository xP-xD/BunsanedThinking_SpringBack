package com.example.bunsanedthinking_springback.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.InsuranceVO;

@Mapper
public interface InsuranceMapper {
	InsuranceVO get_SalesModel(int id);
}
