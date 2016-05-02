package com.weixin.platform.operate.askbuy;

import com.weixin.platform.domain.Process;
import com.weixin.platform.domain.Product;
import com.weixin.platform.domain.Student;
import com.weixin.platform.domain.state.ExecEnum;
import com.weixin.platform.domain.state.SellEnum;
import com.weixin.platform.domain.state.TradeEnum;
import com.weixin.platform.operate.BaseOperate;
import com.weixin.platform.wxtools.Tools;
import com.weixin.platform.wxtools.vo.recv.WxRecvMsg;
import com.weixin.platform.wxtools.vo.recv.WxRecvTextMsg;
import com.weixin.platform.wxtools.vo.send.WxSendMsg;
import com.weixin.platform.wxtools.vo.send.WxSendTextMsg;

/**
 * Created by Administrator on 2015/5/1.
 */
public class AskBuy extends BaseOperate {

    public WxSendMsg execute(WxRecvMsg recvMsg, WxSendMsg sendMsg) {
        Student student =studentService.getByName(recvMsg.getFromUser());
        if(student != null) {
            this.execEnum = ExecEnum.DONE;
            return new WxSendTextMsg(sendMsg, Tools.thankyou(student.getNick(), student.getPhone(), student.getName()));
        }else {
            this.execEnum = ExecEnum.INFORMATION;
            return new WxSendTextMsg(sendMsg, Tools.INFORMATION);
        }
    }

    @Override
    public void refresh(WxRecvMsg msg) {
        WxRecvTextMsg textMsg = (WxRecvTextMsg)msg;
        Process process = new Process(execEnum.getName());
        process.setName(msg.getFromUser());
        process.setDetail(textMsg.getContent());
        process.setSellType(SellEnum.ASK_BUY.getCode());
        process.setTradeType("");
        this.stateService.refresh(process);

        if(this.execEnum.equals(execEnum.DONE)) {
            Product product = new Product(process);
            productService.save(product, process.getTradeType());
        }
    }
}
