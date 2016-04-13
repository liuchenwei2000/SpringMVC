/**
 * 
 */
package vo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * User
 *
 * @author 刘晨伟
 * 
 * 创建日期：2015年10月30日
 */
// 支持序列为 XML 文本
@XmlRootElement
public class User {

	private String id;
	private String code;
	private String name;

	public User() {
		super();
	}

	public User(String id, String code, String name) {
		this.id = id;
		this.code = code;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", code=" + code + ", name=" + name + "]";
	}
}
