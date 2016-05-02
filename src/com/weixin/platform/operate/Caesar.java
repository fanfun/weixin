package com.weixin.platform.operate;

import com.weixin.platform.wxtools.vo.recv.WxRecvMsg;
import com.weixin.platform.wxtools.vo.send.WxSendMsg;

/**
 * Created by ruanzf on 2015/4/23.
 */
public class Caesar {

    private OperateFactory operateFactory;

    public Caesar() {
        this.operateFactory = new OperateFactory();
    }

    public WxSendMsg execute(WxRecvMsg msg) {
        Operate operate = operateFactory.build(msg);
        return operate.Operate(msg);
    }
}
