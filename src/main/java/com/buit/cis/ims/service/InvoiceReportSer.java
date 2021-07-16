package com.buit.cis.ims.service;

import cn.hutool.core.bean.BeanUtil;
import com.buit.cis.ims.dao.InvoiceReportDao;
import com.buit.cis.ims.dto.InvoiceReportDto;
import com.buit.cis.ims.request.InvoiceReportReq;
import com.buit.cis.ims.response.InvoiceReportResp;
import com.buit.utill.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 结算发票查询
 *
 * @author jiangwei
 */
@Service
public class InvoiceReportSer {

    @Autowired
    InvoiceReportDao dao;

    /**
     * 多条件列表查询
     */
    public List<InvoiceReportResp> list(InvoiceReportReq condition) {
        List<InvoiceReportDto> list = dao.list(condition);
        List<InvoiceReportResp> result = new ArrayList<>(list.size());
        for (InvoiceReportDto rowData : list) {
            result.add(dtoHandler(rowData));
        }
        return result;
    }

    /**
     * 数据处理
     *
     * @param row 传输数据对象
     * @return 展示数据对象
     */
    public InvoiceReportResp dtoHandler(InvoiceReportDto row) {
        InvoiceReportResp result = new InvoiceReportResp();
        BeanUtil.copyProperties(row, result);
        result.setJsts(DateUtils.getDaysDiff(row.getKsrq(), row.getZzrq()));
        if (0 == row.getZfpb()) {
            result.setOperUser(row.getCzgh());
            result.setOperDate(row.getJsrq());
        } else if (1 == row.getZfpb()) {
            result.setOperUser(row.getZfgh());
            result.setOperDate(row.getZfrq());
        }
        return result;
    }
}
