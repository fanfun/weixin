package com.weixin.platform.operate.sell;


import com.weixin.platform.domain.Process;
import com.weixin.platform.domain.Product;
import com.weixin.platform.domain.state.ExecEnum;
import com.weixin.platform.operate.BaseOperate;
import com.weixin.platform.wxtools.Tools;
import com.weixin.platform.wxtools.vo.recv.WxRecvMsg;
import com.weixin.platform.wxtools.vo.recv.WxRecvPicMsg;
import com.weixin.platform.wxtools.vo.recv.WxRecvTextMsg;
import com.weixin.platform.wxtools.vo.send.WxSendMsg;
import com.weixin.platform.wxtools.vo.send.WxSendTextMsg;

/**
 * Created by ruanzf on 2015/4/27.
 */
public class MoreSell extends BaseOperate {

    public WxSendMsg execute(WxRecvMsg recvMsg, WxSendMsg sendMsg) {
        if (recvMsg instanceof WxRecvPicMsg) {
            Process process = stateService.getProcess(recvMsg.getFromUser());
            if (process.pictureSize() >= Tools.MAX_PIC) {
                return SellDone(recvMsg, sendMsg);
            }else {
                int pictureCount = process.pictureSize() + 1;
                this.execEnum = ExecEnum.MORE_SELL;
                return new WxSendTextMsg(sendMsg, Tools.recvPicture(pictureCount));
            }
        } else if (recvMsg instanceof WxRecvTextMsg) {
            String content = ((WxRecvTextMsg) recvMsg).getContent();
            if (content.contains(Tools.OK)) {
                Process process = stateService.getProcess(recvMsg.getFromUser());
                if(process.pictureSize() <= 0) {
                    this.refresh = false;
                    return new WxSendTextMsg(sendMsg, Tools.PICTURE_NO);
                }else {
                    return SellDone(recvMsg, sendMsg);
                }
            } else if(content.contains(Tools.WITHOUT)) {
                return SellDone(recvMsg, sendMsg);
            }else {
                this.refresh = false;
                return new WxSendTextMsg(sendMsg, Tools.PICTURE_DONE);
            }
        } else {
            this.refresh = false;
            return new WxSendTextMsg(sendMsg, Tools.PICTURE_DONE);
        }
    }

    @Override
    public void refresh(WxRecvMsg msg) {
        if (msg instanceof WxRecvPicMsg) {
            WxRecvPicMsg picMsg = (WxRecvPicMsg) msg;
            Process process = stateService.getProcess(picMsg.getFromUser());
            if (process != null) {
                process.appendPicture(picMsg.getPicUrl());
                this.stateService.refresh(process);
            } else {
                process = new Process(ExecEnum.SHUNT.getName());
                this.stateService.refresh(process);
            }
        }
        if (execEnum.equals(ExecEnum.DONE)) {
            Process process = stateService.getProcess(msg.getFromUser());
            process.setState(execEnum.getName());
            Product product = new Product(process);
            productService.save(product, process.getTradeType());
            stateService.refresh(execEnum, msg.getFromUser());
        } else if(execEnum.equals(ExecEnum.INFORMATION)) {
            stateService.refresh(execEnum, msg.getFromUser());
        }
    }
}
