//package com.weixin.platform.operate.sell;
//
//import com.weixin.platform.domain.Process;
//import com.weixin.platform.domain.state.ExecEnum;
//import com.weixin.platform.domain.state.ProductEnum;
//import com.weixin.platform.operate.BaseOperate;
//import com.weixin.platform.wxtools.Tools;
//import com.weixin.platform.wxtools.vo.recv.WxRecvMsg;
//import com.weixin.platform.wxtools.vo.recv.WxRecvTextMsg;
//import com.weixin.platform.wxtools.vo.send.WxSendMsg;
//import com.weixin.platform.wxtools.vo.send.WxSendTextMsg;
//
///**
// * Created by ruanzf on 2015/5/8.
// */
//public class ChooseType extends BaseOperate {
//
//    private int type = 0;
//
//    @Override
//    public WxSendMsg execute(WxRecvMsg recvMsg, WxSendMsg sendMsg) {
//        if(recvMsg instanceof WxRecvTextMsg) {
//            WxRecvTextMsg textMsg = (WxRecvTextMsg)recvMsg;
//            String content = textMsg.getContent();
//            try {
//                type = Integer.parseInt(content);
//                if(type >0 && type <= ProductEnum.size()) {
//                    this.execEnum = ExecEnum.MORE_SELL;
//                    return  new WxSendTextMsg(sendMsg, Tools.SEND_PIC);
//                }else {
//                    refresh = false;
//                    return new WxSendTextMsg(sendMsg, Tools.SELL_TYPE_ERROR2);
//                }
//            }catch (Exception e) {
//                refresh = false;
//                return new WxSendTextMsg(sendMsg, Tools.SELL_TYPE_ERROR);
//            }
//        }else {
//            refresh = false;
//            return new WxSendTextMsg(sendMsg, Tools.SELL_TYPE_ERROR);
//        }
//    }
//
//    @Override
//    public void refresh(WxRecvMsg msg) {
//        Process process = stateService.getProcess(msg.getFromUser());
//        ProductEnum productEnum = ProductEnum.getById(type);
//        process.setSellType(productEnum.getCode());
//        process.setState(execEnum.getName());
//        this.stateService.refresh(process);
//    }
//}
