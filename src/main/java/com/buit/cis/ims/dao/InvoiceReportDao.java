package com.buit.cis.ims.dao;

import com.buit.cis.ims.dto.InvoiceReportDto;
import com.buit.cis.ims.request.InvoiceReportReq;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 结算发票查询
 *
 * @author jiangwei
 */
@Mapper
@Repository
public interface InvoiceReportDao {

    /**
     * 多条件查询
     */
    List<InvoiceReportDto> list(InvoiceReportReq condition);
}
