package com.sanyka.weixin.api.mp;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sanyka.weixin.api.mp.message.MessageBase;
import com.sanyka.weixin.util.SignUtil;

/**
 * 微信服务端验证
 * 
 * @author OF
 * @date 2017年10月10日
 */
@WebServlet(name = "coreInit", loadOnStartup = 1, urlPatterns = { "/wx/sing.html" })
public class CoreInit extends HttpServlet {
	private static final Logger log = LoggerFactory.getLogger(CoreInit.class);
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// 加载配置文件
		log.info("init weixin config file start");
		// 手动配置
		AppConfig.configConstant();
		// 加载配置文件
		WeixinProperties.init();
		// 初始化常量
		AppConfig.init();
		log.info("init weixin config file end");
	}

	/**
	 * <pre>
	 * get 请求验证消息
	 * </pre>
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		OutputStream ops = null;
		try {
			// 获取参数
			String signature = request.getParameter("signature");// 微信加密签名
			String timestamp = request.getParameter("timestamp");// 时间戳
			String nonce = request.getParameter("nonce"); // 随机数
			String echostr = request.getParameter("echostr");
			log.debug("WeChat signature:" + signature + ",timestamp:"
					+ timestamp + ",nonce:" + nonce + "echostr" + echostr);
			// 签名判断
			if (SignUtil.getSignature(signature, AppConfig.WX_TOKEN, timestamp,
					nonce)) {
				ops = resp.getOutputStream();
				ops.write(echostr.getBytes());
				ops.flush();
				ops.close();
				log.debug("接入成功！！！");
			} else {
				log.error("接入失败token验证失败！！！！！！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("签名失败！！！！！！");
		} finally {
			if (null != ops) {
				ops.close();
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("============================处理微信消息开始===============================");
		try {
			// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			// 调用核心业务类接收消息、处理消息
			MessageBase messageBase = HandlerFactory.getMessageHandler();
			String result = messageBase.execute(request);
			OutputStream out = response.getOutputStream();
			if (result == null) {
				result = "";
			}
			out.write(result.getBytes("utf-8"));
			out.close();
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("============================处理微信消息结束===============================");
	}

}
