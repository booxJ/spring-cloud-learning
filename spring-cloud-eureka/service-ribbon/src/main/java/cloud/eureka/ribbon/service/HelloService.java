package cloud.eureka.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "error")
    public String hello(String name) {
        return restTemplate.getForObject("http://SERVICE-HELLO/hello?name=" + name, String.class);
    }

    public String error(String name) {
        return "hi," + name + ",sorry,error!";
    }
}
