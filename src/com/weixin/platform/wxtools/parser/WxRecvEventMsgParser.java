package com.weixin.platform.wxtools.parser;

import com.weixin.platform.wxtools.vo.recv.WxRecvEventMsg;
import com.weixin.platform.wxtools.vo.recv.WxRecvMsg;
import org.jdom.Element;
import org.jdom.JDOMException;

public class WxRecvEventMsgParser extends WxRecvMsgBaseParser {

	@Override
	protected WxRecvEventMsg parser(Element root, WxRecvMsg msg) throws JDOMException {
		String event = getElementText(root, "Event");
		String eventKey = getElementText(root, "EventKey");
		
		return new WxRecvEventMsg(msg, event,eventKey);
	}

}
