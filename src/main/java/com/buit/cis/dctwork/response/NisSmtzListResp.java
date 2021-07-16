   
package com.buit.cis.dctwork.response;

import java.sql.Timestamp;
import java.util.List;

import com.buit.commons.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 类名称：NisSmtz<br> 
 * 类描述：生命体征<br>
 * @author GONGFANGZHOU
 */
@ApiModel(value="生命体征_listResp")
public class NisSmtzListResp extends PageQuery  implements Comparable<NisSmtzListResp>{
    @ApiModelProperty(value="采集时间")
    private Timestamp cjsj;
   
    @ApiModelProperty(value="采集项目集合")
    private List<NisSmtzResp> nisSmtzList;

	public Timestamp getCjsj() {
		return cjsj;
	}

	public void setCjsj(Timestamp cjsj) {
		this.cjsj = cjsj;
	}

	public List<NisSmtzResp> getNisSmtzList() {
		return nisSmtzList;
	}

	public void setNisSmtzList(List<NisSmtzResp> nisSmtzList) {
		this.nisSmtzList = nisSmtzList;
	}
    
	@Override
	public int compareTo(NisSmtzListResp o) {
		long thisTime = this.getCjsj().getTime();
        long anotherTime = o.getCjsj().getTime();
        int i = (thisTime > anotherTime ? -1 :(thisTime == anotherTime ? 0 : 1));
		return i;
	}
    
}
