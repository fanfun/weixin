package com.weixin.platform.operate.sell;

import com.weixin.platform.domain.Process;
import com.weixin.platform.domain.state.ExecEnum;
import com.weixin.platform.domain.state.ProductEnum;
import com.weixin.platform.domain.state.TradeEnum;
import com.weixin.platform.operate.BaseOperate;
import com.weixin.platform.wxtools.Tools;
import com.weixin.platform.wxtools.vo.recv.WxRecvMsg;
import com.weixin.platform.wxtools.vo.recv.WxRecvTextMsg;
import com.weixin.platform.wxtools.vo.send.WxSendMsg;
import com.weixin.platform.wxtools.vo.send.WxSendTextMsg;

/**
 * Created by ruanzf on 2015/5/8.
 */
public class Category extends BaseOperate {

    private String categorys;

    @Override
    public WxSendMsg execute(WxRecvMsg recvMsg, WxSendMsg sendMsg) {
        if(recvMsg instanceof WxRecvTextMsg) {
            WxRecvTextMsg textMsg = (WxRecvTextMsg)recvMsg;
            String content = textMsg.getContent();
            return parseCategory(sendMsg, content);
        }else {
            refresh = false;
            return new WxSendTextMsg(sendMsg, Tools.SELL_TYPE_ERROR);
        }
    }

    @Override
    public void refresh(WxRecvMsg msg) {
        Process process = stateService.getProcess(msg.getFromUser());
        process.setSellType(ProductEnum.SELL.getCode());
        process.setState(execEnum.getName());
        process.setTradeType(categorys);
        this.stateService.refresh(process);
    }

    public WxSendMsg parseCategory(WxSendMsg sendMsg, String content) {
        categorys = content.replace("／", "/");
        String[] typeArray = categorys.split("/");

        int size = 3;
        int type = 0;

        if(typeArray.length < size) {
            size = typeArray.length;
        }

        try {
            for(int i=0; i < size; i++) {
                String typeStr = typeArray[i];
                type = Integer.parseInt(typeStr);
                if(type <= 0 || type > TradeEnum.size()) {
                    refresh = false;
                    return new WxSendTextMsg(sendMsg, Tools.SELL_TYPE_ERROR2);
                }
            }
            this.execEnum = ExecEnum.MORE_SELL;
            return  new WxSendTextMsg(sendMsg, Tools.SEND_PIC);
        }catch (Exception e) {
            refresh = false;
            return new WxSendTextMsg(sendMsg, Tools.SELL_TYPE_ERROR);
        }
    }
}
