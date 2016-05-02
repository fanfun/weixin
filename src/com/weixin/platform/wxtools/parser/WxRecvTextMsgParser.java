package com.weixin.platform.wxtools.parser;

import com.weixin.platform.wxtools.vo.recv.WxRecvMsg;
import org.jdom.Element;
import org.jdom.JDOMException;

import com.weixin.platform.wxtools.vo.recv.WxRecvTextMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WxRecvTextMsgParser extends WxRecvMsgBaseParser{

	private final static Logger logger = LoggerFactory.getLogger(WxRecvTextMsgParser.class);


	@Override
	protected WxRecvTextMsg parser(Element root, WxRecvMsg msg) throws JDOMException {
		String content = getElementText(root, "Content");
		String fromUserName = getElementText(root, "FromUserName");
		log(content, fromUserName);
		return new WxRecvTextMsg(msg, content.replace("＃", "#"));
	}

	public void log(String content, String fromUserName) {
		String logContent;
		if(content.length() > 10){
			logContent = content.substring(0, 10);
		}else {
			logContent = content;
		}
		logger.info("content {}, FromUserName {}", logContent, fromUserName);
	}
}
