package rpc.server;

import java.lang.reflect.Proxy;

public class RpcProxyClient {
    public <T>T proxy(Class<T> interFace,String host,int port){
        return (T) Proxy.newProxyInstance(interFace.getClassLoader(),
                new Class[]{interFace},new RpcInvocationHandler(host,port));
    }
}
