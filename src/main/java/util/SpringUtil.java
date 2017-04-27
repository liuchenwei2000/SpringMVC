package util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring 工具类
 * <p>
 * 可以使用此工具获取 Spring 容器指定的 Bean。
 * <p>
 * 此工具类需要在 XML 配置文件中配置为一个 Bean（或使用注解被 Spring 自动扫描为 Bean）。
 *
 * @author liuchenwei
 * @date 2016/4/10
 * @since 1.0
 */
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    public static Object getBean(String id) {
        return applicationContext.getBean(id);
    }
}
