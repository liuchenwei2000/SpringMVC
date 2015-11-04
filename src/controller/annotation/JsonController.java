package controller.annotation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vo.User;

/**
 * ����/���� JSON �Ŀ�����ʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2015��5��19��
 */
@Controller 
@RequestMapping("/api")
public class JsonController {

	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> index() {
		return new ResponseEntity<>(getAllUsers(), HttpStatus.OK);
	}

	private List<User> getAllUsers() {
		List<User> user = new ArrayList<User>();
		for (int i = 0; i < 10; i++) {
			user.add(new User("id000" + i, "code000" + i, "name000" + i));
		}
		return user;
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addUser(@RequestBody User user) {
		if (StringUtils.isEmpty(user) || StringUtils.isEmpty(user.getCode()) || StringUtils.isEmpty(user.getName())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		user.setId("id000X");
		System.out.println(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}