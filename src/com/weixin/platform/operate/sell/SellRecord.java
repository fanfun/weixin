package com.weixin.platform.operate.sell;

import com.weixin.platform.domain.Process;
import com.weixin.platform.domain.state.ExecEnum;
import com.weixin.platform.operate.BaseOperate;
import com.weixin.platform.wxtools.Tools;
import com.weixin.platform.wxtools.vo.recv.WxRecvMsg;
import com.weixin.platform.wxtools.vo.recv.WxRecvTextMsg;
import com.weixin.platform.wxtools.vo.send.WxSendMsg;
import com.weixin.platform.wxtools.vo.send.WxSendTextMsg;

/**
 * Created by Administrator on 2015/4/26.
 */
public class SellRecord extends BaseOperate {

    public SellRecord() {
        this.execEnum = ExecEnum.SELL_TYPE;
    }

    public WxSendMsg execute(WxRecvMsg recvMsg, WxSendMsg sendMsg) {
        return new WxSendTextMsg(sendMsg, Tools.SellType());
    }

    @Override
    public void refresh(WxRecvMsg msg) {
        WxRecvTextMsg textMsg = (WxRecvTextMsg) msg;
        Process process = new Process(execEnum.getName());
        process.setName(msg.getFromUser());
        process.setDetail(textMsg.getContent());
        this.stateService.refresh(process);
    }
}
