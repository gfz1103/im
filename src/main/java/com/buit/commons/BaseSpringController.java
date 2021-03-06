package com.buit.commons;


import cn.hutool.core.util.StrUtil;
import com.buit.utill.RedisFactory;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpServletRequest;
/**
* @ClassName: BaseSpringController
* @Description: 所有 的基础类
* @author 神算子
* @date 2020年4月26日 下午3:34:33
*
 */
public abstract class BaseSpringController extends BaseController{
	static final Logger logger = LoggerFactory.getLogger(BaseSpringController.class);

	/**
	 * 开发环境
	 */
	static final String DEVELOP_ENVIRONMENT = "dev";
	static final String ACTIVE = "knife4j.production";

	@Autowired  
    private Environment env;  
	@Autowired
    private HttpServletRequest request;
	@Autowired
    private RedisFactory redisFactory;

	/**
     * 获取登录用户信息
     */
    public SysUser getUser() {
        String token = request.getHeader("token");
        if(StringUtils.isBlank(token)){
			token = request.getParameter("token");
		}
        String refreshTokenKey = String.format("JWT_TOKEN::%s", token);		
        SysUser loginUser=redisFactory.getValue(refreshTokenKey, SysUser.class);

        //方便测试注意后期去掉
//		loginUser.setHospitalId(310112041);
        if (null == loginUser) {
			//如果开发环境 为了测试方便
            if(env.getProperty(ACTIVE)==null||env.getProperty(ACTIVE).equals("false")) {
                loginUser =new SysUser();
                loginUser.setUserId(1000021);
                loginUser.setHospitalName("上海天佑医院");
                loginUser.setUserName("超级管理员");
                loginUser.setHospitalId(310112001);
                loginUser.setGroupId(1);
                loginUser.setDataVersion(1);
                loginUser.setDeptId(9000);
                return loginUser;
             }else {
                 throw BaseException.create("ERROR_USER_0000");
             }
        }
        return loginUser;
    }
	/**
	 * 获取请求方IP
	 *
	 * @return 客户端Ip
	 */
    public String getIpAddress(){
        String xff = StrUtil.isEmpty(request.getHeader("REQUEST-IP")) ? request.getParameter("REQUEST-IP") : request.getHeader("REQUEST-IP");
        String splitIp = null;
        if(StrUtil.isNotEmpty(request.getHeader("X-Forwarded-For"))){
            String [] strings = request.getHeader("X-Forwarded-For").split(",");
            splitIp = strings[0];
        }
        logger.info("X-Forwarded-For ===> " + request.getHeader("X-Forwarded-For") + "     splitIp ===>  " + splitIp);
        if (StrUtil.isEmpty(xff) && StrUtil.isEmpty(splitIp)) {
            throw BaseException.create("ERROR_IP_0001");
        }else {
            return StrUtil.isEmpty(xff) || xff.equals("null") ? splitIp : xff;
        }
    }
}
