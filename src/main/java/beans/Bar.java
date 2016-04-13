package beans;

/**
 * 示例 Bean
 *
 * @author liuchenwei
 * @date 2016/4/10
 * @since 1.0
 */
public class Bar {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Bar{" +
                "message='" + message + '\'' +
                '}';
    }
}
