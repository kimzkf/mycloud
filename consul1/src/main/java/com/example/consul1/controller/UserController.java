package com.example.consul1.controller;

import com.example.consul1.feign.UserFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import feign.Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.support.FallbackCommand;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private UserFeign userFeign;
    @Value("${name:default}")//默认值
    private String name;
    /**
     * 获取已经注册的服务
     * @return
     */
    @RequestMapping("/getServer")
    public List<String> getServer(){
        List<String> list=discoveryClient.getServices();
        return  list;
    }
    /**
     * 获取服务的集群信息
     * @return
     */
    @RequestMapping("/getServerUrl/{name}")
    public List<String> getServerUrl(@PathVariable String name){
        List<ServiceInstance> list=discoveryClient.getInstances(name);
        List<String> service=new ArrayList<String>();
        for(ServiceInstance serviceInstance:list){
            if(serviceInstance!=null){
                service.add(serviceInstance.getUri().toString());
            }
        }
        return  service;
    }
    /**
     * 调用服务两种方式，一种采用服务别名，另一种直接调用，使用别名在注册中心获取对应服务地址
     * 采用第一种，自动负载均衡
     * @return
     */
   @RequestMapping("/order/{name}")
   public String getOrder(@PathVariable String name){
       String serviceUrl=getUrl(name)+"/order";
       System.out.println(serviceUrl);
       String result=(String) restTemplate.getForObject(serviceUrl,String.class);
       return  result;
   }

    public String getUrl(String name){
      List<ServiceInstance> list=discoveryClient.getInstances(name);
      if(list!=null&&!list.isEmpty()){
          //在此可以实现负载均衡算法
            return list.get(0).getUri().toString();
      }
       return  null;
    }

    /**
     * 使用feign远程访问
     * @param name
     * @return
     */
    //@HystrixCommand(fallbackMethod = "fallback")//错误之后执行方法
    @RequestMapping("/feign")
    public String getOrders(){
        return  userFeign.show();
    }
    public String fallback(){
        return  "sorry!";
    }

    /**
     * 获取配置服务器的配置
     * @return
     */

    @RequestMapping("/config")
    public String getConfig(){
        return  name;
    }
}
