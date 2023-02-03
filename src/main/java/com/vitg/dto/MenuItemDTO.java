package com.vitg.dto;

import java.util.List;

import lombok.Data;

@Data
public class MenuItemDTO {
	
	private String displayName;
	private List<MenuItemChildDTO> children;

}
