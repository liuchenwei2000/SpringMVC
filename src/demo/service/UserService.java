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
 * @author ����ΰ
 * 
 * �������ڣ�2015��10��22��
 */
@Service
public class UserService {
	
	@Resource
	private UserDAO userDao;

	public String getUser(){
		return userDao.getUser();
	}
}
