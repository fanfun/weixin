package com.weixin.platform.wxtools.parser;

import com.weixin.platform.wxtools.vo.recv.WxRecvLinkMsg;
import com.weixin.platform.wxtools.vo.recv.WxRecvMsg;
import org.jdom.Element;
import org.jdom.JDOMException;

public class WxRecvLinkMsgParser extends WxRecvMsgBaseParser {

	@Override
	protected WxRecvLinkMsg parser(Element root, WxRecvMsg msg) throws JDOMException {
		
		String title = getElementText(root, "Title");
		String description = getElementText(root, "Description");
		String url = getElementText(root, "Url");
		return new WxRecvLinkMsg(msg, title, description, url);
	}

}
