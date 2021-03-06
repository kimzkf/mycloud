package com.example.eu_client.controller;
import com.example.bean.User;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    @Autowired
    private  RestTemplate restTemplate;//spring 提供的一个用于访问rest接口的模板对象
   //使用配置文件获取地址
    @Value("${user.url}")
    private String server_url;
    // private String server_url="http://localhost:8801/user/";

    @Autowired
    private EurekaClient euClient;

    @GetMapping("/order/{id}")
    public User showOrder(@PathVariable("id") String id){

        //根据配置别名，获取地址
        InstanceInfo instanceInfo=euClient.getNextServerFromEureka("EUSERVER",false);
        String homeurl= instanceInfo.getHomePageUrl();

        //通过访问rest,获取json对象，转换为user对象
        User user = restTemplate.getForObject(homeurl+"/user/"+id, User.class);
        return user;
    }
    @GetMapping("/ribbon/{id}")
    public User ribbon(@PathVariable("id") String id){
        //ribbon使用配置别名
        //会请求别名一致的服务
        User user = restTemplate.getForObject("http://euserver/user/"+id, User.class);
        return user;
    }
}
