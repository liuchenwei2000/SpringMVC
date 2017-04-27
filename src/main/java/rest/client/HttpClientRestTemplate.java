package rest.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import vo.Book;

import java.io.IOException;

/**
 * 使用 Apache HttpClient 调用 REST API 进行应用程序间的通信
 * <p>
 * <p>
 * Created by liuchenwei on 2016/4/14.
 */
public class HttpClientRestTemplate {

    /**
     * 获取所有 Book 对象
     */
    public Book[] retrieveBooks() {
        // 创建 HttpClient
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // REST API 地址
        String url = "http://localhost:8080/SpringMVC/api/books";

        HttpGet httpGet = new HttpGet(url);// GET 请求
        httpGet.setHeader(new BasicHeader("Accept", "application/json"));// 设置请求头

        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpGet);// 执行 GET 请求
            HttpEntity entity = response.getEntity();// 解析结果
            // 利用 Jackson 将 JSON 转换成 Java 对象并返回
            return new ObjectMapper().readValue(entity.getContent(), Book[].class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("retrieve books failed:" + e.getMessage());
        } finally {
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Book[] books = new HttpClientRestTemplate().retrieveBooks();
        for (Book book : books) {
            System.out.println(book.getName());
        }
    }
}
