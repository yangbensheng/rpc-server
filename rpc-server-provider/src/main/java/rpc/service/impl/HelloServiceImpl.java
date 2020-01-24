package rpc.service.impl;

import rpc.service.HelloService;
import rpc.vo.User;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String content) {
        System.out.println("request come in "+ content);
        return "say hello "+ content;
    }

    @Override
    public String sayUser(User user) {
        System.out.println("user come in "+ user);
        return "success";
    }
}
