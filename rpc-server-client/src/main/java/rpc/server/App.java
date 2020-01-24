package rpc.server;

import rpc.service.HelloService;

public class App {
    public static void main(String[] args) {
        System.out.println("rpc client");
        RpcProxyClient rpcProxyClient = new RpcProxyClient();
        HelloService proxy = rpcProxyClient.proxy(HelloService.class, "localhost", 8080);
        proxy.sayHello("ybs");
    }
}
