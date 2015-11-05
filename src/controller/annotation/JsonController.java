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
import org.springframework.web.bind.annotation.ResponseBody;

import vo.User;

/**
 * 处理/返回 JSON 的控制器示例
 * <p>
 * 浏览器访问报：
 * the resource identified by this request is only capable of generating responses 
 * with characteristics not acceptable according to the request "accept" headers.
 * 
 * 需要添加：jackson-core-2.4.1、jackson-databind-2.4.1.1、jackson-annotations-2.4.0
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2015年5月19日
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

	@RequestMapping(value = "/user/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> addUser(@RequestBody User user) {
		if (StringUtils.isEmpty(user) || StringUtils.isEmpty(user.getCode()) || StringUtils.isEmpty(user.getName())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		user.setId("id000X");
		System.out.println(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}