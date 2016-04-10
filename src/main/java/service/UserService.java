package service;

import org.springframework.stereotype.Service;
import vo.User;

import java.util.HashMap;
import java.util.Map;

/**
 * User Service 层
 *
 * @author liuchenwei
 * @date 2016/4/10
 * @since 1.0
 */
@Service
public class UserService {

    private static final String ID_PREFIX = "IDxxx";

    private static int counter = 0;

    private Map<String, User> id_user_map;// 模拟数据库

    public String save(User user) {
        String id = ID_PREFIX + (++counter);
        getId_user_map().put(id, user);
        return id;
    }

    public User find(String id) {
        return getId_user_map().get(id);
    }

    private Map<String, User> getId_user_map() {
        if (id_user_map == null) {
            id_user_map = new HashMap<>();
        }
        return id_user_map;
    }
}
