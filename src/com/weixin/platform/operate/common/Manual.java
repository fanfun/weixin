package com.weixin.platform.operate.common;

import com.weixin.platform.operate.BaseOperate;
import com.weixin.platform.wxtools.Tools;
import com.weixin.platform.wxtools.vo.recv.WxRecvMsg;
import com.weixin.platform.wxtools.vo.send.WxSendMsg;
import com.weixin.platform.wxtools.vo.send.WxSendNewsMsg;

/**
 * Created by ruanzf on 2015/5/8.
 */
public class Manual extends BaseOperate {
    @Override
    public WxSendMsg execute(WxRecvMsg recvMsg, WxSendMsg sendMsg) {
        return new WxSendNewsMsg(sendMsg).addItem(Tools.GUIDE, Tools.MANUAL_DETAIL, Tools.PIC_MANUAL, Tools.GUIDE_URL);
    }

    @Override
    public void refresh(WxRecvMsg msg) {
    }
}
