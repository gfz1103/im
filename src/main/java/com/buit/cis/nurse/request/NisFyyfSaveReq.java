package com.buit.cis.nurse.request;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisFyyf<br>
 * 类描述：病区_发药药房<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="病区_发药药房_saveReq")
public class NisFyyfSaveReq {

	@ApiModelProperty(value="病区发药药房保存数据集合")
	private List<NisFyyfSaveBody> nisFyyfSaveBodyList;

	public List<NisFyyfSaveBody> getNisFyyfSaveBodyList() {
		return nisFyyfSaveBodyList;
	}

	public void setNisFyyfSaveBodyList(List<NisFyyfSaveBody> nisFyyfSaveBodyList) {
		this.nisFyyfSaveBodyList = nisFyyfSaveBodyList;
	}

	public static class NisFyyfSaveBody {
		
	    @ApiModelProperty(value="记录序号")
	    private Integer jlxh;
	    @ApiModelProperty(value="病区代码")
	    private String bqdm;
	    @ApiModelProperty(value="功能分类")
	    private Integer gnfl;
	    @ApiModelProperty(value="医嘱类型")
	    private Integer type;
	    @ApiModelProperty(value="药房识别")
	    private String yfsb;
	    @ApiModelProperty(value="代码识别")
	    private String dmsb;
	    @ApiModelProperty(value="注销判别")
	    private Integer zxpb;
	    @ApiModelProperty(value="机构id")
	    private Integer jgid;
	
	    public Integer getJlxh() {
			return jlxh;
		}
	
		public void setJlxh(Integer jlxh) {
			this.jlxh = jlxh;
		}
	
		public Integer getJgid() {
	        return jgid;
	    }
	
	    public void setJgid(Integer jgid) {
	        this.jgid = jgid;
	    }
	
	    public String getBqdm() {
	        return bqdm;
	    }
	
	    public void setBqdm(String bqdm) {
	        this.bqdm = bqdm;
	    }
	
	    public Integer getGnfl() {
	        return gnfl;
	    }
	
	    public void setGnfl(Integer gnfl) {
	        this.gnfl = gnfl;
	    }
	
	    public Integer getType() {
	        return type;
	    }
	
	    public void setType(Integer type) {
	        this.type = type;
	    }
	
	    public String getYfsb() {
	        return yfsb;
	    }
	
	    public void setYfsb(String yfsb) {
	        this.yfsb = yfsb;
	    }
	
	    public String getDmsb() {
	        return dmsb;
	    }
	
	    public void setDmsb(String dmsb) {
	        this.dmsb = dmsb;
	    }
	
	    public Integer getZxpb() {
	        return zxpb;
	    }
	
	    public void setZxpb(Integer zxpb) {
	        this.zxpb = zxpb;
	    }
    
	}

}
