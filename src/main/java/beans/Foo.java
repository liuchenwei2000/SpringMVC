package beans;

/**
 * 示例 Bean
 *
 * @author liuchenwei
 * @date 2016/4/10
 * @since 1.0
 */
public class Foo {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "name='" + name + '\'' +
                '}';
    }
}
