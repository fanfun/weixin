package com.weixin.platform.operate;

import com.weixin.platform.wxtools.vo.recv.WxRecvMsg;
import com.weixin.platform.wxtools.vo.send.WxSendMsg;

/**
 * Created by ruanzf on 2015/4/23.
 */
public interface Operate {

    public WxSendMsg Operate(WxRecvMsg msg);

    public WxSendMsg execute(WxRecvMsg recvMsg, WxSendMsg sendMsg);

    public void refresh(WxRecvMsg msg);
}
