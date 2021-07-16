package com.buit.cis.nurse.enums;

/**
 * 护理记录单类型
 * @ClassName: NursingTypeEnum
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 龚方舟
 * @date 2020年9月9日 下午2:41:56
 *
 */
public enum NursingTypeEnum {
	
	/**
	 * commonly:一般护理记录,commonlyGrave:危重患者护理记录,diseaseWarning:患者病情预警评估单,
	 * barthelRecord:Barthel指数评定量表,prenatalCare:产前护理记录单,postpartumCare:产后护理记录单,
	 * pressureSore:住院患者压疮危险因素评估表,acquiredPneumonia:医院获得性肺炎风险因素评估单
	 */
	commonly("commonly"),
	commonlyGrave("commonlyGrave"),
	diseaseWarning("diseaseWarning"),
	barthelRecord("barthelRecord"),
	prenatalCare("prenatalCare"),
	postpartumCare("postpartumCare"),
	pressureSore("pressureSore"),
	acquiredPneumonia("acquiredPneumonia"),
	pipeSlippage("pipeSlippage"),
	nursingRecord("nursingRecord"),
	fallingBed("fallingBed"),
	catheterInfection("catheterInfection"),
	stressInjury("stressInjury"),
	centralVenousCatheter("centralVenousCatheter"),
	emotionalDisorder("emotionalDisorder"),
	pulmonaryEmbolism("pulmonaryEmbolism"),
	admissionNursing("admissionNursing"),
	deepVeinThrombosis("deepVeinThrombosis"),
	neurologyRecords("neurologyRecords"),
	patientCare("patientCare"),
	youthCare("youthCare"),
	abuseNeglect("abuseNeglect"),
	frailOldMan("frailOldMan"),
	infectiousDiseases("infectiousDiseases"),
	terminalPregnancy("terminalPregnancy"),
	stageDisease("stageDisease"),
	chemotherapyPatient("chemotherapyPatient"),
	chronicPain("chronicPain"),
	hypoimmunityPatient("hypoimmunityPatient"),
	dayWard("dayWard");
	
	private String code;

	NursingTypeEnum(String code) {
        this.code = code;
    }
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
