/**
 * 
 */
package vo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Book
 *
 * @author 刘晨伟
 * 
 * 创建日期：2015年10月30日
 */
// 支持序列为 XML 文本
@XmlRootElement
public class Book {

	private String id;
	private String name;

	public Book() {
	}

	public Book(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Book{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
