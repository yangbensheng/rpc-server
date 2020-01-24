package rpc.service.impl;

import rpc.vo.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class SocketHandler implements Runnable {
    private Socket socket;
    private Class<?> clazz;

    public SocketHandler(Socket socket, Class<?> clazz) {
        this.socket = socket;
        this.clazz = clazz;
    }

    @Override
    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest request = (RpcRequest) inputStream.readObject();
            Object rsp = this.invoke(request);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(rsp);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Object invoke(RpcRequest request) throws Exception {
        Object[] args = request.getArgs();
        Class<?>[] types = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            types[i] = args[i].getClass();
        }
        Object instance = clazz.newInstance();
        Method method = clazz.getMethod(request.getMethodName(), types);
        return method.invoke(instance,args);
    }
}
