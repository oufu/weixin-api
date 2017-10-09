package com.sanyka.weixin.utils;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

public class ZkClientUtil {
	public String path;
	private String ip;
	private int sessionTimeout;
	private int connectionTimeout;
	private String isCreatDir;
	private String isListener;
	public ZkClient zkClient;
	private IZkDataListener iZkDataListener;

	public void init() {
		connection();
		if ("yes".equals(isCreatDir)) {
			if (!exists(path)) {
				this.create(path);
			}
		}
		if ("yes".equals(isListener)) {
			zkClient.subscribeDataChanges(path, iZkDataListener);
		}
	}

	public void connection() {
		zkClient = new ZkClient(ip, sessionTimeout, connectionTimeout,
				new SerializableSerializer());
	}

	/**
	 * 创建
	 * 
	 * @param path
	 *            节点
	 * @return
	 */
	public void create(String path) {
		zkClient.createPersistent(path);
	}

	/**
	 * 创建
	 * 
	 * @param path
	 *            节点
	 * @param data
	 *            数据 节点
	 * @return
	 */
	public void create(String path, Object data) {
		zkClient.createPersistent(path, data);
	}

	/**
	 * 获取
	 * 
	 * @param path
	 *            节点
	 * @return
	 */
	public String get(String path) {
		return zkClient.readData(path);
	}

	/**
	 * 写入、修改
	 * 
	 * @param path
	 *            节点
	 * @param object
	 */
	public void set(String path, Object object) {
		zkClient.writeData(path, object);
	}

	/**
	 * 删除
	 * 
	 * @param path
	 *            节点
	 * @param object
	 */
	public void del(String path) {
		zkClient.delete(path);
	}

	/**
	 * 节点是否存在
	 * 
	 * @param path
	 *            节点
	 * @param object
	 */
	public boolean exists(String path) {
		return zkClient.exists(path);
	}

	/**
	 * 关闭
	 * 
	 * @param path
	 *            节点
	 * @param object
	 */
	public void close(String path) {
		zkClient.close();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getSessionTimeout() {
		return sessionTimeout;
	}

	public void setSessionTimeout(int sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public String getIsCreatDir() {
		return isCreatDir;
	}

	public void setIsCreatDir(String isCreatDir) {
		this.isCreatDir = isCreatDir;
	}

	public String getIsListener() {
		return isListener;
	}

	public void setIsListener(String isListener) {
		this.isListener = isListener;
	}

	public IZkDataListener getiZkDataListener() {
		return iZkDataListener;
	}

	public void setiZkDataListener(IZkDataListener iZkDataListener) {
		this.iZkDataListener = iZkDataListener;
	}

}
