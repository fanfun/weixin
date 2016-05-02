package com.weixin.platform.operate.common;

import com.weixin.platform.domain.state.ExecEnum;
import com.weixin.platform.operate.BaseOperate;
import com.weixin.platform.wxtools.Tools;
import com.weixin.platform.wxtools.vo.recv.WxRecvMsg;
import com.weixin.platform.wxtools.vo.send.WxSendMsg;
import com.weixin.platform.wxtools.vo.send.WxSendNewsMsg;
import com.weixin.platform.wxtools.vo.send.WxSendTextMsg;

/**
 * Created by ruanzf on 2015/5/8.
 */
public class Reset extends BaseOperate {
    @Override
    public WxSendMsg execute(WxRecvMsg recvMsg, WxSendMsg sendMsg) {
        return new WxSendTextMsg(sendMsg, Tools.RESET_MSG);
    }

    @Override
    public void refresh(WxRecvMsg msg) {
        stateService.refresh(ExecEnum.DONE, msg.getFromUser());
    }
}
