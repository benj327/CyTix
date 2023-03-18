package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
  
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
  
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exp5 {
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Exp5Id;
    private String name;
    private String address;
    private String code;
	public Long getExp5Id() {
		return Exp5Id;
	}
	public void setExp5Id(Long exp5Id) {
		Exp5Id = exp5Id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}