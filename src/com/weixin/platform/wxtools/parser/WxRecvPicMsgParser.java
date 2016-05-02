package com.weixin.platform.wxtools.parser;

import com.weixin.platform.wxtools.vo.recv.WxRecvMsg;
import org.jdom.Element;
import org.jdom.JDOMException;

import com.weixin.platform.wxtools.vo.recv.WxRecvPicMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WxRecvPicMsgParser extends WxRecvMsgBaseParser {
	private final static Logger logger = LoggerFactory.getLogger(WxRecvPicMsgParser.class);

	@Override
	protected WxRecvPicMsg parser(Element root, WxRecvMsg msg) throws JDOMException {
		logger.info("from {}, picture {}", msg.getFromUser(), getElementText(root, "PicUrl"));
		return new WxRecvPicMsg(msg, getElementText(root, "PicUrl"));
	}

}
