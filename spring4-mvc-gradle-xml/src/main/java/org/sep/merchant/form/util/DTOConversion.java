package org.sep.merchant.form.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.sep.merchant.form.dto.RiskItemDTO;
import org.sep.merchant.form.model.RiskItem;

public class DTOConversion {

	public static List<RiskItemDTO> getRiskItemDTOs(Set<RiskItem> riskItems){
		List<RiskItemDTO> riskItemDTOs = new ArrayList<RiskItemDTO>();
		
		for(RiskItem ri : riskItems){
			RiskItemDTO riDTO = new RiskItemDTO();
			riDTO.setId(ri.getId());
			riDTO.setName(ri.getName());
			riskItemDTOs.add(riDTO);
		}
		
		return riskItemDTOs;
		
	}
}
