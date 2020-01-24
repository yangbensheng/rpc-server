package rpc.service;

import rpc.vo.User;

public interface HelloService {
    String sayHello(String content);

    String sayUser(User user);

}
