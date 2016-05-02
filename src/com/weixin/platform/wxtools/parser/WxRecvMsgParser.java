package com.weixin.platform.wxtools.parser;

import com.weixin.platform.wxtools.vo.recv.WxRecvMsg;
import org.jdom.Document;
import org.jdom.JDOMException;

public interface WxRecvMsgParser {
	WxRecvMsg parser(Document doc) throws JDOMException;
}
