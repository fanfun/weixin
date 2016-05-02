package com.weixin.platform.operate.common;

import com.weixin.platform.domain.state.ResponseEnum;
import com.weixin.platform.domain.state.ShuntEnum;
import com.weixin.platform.operate.BaseOperate;
import com.weixin.platform.wxtools.Tools;
import com.weixin.platform.wxtools.vo.recv.WxRecvMsg;
import com.weixin.platform.wxtools.vo.recv.WxRecvTextMsg;
import com.weixin.platform.wxtools.vo.send.WxSendMsg;
import com.weixin.platform.wxtools.vo.send.WxSendTextMsg;

/**
 * Created by ruanzf on 2015/5/8.
 */
public class SoldOut extends BaseOperate {

    @Override
    public WxSendMsg execute(WxRecvMsg recvMsg, WxSendMsg sendMsg) {
        if(recvMsg instanceof WxRecvTextMsg) {
            WxRecvTextMsg textMsg = (WxRecvTextMsg)recvMsg;
            String content = textMsg.getContent();
            int start = content.indexOf(Tools.division) + 1;
            String code = content.substring(start);
            try {
                int number = Integer.parseInt(code);
                stateService.operateCount(ShuntEnum.SOLD_OUT.getCode());
                ResponseEnum responseEnum = productService.soldOut(number, textMsg.getFromUser());
                return new WxSendTextMsg(sendMsg, responseEnum.getMsg());
            }catch (Exception e) {
                return new WxSendTextMsg(sendMsg, Tools.SOLDOUT_ERROR);
            }
        }else {
            refresh = false;
            return new WxSendTextMsg(sendMsg, Tools.SOLDOUT_ERROR);
        }
    }

    @Override
    public void refresh(WxRecvMsg msg) {
        super.refresh(msg);
    }

}
