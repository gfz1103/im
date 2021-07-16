
package com.buit.cis.dctwork.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;


/**
 * 类名称：OptSssqSaveReq<br>
 * 类描述：手术申请-保存请求<br>
 * @author 老花生
 */
@ApiModel(value="手术申请-保存请求")
public class CisSssqSaveReq {
    @ApiModelProperty(value="申请单号")
    private Integer sqdh;
    @ApiModelProperty(value="机构ID")
    private Integer jgid;
    @ApiModelProperty(value="门诊号码")
    private String mzhm;
    @ApiModelProperty(value="手术科室")
    private Integer ssks;
    @ApiModelProperty(value="申请科室")
    private Integer sqks;
    @ApiModelProperty(value="手术内码")
    private Integer ssnm;
    @ApiModelProperty(value="手术医师")
    private String ssys;
    @ApiModelProperty(value="手术一助")
    private String ssyz;
    @ApiModelProperty(value="手术二助")
    private String ssez;
    @ApiModelProperty(value="手术三助")
    private String sssz;
    @ApiModelProperty(value="麻醉代码")
    private Integer mzdm;
    @ApiModelProperty(value="麻醉医师")
    private String mzys;
    @ApiModelProperty(value="操作工号")
    private String czgh;
    @ApiModelProperty(value="拟手术名称")
    private String nssmc;
    @ApiModelProperty(value="手术等级")
    private Integer ssjb;
    @ApiModelProperty(value="手术备注")
    private String ssbz;
    @ApiModelProperty(value="上级医生")
    private String sjys;
    @ApiModelProperty(value="住院号码")
    private String zyhm;
    @ApiModelProperty(value="术前诊断")
    private String sqzd;
    @ApiModelProperty(value="病人科室")
    private Integer brks;
    @ApiModelProperty(value="申请类型")
    private Integer sqlx;
    @ApiModelProperty(value="住院号")
    private Integer zyh;
    @ApiModelProperty(value="费用序号")
    private Integer fyxh;
    @ApiModelProperty(value="麻醉名称")
    private String mzmc;
    @ApiModelProperty(value="手术名称")
    private String ssmc;

    @ApiModelProperty(value="就诊序号")
    private int clinicId;
    @ApiModelProperty(value="病人ID")
    private Integer brid;
    @ApiModelProperty(value="病人姓名")
    private String brxm;
    @ApiModelProperty(value="单据来源")
    private int djly;
    @ApiModelProperty(value="挂号关联")
    private int ghgl;
    @ApiModelProperty(value="手术申请信息")
    private FormInfoBean formInfo;
    private BodyBean body;
    @ApiModelProperty(value="就诊流水号")
    private String jzlsh;
    
    @ApiModelProperty(value="病人病区")
    private Integer brbq;
    
    @ApiModelProperty(value="病人床号")
    private String brch;

    public class FormInfoBean {
        @ApiModelProperty(value="操作类型")
        private String op;
        private FormDataBean formData;
        @ApiModelProperty(value="申请类型")
        private Integer sqlx;

        public class FormDataBean {
            @ApiModelProperty(value="病人ID")
            private Integer brid;
            @ApiModelProperty(value="申请日期")
            private Timestamp sqrq;
            @ApiModelProperty(value="申请医师")
            private String sqys;
            @ApiModelProperty(value="手术日期")
            private Timestamp ssrq;
            @ApiModelProperty(value="门诊号码")
            private String mzhm;
            @ApiModelProperty(value="病人姓名")
            private String brxm;
            @ApiModelProperty(value="病人性别")
            private String brxb;
            @ApiModelProperty(value="病人年龄")
            private String brnn;
            @ApiModelProperty(value="病人科室")
            private int brks;
            @ApiModelProperty(value="诊断")
            private String sqzd;
            @ApiModelProperty(value="麻醉方法")
            private String mzdm;
            @ApiModelProperty(value="麻醉方法")
            private String mzmc;
            @ApiModelProperty(value="手术医师")
            private String ssys;
            @ApiModelProperty(value="手术一助")
            private String ssyz;
            @ApiModelProperty(value="手术二助")
            private String ssez;
            @ApiModelProperty(value="手术三助")
            private String sssz;
            @ApiModelProperty(value="麻醉医师")
            private String mzys;
            @ApiModelProperty(value="手术等级")
            private Integer ssjb;
            @ApiModelProperty(value="上级医生")
            private String sjys;
            @ApiModelProperty(value="手术备注")
            private String ssbz;
            @ApiModelProperty(value="申请类型")
            private Integer sqlx;
            @ApiModelProperty(value="手术内码")
            private Integer ssnm;
            @ApiModelProperty(value="费用序号")
            private Integer fyxh;
            @ApiModelProperty(value="住院号码")
            private String zyhm;
            @ApiModelProperty(value="手术名称")
            private String ssmc;
            @ApiModelProperty(value="手术单号")
            private Integer sqdh;

            public Integer getBrid() {
                return brid;
            }

            public void setBrid(Integer brid) {
                this.brid = brid;
            }

            public Timestamp getSqrq() {
                return sqrq;
            }

            public void setSqrq(Timestamp sqrq) {
                this.sqrq = sqrq;
            }

            public String getSqys() {
                return sqys;
            }

            public void setSqys(String sqys) {
                this.sqys = sqys;
            }

            public Timestamp getSsrq() {
                return ssrq;
            }

            public void setSsrq(Timestamp ssrq) {
                this.ssrq = ssrq;
            }

            public String getMzhm() {
                return mzhm;
            }

            public void setMzhm(String mzhm) {
                this.mzhm = mzhm;
            }

            public String getBrxm() {
                return brxm;
            }

            public void setBrxm(String brxm) {
                this.brxm = brxm;
            }

            public String getBrxb() {
                return brxb;
            }

            public void setBrxb(String brxb) {
                this.brxb = brxb;
            }

            public String getBrnn() {
                return brnn;
            }

            public void setBrnn(String brnn) {
                this.brnn = brnn;
            }

            public int getBrks() {
                return brks;
            }

            public void setBrks(int brks) {
                this.brks = brks;
            }

            public String getSqzd() {
                return sqzd;
            }

            public void setSqzd(String sqzd) {
                this.sqzd = sqzd;
            }

            public String getMzdm() {
                return mzdm;
            }

            public String getMzmc() {
                return mzmc;
            }

            public void setMzmc(String mzmc) {
                this.mzmc = mzmc;
            }

            public void setMzdm(String mzdm) {
                this.mzdm = mzdm;
            }

            public String getSsys() {
                return ssys;
            }

            public void setSsys(String ssys) {
                this.ssys = ssys;
            }

            public String getSsyz() {
                return ssyz;
            }

            public void setSsyz(String ssyz) {
                this.ssyz = ssyz;
            }

            public String getSsez() {
                return ssez;
            }

            public void setSsez(String ssez) {
                this.ssez = ssez;
            }

            public String getSssz() {
                return sssz;
            }

            public void setSssz(String sssz) {
                this.sssz = sssz;
            }

            public String getMzys() {
                return mzys;
            }

            public void setMzys(String mzys) {
                this.mzys = mzys;
            }

            public Integer getSsjb() {
                return ssjb;
            }

            public void setSsjb(Integer ssjb) {
                this.ssjb = ssjb;
            }

            public String getSjys() {
                return sjys;
            }

            public void setSjys(String sjys) {
                this.sjys = sjys;
            }

            public String getSsbz() {
                return ssbz;
            }

            public void setSsbz(String ssbz) {
                this.ssbz = ssbz;
            }

            public Integer getSqlx() {
                return sqlx;
            }

            public void setSqlx(Integer sqlx) {
                this.sqlx = sqlx;
            }

            public Integer getSsnm() {
                return ssnm;
            }

            public void setSsnm(Integer ssnm) {
                this.ssnm = ssnm;
            }

            public Integer getFyxh() {
                return fyxh;
            }

            public void setFyxh(Integer fyxh) {
                this.fyxh = fyxh;
            }

			public String getZyhm() {
				return zyhm;
			}

			public void setZyhm(String zyhm) {
				this.zyhm = zyhm;
			}

			public String getSsmc() {
				return ssmc;
			}

			public void setSsmc(String ssmc) {
				this.ssmc = ssmc;
			}

			public Integer getSqdh() {
				return sqdh;
			}

			public void setSqdh(Integer sqdh) {
				this.sqdh = sqdh;
			}
            
            
        }

        public String getOp() {
            return op;
        }

        public void setOp(String op) {
            this.op = op;
        }

        public FormDataBean getFormData() {
            return formData;
        }

        public void setFormData(FormDataBean formData) {
            this.formData = formData;
        }

        public Integer getSqlx() {
            return sqlx;
        }

        public void setSqlx(Integer sqlx) {
            this.sqlx = sqlx;
        }
    }

    public class BodyBean {
        @ApiModelProperty(value="操作类型")
        private String opStatus;
        @ApiModelProperty(value="机构ID")
        private String jgid;
        @ApiModelProperty(value="项目类型")
        private Integer xmlx;
        @ApiModelProperty(value="费用归并")
        private Integer fygb;
        @ApiModelProperty(value="医技组号")
        private Integer yjzh;
        @ApiModelProperty(value="医疗序号")
        private Integer ylxh;
        @ApiModelProperty(value="医疗单价")
        private Integer yldj;
        @ApiModelProperty(value="医疗数量")
        private Integer ylsl;
        @ApiModelProperty(value="医技主项")
        private Integer yjzx;
        @ApiModelProperty(value="自负比例")
        private Integer zfbl;
        @ApiModelProperty(value="打折比例")
        private Integer dzbl;
        @ApiModelProperty(value="开单科室")
        private Integer ksdm;
        @ApiModelProperty(value="开单科室text")
        private String ksdmText;
        @ApiModelProperty(value="执行科室")
        private Integer zxks;
        @ApiModelProperty(value="执行科室text")
        private String zxksText;
        @ApiModelProperty(value="申请医生")
        private String ysdm;
        @ApiModelProperty(value="划价金额")
        private Integer hjje;

        public String getOpStatus() {
            return opStatus;
        }

        public void setOpStatus(String opStatus) {
            this.opStatus = opStatus;
        }

        public String getJgid() {
            return jgid;
        }

        public void setJgid(String jgid) {
            this.jgid = jgid;
        }

        public Integer getXmlx() {
            return xmlx;
        }

        public void setXmlx(Integer xmlx) {
            this.xmlx = xmlx;
        }

        public Integer getFygb() {
            return fygb;
        }

        public void setFygb(Integer fygb) {
            this.fygb = fygb;
        }

        public Integer getYjzh() {
            return yjzh;
        }

        public void setYjzh(Integer yjzh) {
            this.yjzh = yjzh;
        }

        public Integer getYlxh() {
            return ylxh;
        }

        public void setYlxh(Integer ylxh) {
            this.ylxh = ylxh;
        }

        public Integer getYldj() {
            return yldj;
        }

        public void setYldj(Integer yldj) {
            this.yldj = yldj;
        }

        public Integer getYlsl() {
            return ylsl;
        }

        public void setYlsl(Integer ylsl) {
            this.ylsl = ylsl;
        }

        public Integer getYjzx() {
            return yjzx;
        }

        public void setYjzx(Integer yjzx) {
            this.yjzx = yjzx;
        }

        public Integer getZfbl() {
            return zfbl;
        }

        public void setZfbl(Integer zfbl) {
            this.zfbl = zfbl;
        }

        public Integer getDzbl() {
            return dzbl;
        }

        public void setDzbl(Integer dzbl) {
            this.dzbl = dzbl;
        }

        public Integer getKsdm() {
            return ksdm;
        }

        public void setKsdm(Integer ksdm) {
            this.ksdm = ksdm;
        }

        public String getKsdmText() {
            return ksdmText;
        }

        public void setKsdmText(String ksdmText) {
            this.ksdmText = ksdmText;
        }

        public Integer getZxks() {
            return zxks;
        }

        public void setZxks(Integer zxks) {
            this.zxks = zxks;
        }

        public String getZxksText() {
            return zxksText;
        }

        public void setZxksText(String zxksText) {
            this.zxksText = zxksText;
        }

        public String getYsdm() {
            return ysdm;
        }

        public void setYsdm(String ysdm) {
            this.ysdm = ysdm;
        }

        public Integer getHjje() {
            return hjje;
        }

        public void setHjje(Integer hjje) {
            this.hjje = hjje;
        }
    }

    public Integer getSqdh() {
        return sqdh;
    }

    public void setSqdh(Integer sqdh) {
        this.sqdh = sqdh;
    }

    public Integer getJgid() {
        return jgid;
    }

    public void setJgid(Integer jgid) {
        this.jgid = jgid;
    }

    public String getMzhm() {
        return mzhm;
    }

    public void setMzhm(String mzhm) {
        this.mzhm = mzhm;
    }

    public Integer getSsks() {
        return ssks;
    }

    public void setSsks(Integer ssks) {
        this.ssks = ssks;
    }

    public Integer getSqks() {
        return sqks;
    }

    public void setSqks(Integer sqks) {
        this.sqks = sqks;
    }

    public Integer getSsnm() {
        return ssnm;
    }

    public void setSsnm(Integer ssnm) {
        this.ssnm = ssnm;
    }

    public String getSsys() {
        return ssys;
    }

    public void setSsys(String ssys) {
        this.ssys = ssys;
    }

    public String getSsyz() {
        return ssyz;
    }

    public void setSsyz(String ssyz) {
        this.ssyz = ssyz;
    }

    public String getSsez() {
        return ssez;
    }

    public void setSsez(String ssez) {
        this.ssez = ssez;
    }

    public String getSssz() {
        return sssz;
    }

    public void setSssz(String sssz) {
        this.sssz = sssz;
    }

    public Integer getMzdm() {
        return mzdm;
    }

    public void setMzdm(Integer mzdm) {
        this.mzdm = mzdm;
    }

    public String getMzys() {
        return mzys;
    }

    public void setMzys(String mzys) {
        this.mzys = mzys;
    }

    public String getCzgh() {
        return czgh;
    }

    public void setCzgh(String czgh) {
        this.czgh = czgh;
    }

    public String getNssmc() {
        return nssmc;
    }

    public void setNssmc(String nssmc) {
        this.nssmc = nssmc;
    }

    public Integer getSsjb() {
        return ssjb;
    }

    public void setSsjb(Integer ssjb) {
        this.ssjb = ssjb;
    }

    public String getSsbz() {
        return ssbz;
    }

    public void setSsbz(String ssbz) {
        this.ssbz = ssbz;
    }

    public String getSjys() {
        return sjys;
    }

    public void setSjys(String sjys) {
        this.sjys = sjys;
    }

    public String getZyhm() {
        return zyhm;
    }

    public void setZyhm(String zyhm) {
        this.zyhm = zyhm;
    }

    public String getSqzd() {
        return sqzd;
    }

    public void setSqzd(String sqzd) {
        this.sqzd = sqzd;
    }

    public Integer getBrks() {
        return brks;
    }

    public void setBrks(Integer brks) {
        this.brks = brks;
    }

    public Integer getSqlx() {
        return sqlx;
    }

    public void setSqlx(Integer sqlx) {
        this.sqlx = sqlx;
    }

    public Integer getZyh() {
        return zyh;
    }

    public void setZyh(Integer zyh) {
        this.zyh = zyh;
    }

    public Integer getFyxh() {
        return fyxh;
    }

    public void setFyxh(Integer fyxh) {
        this.fyxh = fyxh;
    }

    public String getMzmc() {
        return mzmc;
    }

    public void setMzmc(String mzmc) {
        this.mzmc = mzmc;
    }

    public String getSsmc() {
        return ssmc;
    }

    public void setSsmc(String ssmc) {
        this.ssmc = ssmc;
    }

    public int getClinicId() {
        return clinicId;
    }

    public void setClinicId(int clinicId) {
        this.clinicId = clinicId;
    }

    public Integer getBrid() {
        return brid;
    }

    public void setBrid(Integer brid) {
        this.brid = brid;
    }

    public String getBrxm() {
        return brxm;
    }

    public void setBrxm(String brxm) {
        this.brxm = brxm;
    }

    public int getDjly() {
        return djly;
    }

    public void setDjly(int djly) {
        this.djly = djly;
    }

    public int getGhgl() {
        return ghgl;
    }

    public void setGhgl(int ghgl) {
        this.ghgl = ghgl;
    }

    public FormInfoBean getFormInfo() {
        return formInfo;
    }

    public void setFormInfo(FormInfoBean formInfo) {
        this.formInfo = formInfo;
    }

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public String getJzlsh() {
        return jzlsh;
    }

    public void setJzlsh(String jzlsh) {
        this.jzlsh = jzlsh;
    }

	public Integer getBrbq() {
		return brbq;
	}

	public void setBrbq(Integer brbq) {
		this.brbq = brbq;
	}

	public String getBrch() {
		return brch;
	}

	public void setBrch(String brch) {
		this.brch = brch;
	}
    
    
}
