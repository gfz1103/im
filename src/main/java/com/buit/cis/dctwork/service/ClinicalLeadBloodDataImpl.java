package com.buit.cis.dctwork.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buit.cis.dctwork.dao.CisBxsqdDao;
import com.buit.cis.dctwork.dao.CisBxxypzDao;
import com.buit.cis.dctwork.dto.ClinicalLeadBloodFieldsDto;
import com.buit.cis.dctwork.dto.ClinicalLeadBloodParamDto;
import com.buit.cis.dctwork.enums.ExportDataTypeEnum;
import com.buit.cis.dctwork.model.CisBxxypz;
import com.buit.cis.dctwork.request.ClinicalLeadBloodReq;
import com.buit.commons.SysUser;
import com.buit.his.export.dto.JasperExportDataDto;
import com.buit.his.export.service.JasperExportDataService;
import com.buit.system.utill.ObjectToTypes;
import com.buit.utill.BeanUtil;


@Service("clinicalLeadBloodData")
public class ClinicalLeadBloodDataImpl implements JasperExportDataService<ClinicalLeadBloodReq, 
	ClinicalLeadBloodParamDto, ClinicalLeadBloodFieldsDto>{
	
	@Autowired
	private CisBxsqdDao cisBxsqdDao;
	
	@Autowired
	private CisBxxypzDao cisBxxypzDao;

	@Override
	public String getFileName(ClinicalLeadBloodReq req, SysUser user) {
		return ExportDataTypeEnum.clinicalLeadBloodData.getDesc();
	}

	@Override
	public JasperExportDataDto<ClinicalLeadBloodParamDto, ClinicalLeadBloodFieldsDto> queryData(
			ClinicalLeadBloodReq req, SysUser user) {
		ClinicalLeadBloodParamDto param = cisBxsqdDao.queryClinicalLeadBloodPrint(ObjectToTypes.parseString(req.getSqdh()),
				user.getHospitalId());
		CisBxxypz cisBxxypz = new CisBxxypz();
		cisBxxypz.setSqdh(ObjectToTypes.parseString(req.getSqdh()));
		cisBxxypz.setYljgd(ObjectToTypes.parseString(user.getHospitalId()));
		List<ClinicalLeadBloodFieldsDto> respList = BeanUtil.toBeans(cisBxxypzDao.findByEntity(cisBxxypz), 
				ClinicalLeadBloodFieldsDto.class);
		ClinicalLeadBloodFieldsDto dto = new ClinicalLeadBloodFieldsDto();
		dto.setSxpzmc("备注");
		respList.add(dto);
		return createResp(param, respList);
	}

}
 