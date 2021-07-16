package com.buit.cis.dctwork.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.buit.cis.dctwork.enums.ExportDataTypeEnum;
import com.buit.his.export.enums.ExportFileTypeEnum;
import com.buit.his.export.service.SupportExportDataService;

/**
 * 
 * @ClassName: ImSupportExportDataSer
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 龚方舟
 * @date 2021年1月8日 下午3:39:10
 *
 */
@Service
public class ImSupportExportDataSer implements SupportExportDataService {

    @Override
    public Map<String, String> allDataType() {
        return Arrays.stream(ExportDataTypeEnum.values())
                .collect(Collectors.toMap(ExportDataTypeEnum::name,ExportDataTypeEnum::getDesc));
    }

    @Override
    public List<ExportFileTypeEnum> supportFileType(String dataType) {
        return Arrays.asList(ExportDataTypeEnum.valueOf(dataType).getSupportType());
    }

    @Override
    public String getJasperPath(String dataType) {
        return ExportDataTypeEnum.valueOf(dataType).getJasperPath();
    }

    @Override
    public String getJasperCond(String dataType) {
        return ExportDataTypeEnum.valueOf(dataType).getJasperCond().name();
    }
}
