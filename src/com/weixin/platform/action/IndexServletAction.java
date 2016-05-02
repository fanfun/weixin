package com.weixin.platform.action;

import com.weixin.platform.operate.Caesar;
import com.weixin.platform.wxtools.Tools;
import com.weixin.platform.wxtools.WeiXinTools;
import com.weixin.platform.wxtools.vo.recv.*;
import com.weixin.platform.wxtools.vo.send.WxSendMsg;
import com.weixin.platform.wxtools.vo.send.WxSendNewsMsg;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 微信消息处理 请求地址 http://域名/weixin.do
 */
public class IndexServletAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(IndexServletAction.class);

	// token标识
//	private static final String TOKEN = "xmgcgcapp";
	private static final String TOKEN = "exchange";

	/**
	 * post请求接受用户输入的消息，和消息回复
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("im in here ok");
		try {
			WxRecvMsg msg = WeiXinTools.recv(request.getInputStream());
			WxSendMsg sendMsg = WeiXinTools.builderSendByRecv(msg);
			/** -------------------1.接受到的文本或图片消息，回复处理-------------------------- */
			if ((msg instanceof WxRecvTextMsg) || (msg instanceof WxRecvPicMsg)) {
				Caesar caesar = new Caesar();
				sendMsg = caesar.execute(msg);
				WeiXinTools.send(sendMsg, response.getOutputStream());
			}

			/** -------------------2.接受到的事件消息-------------------------- */
			else if (msg instanceof WxRecvEventMsg) {
				WxRecvEventMsg recvMsg = (WxRecvEventMsg) msg;
				String event = recvMsg.getEvent();

				if ("subscribe".equals(event)) {
					// 订阅消息
					sendMsg = new WxSendNewsMsg(sendMsg).addItem(Tools.GUIDE, Tools.SUBSCRIBE, Tools.PIC_MANUAL, Tools.GUIDE_URL);
					WeiXinTools.send(sendMsg, response.getOutputStream());
					return;
				} else if ("unsubscribe".equals(event)) {
					return;
				} else if ("CLICK".equals(event)) {
					return;
				} else if ("VIEW".equals(event)) {
					return;
				} else {
					return;
				}

			}

			/** -------------------3.接受到的地理位置信息-------------------------- */
			else if (msg instanceof WxRecvGeoMsg) {
				WxRecvGeoMsg recvMsg = (WxRecvGeoMsg) msg;
				return;
			}

			/** -------------------4.接受到的音频消息-------------------------- */
			else if (msg instanceof WxRecvVoiceMsg) {
				WxRecvVoiceMsg recvMsg = (WxRecvVoiceMsg) msg;
				return;
			}

			/** ------------------5.接受到的未能识别的消息-------------------- */
			else {
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * get请求进行验证服务器是否正常
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 进行接口验证
		 */
		logger.info("doGet:");
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		logger.info(signature + " " + timestamp + " " + nonce + " " + echostr);
		if (null != timestamp && null != nonce && null != echostr && null != signature) {
			if (WeiXinTools.access(TOKEN, signature, timestamp, nonce)) {
				response.getWriter().write(echostr);
				return;
			}
			return;
		} else {
			return;
		}
	}

	public void init() throws ServletException {
		String prefix = getServletContext().getRealPath("/");
		String file = getInitParameter("log4jFile");
		if(file!=null){
			PropertyConfigurator.configure(prefix + file);
		}
		System.out.println("Log4j initialized suc!");
	}
}
