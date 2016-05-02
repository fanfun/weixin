package com.weixin.platform.operate;


import com.weixin.platform.domain.Student;
import com.weixin.platform.domain.state.ExecEnum;
import com.weixin.platform.service.ProductService;
import com.weixin.platform.service.StateService;
import com.weixin.platform.service.StudentService;
import com.weixin.platform.wxtools.Tools;
import com.weixin.platform.wxtools.WeiXinTools;
import com.weixin.platform.wxtools.vo.recv.WxRecvMsg;
import com.weixin.platform.wxtools.vo.send.WxSendMsg;
import com.weixin.platform.wxtools.vo.send.WxSendTextMsg;

/**
 * Created by ruanzf on 2015/4/23.
 */
public class BaseOperate implements Operate {

    protected StateService stateService;
    protected ExecEnum execEnum;
    protected boolean refresh;
    protected static StudentService studentService;
    protected static ProductService productService;


    public BaseOperate() {
        this.execEnum = ExecEnum.SHUNT;
        this.stateService = new StateService();
        this.refresh = true;

        if (studentService == null) {
            studentService = new StudentService();
        }

        if (productService == null) {
            productService = new ProductService();
        }
    }

    @Override
    public WxSendMsg execute(WxRecvMsg recvMsg, WxSendMsg sendMsg) {
        return null;
    }

    @Override
    public void refresh(WxRecvMsg msg) {
        this.stateService.refresh(execEnum, msg.getFromUser());
    }

    @Override
    public WxSendMsg Operate(WxRecvMsg recvMsg) {
        WxSendMsg sendMsg = WeiXinTools.builderSendByRecv(recvMsg);
        WxSendMsg msg = execute(recvMsg, sendMsg);
        if (refresh) {
            refresh(recvMsg);
        }
        return msg;
    }

    public WxSendMsg SellDone(WxRecvMsg recvMsg, WxSendMsg sendMsg) {
        Student student = studentService.getByName(recvMsg.getFromUser());
        if (student != null) {
            this.execEnum = ExecEnum.DONE;
            return new WxSendTextMsg(sendMsg, Tools.thankyou(student.getNick(), student.getPhone(), student.getName()));
        } else {
            this.execEnum = ExecEnum.INFORMATION;
            return new WxSendTextMsg(sendMsg, Tools.INFORMATION);
        }
    }

}
