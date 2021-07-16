package com.buit.cis.ims.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

/**
 * 类名称：OpZydj<br>
 * 类描述：<br>
 *     住院病人入院登记
 * @author zhouhaisheng
 */
@ApiModel(value="住院病人基本信息")
public class ZybrjbxxResp {

        @ApiModelProperty(value="病人ID号 | 是一个内码，其它数据表与该表相联系的字段")
        private Integer brid;
        @ApiModelProperty(value="门诊号码 | 唯一一处记载病人门诊号码的地方，在程序中用于刷卡登记或查询相关记录使用")
        private String mzhm;
        @ApiModelProperty(value="病人姓名")
        private String brxm;

        @ApiModelProperty(value="身份证号")
        private String sfzh;
        @ApiModelProperty(value="病人性质 | ")
        private Integer brxz;
        @ApiModelProperty(value="病人性别 | ")
        private Integer brxb;
        @ApiModelProperty(value="出生年月")
        private Timestamp csny;
        @ApiModelProperty(value="婚姻状况 | ")
        private Integer hyzk;
        @ApiModelProperty(value="职业代码 | ")
        private String zydm;
        @ApiModelProperty(value="民族代码 | ")
        private String mzdm;



        @ApiModelProperty(value="单位名称")
        private String dwmc;
        @ApiModelProperty(value="单位电话")
        private String dwdh;
        @ApiModelProperty(value="单位邮编")
        private String dwyb;
        @ApiModelProperty(value="户口地址")
        /** 户口地址 */
        private String hkdz;
        @ApiModelProperty(value="家庭电话")
        private String jtdh;


        /** 国籍代码 */
        private String gjdm;
        @ApiModelProperty(value="联系人名")
        private String lxrm;
        @ApiModelProperty(value="联系关系")
        private Integer lxgx;
        @ApiModelProperty(value="联系地址")
        private String lxdz;
        @ApiModelProperty(value="联系电话")
        private String lxdh;
        @ApiModelProperty(value="担保人名")
        private String dbrm;
        @ApiModelProperty(value="担保关系")
        private Integer dbgx;
        @ApiModelProperty(value="出生地_省区市")
        private Integer csdSqs;
        @ApiModelProperty(value="出生地_市")
        private Integer csdS;
        @ApiModelProperty(value="出生地_县")
        private Integer csdX;
        @ApiModelProperty(value="籍贯代码_省区市")
        private Integer jgdmSqs;
        @ApiModelProperty(value="籍贯代码_市")
        private Integer jgdmS;
        @ApiModelProperty(value="现住址_省区市")
        private Integer xzzSqs;
        @ApiModelProperty(value="现住址_市")
        private Integer xzzS;
        @ApiModelProperty(value="现住址_县")
        private Integer xzzX;
        @ApiModelProperty(value="现住址_邮编")
        private String xzzYb;
        @ApiModelProperty(value="现住址_电话")
        private String xzzDh;
        @ApiModelProperty(value="户口地址_省区市")
        private Integer hkdzSqs;
        @ApiModelProperty(value="户口地址_市")
        private Integer hkdzS;
        @ApiModelProperty(value="户口地址_县")
        private Integer hkdzX;
        @ApiModelProperty(value="现住址_其他地址")
        private String xzzQtdz;
        @ApiModelProperty(value="户口地址_其他地址")
        private String hkdzQtdz;


}
