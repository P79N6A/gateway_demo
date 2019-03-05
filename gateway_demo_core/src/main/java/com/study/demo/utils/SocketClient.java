package com.study.demo.utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
*
* @Description: 
* @ClassName: SocketClient 
* @author zhufj
* @date 2019年3月5日 下午5:49:06 
*
 */
public class SocketClient {
	private static Log        log                       = LogFactory
            .getLog(SocketClient.class);
    /**
     * 
     * 发送消息
     * @param msg 发送内容
     * @param timeOut 超时
     * @param address 地址
     * @param port 端口
     * @return
     * @throws IOException
     */
    
    public static byte[] sendMessage(byte[] sendStr, String address, int port, String correlationID)
            throws Exception {
    	Selector selector = null;
    	SocketChannel channel = null;
    	try {
        	long start = System.currentTimeMillis();
        	//获得通道管理器
        	selector = Selector.open();
        	//获取socket通道
        	channel = SocketChannel.open();
        	channel.configureBlocking(false);
        	//客户端连接服务器，需要调用channel.finishConnect();才能实际完成连接。
        	channel.connect(new InetSocketAddress(address, port));
        	//为该通道注册SelectionKey.OP_CONNECT事件
        	channel.register(selector, SelectionKey.OP_CONNECT);
        	while (true) {
            	//选择注册过的io操作的事件(第一次为SelectionKey.OP_CONNECT)
            	selector.select();
            	Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
            	while (ite.hasNext()) {
                	SelectionKey key = ite.next();
                	//删除已选的key，防止重复处理
                	ite.remove();
                	if (key.isConnectable()) {
                	SocketChannel c = (SocketChannel) key.channel();
                	//如果正在连接，则完成连接
                   	if (c.isConnectionPending()) {
                    	c.finishConnect();
                    	}
                    	c.configureBlocking(false);
                    	//向服务器发送消息
                    	c.write(ByteBuffer.wrap(sendStr));
                    	//连接成功后，注册接收服务器消息的事件
                    	c.register(selector, SelectionKey.OP_READ);
                    	} else if (key.isReadable()) { //有可读数据事件。
                    		SocketChannel c = (SocketChannel) key.channel();
                        	//根据中行的返回报文大致分配300字节的缓冲区，如果读取错误则适当增加
                        	ByteBuffer buffer = ByteBuffer.allocate(600);
                        	int len=c.read(buffer); 
                        	byte[] data = buffer.array();  
                        	byte[] result=new byte[len];
                        	System.arraycopy(data, 0, result, 0, len);
                        	
                        	log.info("correlationID[" + correlationID
                					+ "] 响应时间：" + (System.currentTimeMillis() - start) + "ms; buffer len:" + len);
                        	return result;
                    	}
                 }
            }
        	} catch (IOException e) {
            	log.error("correlationID[" + correlationID
                					+ "]调用socket异常", e);
            	throw e;
        	} finally {
            	try {
                	if (selector != null) {
                    	selector.close();
                	}
                	if (channel != null) {
                    	channel.close();
                	}
                	} catch (IOException e) {
                    	log.error("correlationID[" + correlationID
            					+ "]关闭socket异常", e);
                	}
        	}
    }
}
