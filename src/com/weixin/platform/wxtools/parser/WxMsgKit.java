package com.weixin.platform.wxtools.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.weixin.platform.wxtools.vo.recv.WxRecvMsg;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.weixin.platform.wxtools.vo.send.WxSendMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public final class WxMsgKit {
	
	private static final Map<String, WxRecvMsgParser> recvParserMap = new HashMap<String, WxRecvMsgParser>();
	private final static Logger logger = LoggerFactory.getLogger(WxMsgKit.class);


	static {
		// 文本消息解析程序
		recvParserMap.put("text", new WxRecvTextMsgParser());
		// 链接消息解析程序
		recvParserMap.put("link", new WxRecvLinkMsgParser());
		// 地址消息解析程序
		recvParserMap.put("location", new WxRecvGeoMsgParser());
		// 图片消息解析程序
		recvParserMap.put("image", new WxRecvPicMsgParser());
		// 事件消息解析程序
		recvParserMap.put("event", new WxRecvEventMsgParser());
		// 语音消息
		recvParserMap.put("voice", new WxRecvVoiceMsgParser());
		
	}
	
	public static WxRecvMsg parse(InputStream in) throws JDOMException, IOException {
		Document dom = new SAXBuilder().build(in);
		Element msgType = dom.getRootElement().getChild("MsgType");
		if(null != msgType) {
			String txt = msgType.getText().toLowerCase();
			WxRecvMsgParser parser = recvParserMap.get(txt);
			logger.info("type {}", txt);
			if(null != parser) {
				return parser.parser(dom);
			} else {
				System.out.println(txt);
			}
		}
		return null;
	}
	
	public static Document parse(WxSendMsg msg) throws JDOMException {
		return msg.toDocument();
	}
}
