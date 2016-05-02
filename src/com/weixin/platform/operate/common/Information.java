package com.weixin.platform.operate.common;

import com.weixin.platform.domain.*;
import com.weixin.platform.domain.Process;
import com.weixin.platform.domain.state.ExecEnum;
import com.weixin.platform.operate.BaseOperate;
import com.weixin.platform.wxtools.Tools;
import com.weixin.platform.wxtools.vo.recv.WxRecvMsg;
import com.weixin.platform.wxtools.vo.recv.WxRecvTextMsg;
import com.weixin.platform.wxtools.vo.send.WxSendMsg;
import com.weixin.platform.wxtools.vo.send.WxSendTextMsg;

/**
 * Created by Administrator on 2015/5/3.
 */
public class Information extends BaseOperate{

    private Student student;

    @Override
    public WxSendMsg execute(WxRecvMsg recvMsg, WxSendMsg sendMsg) {
        if(recvMsg instanceof WxRecvTextMsg) {
            WxRecvTextMsg textMsg = (WxRecvTextMsg)recvMsg;
            String content = textMsg.getContent();
            int start = content.indexOf(Tools.division);
            int len = start + 3;
            if(start <= 0 || content.length() < len) {
                refresh = false;
                return new WxSendTextMsg(sendMsg, Tools.INFORMATION_ERROR);
            }else {
                this.execEnum = ExecEnum.DONE;
                initStudent(content, start, recvMsg.getFromUser());
                return new WxSendTextMsg(sendMsg, Tools.thankyou(student.getNick(), student.getPhone(), student.getName()));
            }
        }else {
            refresh = false;
            return new WxSendTextMsg(sendMsg, Tools.INFORMATION_ERROR);
        }
    }

    @Override
    public void refresh(WxRecvMsg msg) {
        Process process = stateService.getProcess(msg.getFromUser());
        process.setState(execEnum.getName());
        studentService.save(student);
        Product product = new Product(process);
        productService.save(product, process.getTradeType());
        stateService.refresh(process);
    }

    public void initStudent(String content, int start, String name) {
        student = new Student();
        String nick = content.substring(0, start);
        String phone = content.substring(start + 1);
        student.setName(name);
        student.setNick(nick);
        student.setPhone(phone);
        student.setTime(Tools.date2String(System.currentTimeMillis()));
        student.setVisit(1);
    }
}
