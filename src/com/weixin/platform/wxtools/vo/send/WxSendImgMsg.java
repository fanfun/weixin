package com.weixin.platform.wxtools.vo.send;

import org.jdom.Document;
import org.jdom.Element;

//208041948
public class WxSendImgMsg extends WxSendMsg {
	private String imgId;

	public WxSendImgMsg(WxSendMsg msg, String imgId) {
		super(msg);
		setMsgType("image");
		this.imgId = imgId;
	}

	public String getImgId() {
		return imgId;
	}

	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

	@Override
	public Document toDocument() {
		Document doc = new Document();
		Element root = new Element("xml");
		doc.setRootElement(root);

		createElement(root,"ToUserName", getToUser());
		createElement(root,"FromUserName", getFromUser());
		createElement(root,"CreateTime", getCreateDt());
		createElement(root,"MsgType", getMsgType());
		createElement(root,"FuncFlag", isStar()?"1":"0");

		Element elem = new Element("Image");
		root.getChildren().add(elem);
		createElement(elem, "MediaId", getImgId());

		return doc;
	}
}
