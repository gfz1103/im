package com.buit.cis.ims.service;


import com.buit.system.request.DicGbsj01Model;
import com.buit.system.request.DicGbsj02Model;
import com.buit.system.request.PubFkfsModel;
import com.buit.system.response.DicKszdModel;
import com.buit.system.response.PubBrxzOut;
import com.buit.system.service.DicGbsj01Service;
import com.buit.system.service.DicGbsj02Service;
import com.buit.system.service.DicKszdOutSer;
import com.buit.system.service.PubBrxzOutSer;
import com.buit.system.service.PubFkfsService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 住院后端字典
 * zhouhaisheng
 */
@Service
public class ZyDicSer {

	@DubboReference
    public PubBrxzOutSer pubBrxzOutSer;
   	@DubboReference
    public DicKszdOutSer dicKszdOutSer;
    @DubboReference
    public PubFkfsService pubFkfsService;
    @DubboReference
    public DicGbsj01Service dicGbsj01Service;
    @DubboReference
    public DicGbsj02Service dicGbsj02Service;
    /**
     * 住院病人性质字典
     * @return
     */
   public Map<Integer,String> getBrxzDic(){
       PubBrxzOut param=new PubBrxzOut();
       param.setZysy(1);
       List<PubBrxzOut> pubBrxzList=pubBrxzOutSer.findByEntity(param);
       Map<Integer,String> brxzMap= pubBrxzList.stream().collect(Collectors.toMap(PubBrxzOut::getBrxz,PubBrxzOut::getXzmc));
       return brxzMap;
   }
    /**
     * 住院病人科室字典
     * @return
     */
    public Map<Integer,String> getBrksDic(Integer hospitalId){
        List<DicKszdModel> dicKszdList=dicKszdOutSer.queryZyBrksDic(hospitalId);
        Map<Integer,String> brksMap= dicKszdList.stream().collect(Collectors.toMap(DicKszdModel::getId,DicKszdModel::getOfficename));
        return brksMap;
    }
    /**
     * 住院病人病区字典
     * @return
     */
    public Map<Integer,String> getBrbqDic(Integer hospitalId){
        DicKszdModel param=new DicKszdModel();
        param.setOrganizcode(String.valueOf(hospitalId));
        param.setLogoff("0");
        param.setHospitalarea("1");
        List<DicKszdModel> dicKszdList=dicKszdOutSer.findByEntity(param);
        Map<Integer,String> brbqMap= dicKszdList.stream().collect(Collectors.toMap(DicKszdModel::getId,DicKszdModel::getOfficename));
        return brbqMap;
    }
    /**
     * 住院付款方式字典
     * @return
     */
    public Map<Integer,String> getJkfsDic(Integer hospitalId){
        PubFkfsModel param=new PubFkfsModel();
        param.setSylx(2);
        param.setZfbz(0);
        List<PubFkfsModel> pubFkfsList=pubFkfsService.findByEntity(param);
        Map<Integer,String> fkfsMap= pubFkfsList.stream().collect(Collectors.toMap(PubFkfsModel::getFkfs,PubFkfsModel::getFkmc));
        return fkfsMap;
    }

    //性别字典
    public Map<String,String> getBrxbDic(){
        DicGbsj01Model dicGbsj01= dicGbsj01Service.findByCode("PD0000000269");
        DicGbsj02Model param=new DicGbsj02Model();
        param.setPrimarydataId(dicGbsj01.getPrimarydataId());
        List<DicGbsj02Model> dicGbsj01ValueList=dicGbsj02Service.findByEntity(param);
        Map<String, String> xbmap=dicGbsj01ValueList.stream().collect(Collectors.toMap(DicGbsj02Model::getDataValue,DicGbsj02Model::getDataValueRemark));

        return xbmap;
    }

}
