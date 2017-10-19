package com.sanyka.weixin.utils.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sanyka.weixin.exception.WeixinException;

/**
 * socket 发送报文工具
 *
 * @author OF
 *
 */
public class SocketClient {
	protected static Logger	log		= LoggerFactory.getLogger(SocketClient.class);
	/**
	 * 需要设置报文头的长度
	 */
	private int				mLength	= 4;
	/**
	 * 设置发送的端口
	 */
	private int				port	= 80;
	/**
	 * 获取发送的IP
	 */
	private String			ip		= "127.0.0.1";
	/**
	 * 设置数据编码集
	 */
	public String			charset	= "utf-8";
	
	public int				timeout	= 1000 * 60;
	
	/**
	 * 初始化构造方法,传入默认参数值
	 *
	 * @param mLength
	 *            报文头的长度
	 */
	public SocketClient(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	/**
	 * 初始化构造方法,传入默认参数值
	 *
	 * @param mLength
	 *            报文头的长度
	 */
	public SocketClient(String ip, int port, int timeout) {
		this.ip = ip;
		this.port = port;
		this.timeout = timeout;
	}
	
	/**
	 * 初始化构造方法,传入默认参数值
	 *
	 * @param ip
	 * @param port
	 * @param timeout
	 * @param mLength
	 */
	public SocketClient(String ip, int port, int timeout, int mLength) {
		this.ip = ip;
		this.port = port;
		this.timeout = timeout;
		this.mLength = mLength;
	}
	
	/**
	 * 初始化构造方法,传入默认参数值
	 *
	 * @param ip
	 * @param port
	 * @param timeout
	 * @param charset
	 */
	public SocketClient(String ip, int port, int timeout, String charset) {
		this.ip = ip;
		this.port = port;
		this.timeout = timeout;
		this.charset = charset;
	}
	
	/**
	 * 发送信息,使用构造方法的初始化属性
	 *
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	public String sendmsg(String msg) throws Exception {
		return sendmsg(ip, port, timeout, msg);
		
	}
	
	/**
	 * 发送信息,使用构造方法的初始化属性
	 *
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	public String sendmsg(String msg, int mLength) throws Exception {
		return sendmsg(ip, port, timeout, msg, charset, mLength);
		
	}
	
	/**
	 * 发送信息到指定的地址,使用默认的编码集 , charset : utf-8
	 *
	 * @param ip
	 * @param port
	 * @param timeout
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	public String sendmsg(String ip, int port, int timeout, String msg)
			throws Exception {
		return sendmsg(ip, port, timeout, msg, charset, mLength);
	}
	
	/**
	 * 发送信息到指定的服务器和用指定的编码集
	 *
	 * @param ip
	 * @param port
	 * @param timeout
	 * @param msg
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public String sendmsg(String ip, int port, int timeout, String msg,
			String charset, int mLength) throws Exception {
		String retmsg = "";
		Socket socket = null;
		try {
			socket = new Socket(ip, port);
			socket.setKeepAlive(true);
			socket.setSoTimeout(timeout);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WeixinException("can't connect to host ：" + ip);
		}
		OutputStream out = null;
		try {
			out = socket.getOutputStream();
			byte[] wb = msg.getBytes(charset);
			String ml = "%0" + mLength + "d";
			byte[] header = String.format(ml, wb.length).getBytes(charset);
			out.write(header);
			out.write(wb);
			out.flush();
			socket.shutdownOutput();
		} catch (Exception e) {
			e.printStackTrace();
			out.close();
			socket.close();
			throw new WeixinException("send msg error : " + e.getMessage());
		}
		InputStream in = null;
		try {
			in = socket.getInputStream();
			byte[] b = new byte[mLength];
			int len = 0;
			if (in.read(b) != -1) {
				len = Integer.valueOf(new String(b, charset)).intValue();
			}
			if (len <= 0) {
				return retmsg;
			}
			b = new byte[len];
			int off = 0;
			int pos = 0;
			while (true) {
				if ((pos = in.read(b, off, len - off)) == -1) {
					throw new WeixinException("this msg is less of length ");
				}
				off += pos;
				if (off >= len)
					break;
			}
			retmsg += new String(b, charset);
			return retmsg;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WeixinException("recv msg error : " + e.getMessage());
		} finally {
			out.close();
			in.close();
			socket.close();
		}
	}
}
