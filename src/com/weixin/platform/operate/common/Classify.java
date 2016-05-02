//package com.weixin.platform.operate.common;
//
//import com.weixin.platform.domain.*;
//import com.weixin.platform.domain.Process;
//import com.weixin.platform.domain.state.ExecEnum;
//import com.weixin.platform.operate.BaseOperate;
//import com.weixin.platform.wxtools.Tools;
//import com.weixin.platform.wxtools.vo.recv.WxRecvMsg;
//import com.weixin.platform.wxtools.vo.recv.WxRecvTextMsg;
//import com.weixin.platform.wxtools.vo.send.WxSendMsg;
//import com.weixin.platform.wxtools.vo.send.WxSendTextMsg;
//
///**
// * Created by Administrator on 2015/5/2.
// */
//public class Classify extends BaseOperate{
//
//    private int item = 0;
//
//    @Override
//    public WxSendMsg execute(WxRecvMsg recvMsg, WxSendMsg sendMsg) {
//        if(recvMsg instanceof WxRecvTextMsg) {
//            WxRecvTextMsg textMsg = (WxRecvTextMsg)recvMsg;
//            String content = textMsg.getContent();
//            try {
//                item = Integer.parseInt(content);
//                if(item >=0 && item <= Tools.CLASSIFY_TYPE) {
//                    return SellDone(recvMsg, sendMsg);
//                }else {
//                    refresh = false;
//                    return new WxSendTextMsg(sendMsg, Tools.CLASSIFY_ERROR2);
//                }
//            }catch (Exception e) {
//                refresh = false;
//                return new WxSendTextMsg(sendMsg, Tools.CLASSIFY_ERROR);
//            }
//        }else {
//            refresh = false;
//            return new WxSendTextMsg(sendMsg, Tools.CLASSIFY_ERROR);
//        }
//    }
//
//    @Override
//    public void refresh(WxRecvMsg msg) {
//        Process process = stateService.getProcess(msg.getFromUser());
//        process.setState(execEnum.getName());
//        process.setItem(item);
//        Product product = new Product(process);
//        productService.save(product, process.getSellType());
//        stateService.refresh(process);
//    }
//
//    @Override
//    public WxSendMsg SellDone(WxRecvMsg recvMsg, WxSendMsg sendMsg) {
//        Student student = studentService.getByName(recvMsg.getFromUser());
//        if (student != null) {
//            this.execEnum = ExecEnum.DONE;
//            return new WxSendTextMsg(sendMsg, Tools.thankyou(student.getNick(), student.getPhone()));
//        } else {
//            this.execEnum = ExecEnum.INFORMATION;
//            return new WxSendTextMsg(sendMsg, Tools.INFORMATION);
//        }
//    }
//}
