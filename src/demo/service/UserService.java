/**
 * 
 */
package demo.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import demo.dao.UserDAO;

/**
 * 
 * <p>
 * 
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2015年10月22日
 */
@Service
public class UserService {
	
	@Resource
	private UserDAO userDao;

	public String getUser(){
		return userDao.getUser();
	}
}
