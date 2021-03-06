package com.ruoyi.project.im.webserver.user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.project.im.constant.IMConstants;
import com.ruoyi.project.im.webserver.base.controller.IMBaseController;
import com.ruoyi.project.im.webserver.user.model.UserInfoEntity;
import com.ruoyi.project.im.webserver.user.service.UserInfoService;
import com.ruoyi.project.im.webserver.util.Query;
 
/**
 * 用户信息表
 * 
 * @author qiqiim
 * @email 1044053532@qq.com
 * @date 2017-11-27 14:56:08
 */
@Controller
@RequestMapping("/im/userinfo")
public class UserInfoController extends IMBaseController{
	@Autowired
	private UserInfoService userInfoServiceImpl;
	
	/**
	 * 页面
	 */
	@RequestMapping("/page")
	public String page(@RequestParam Map<String, Object> params){
		return "userinfo";
	}
	/**
	 * 列表
	 */
	@RequestMapping(value="/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam Map<String, Object> params){
	    Query query = new Query(params);
		List<UserInfoEntity> userInfoList = userInfoServiceImpl.queryList(query);
		int total = userInfoServiceImpl.queryTotal(query);
		return putMsgToJsonString(IMConstants.WebSite.SUCCESS,"",total,userInfoList);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping(value="/info/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object info(@PathVariable("id") Long id){
		UserInfoEntity userInfo = userInfoServiceImpl.queryObject(id);
		return putMsgToJsonString(IMConstants.WebSite.SUCCESS,"",0,userInfo);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public Object save(@ModelAttribute UserInfoEntity userInfo){
		userInfoServiceImpl.save(userInfo);
		return putMsgToJsonString(IMConstants.WebSite.SUCCESS,"",0,userInfo);
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@ModelAttribute UserInfoEntity userInfo){
		int result = userInfoServiceImpl.update(userInfo);
		return putMsgToJsonString(result,"",0,"");
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object delete(@RequestParam Long[] ids){
		int result = userInfoServiceImpl.deleteBatch(ids);
		return putMsgToJsonString(result,"",0,"");
	}
	
}
