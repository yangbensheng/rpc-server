package rpc.service.impl;

public class app {
    public static void main(String[] args) {
        RpcProxyService rpcProxyService = new RpcProxyService();
        rpcProxyService.publish(8080);
    }
}
