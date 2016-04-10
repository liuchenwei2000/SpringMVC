package controller.annotation;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;
import vo.User;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

/**
 * 页面控制器实现，使用 annotation
 *
 * @author 刘晨伟
 *         <p>
 *         创建日期：2015年5月19日
 */
@Controller
// 如果在类上使用 @RequestMapping 注解一般是用于窄化功能处理方法的映射的
@RequestMapping("/user") // ① 处理器的通用映射前缀        
public class UserController {

    // 注入 ServletContext，为了获取 WebRoot 路径
    @Resource
    private ServletContext servletContext;

    @Resource
    private UserService userService;

    // 相对于①处的映射进行窄化，即响应 /user/firstname
    @RequestMapping("/firstname")
    public ModelAndView getFirstName() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "Bill");
        mv.setViewName("show");
        return mv;
    }

    // 限定只处理 GET 方式的请求
    @RequestMapping(value = "/lastname", method = RequestMethod.GET)
    public ModelAndView getLastName() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "Gates");
        mv.setViewName("show");
        return mv;
    }

    // 既可以处理 POST 又可以处理 GET 方式的请求
    @RequestMapping(value = "/all", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView all() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "Bill Gates");
        mv.setViewName("show");
        return mv;
    }

    // GET 请求中有 "query" 参数即可匹配，如 "http://×××/user?query=a"
    @RequestMapping(params = "query", method = RequestMethod.GET)
    public ModelAndView query(@RequestParam("query") String param) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", param);
        mv.setViewName("show");
        return mv;
    }

    // URI 模板模式映射：{userid} 是占位符， 请求的 URL 可以是 "/delete/123456"或 "/users/abcd"
    // 通过 @PathVariable 可以提取 URI 模板模式中 {userid} 的 userid 变量值。
    @RequestMapping("/delete/{userid}")
    public ModelAndView deleteUser(@PathVariable String userid) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", userid);
        mv.setViewName("show");
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUser() {
        return "adduser";// 直接返回逻辑视图名
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(User user) {
        String id = userService.save(user);
        // 这里返回了一个重定向的视图而不是指明逻辑视图名称
        // 前缀 redirect: 说明请求将被重定向到指定路径，实际上重定向的 URL 将由下面的 showUser 方法响应处理。
        return "redirect:/user/" + id;
    }

    @RequestMapping("/{userid}")
    public ModelAndView showUser(@PathVariable String userid) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", userService.find(userid));
        mv.setViewName("user");
        return mv;
    }

    /**
     * Spring 使用文件上传一般需要做3件事：
     * 1，在 JSP 表单中添加一个文件上传域，详见 WEb-INF/views/uploadPic.jsp。
     * 2，修改 Controller 响应方法以接收上传的文件，即下面的代码。
     * 3，在 Spring 中配置 multipart 文件处理器，详见 spring-servlet-config_anno.xml。
     */

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String uploadPic() {
        return "uploadPic";// 直接返回逻辑视图名
    }

    // 参数 image 为 MultipartFile 类型，以接收文件上传
    @RequestMapping(value = "/savePic", method = RequestMethod.POST)
    public String uploadPic(User user, @RequestParam(value = "pic") MultipartFile image) {
        String id = userService.save(user);
        try {
            if (!image.isEmpty()) {
                validateImage(image);
                saveImage(id, image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/user/" + id;
    }

    /**
     * 将文件保存在服务器文件系统中
     */
    private void saveImage(String id, MultipartFile image) {
        try {
            String file = getUploadDir() + File.separator + id + ".jpg";
            System.out.println(file);
            FileUtils.writeByteArrayToFile(new File(file), image.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取服务器上传文件的目录路径
     */
    private String getUploadDir(){
        return servletContext.getRealPath("") + File.separator + "images";
    }

    private void validateImage(MultipartFile image) {
        if (!image.getContentType().equals("image/jpeg")) {
            throw new RuntimeException("Only JPG images accepted.");
        }
    }
}
