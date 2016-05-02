package com.weixin.platform.operate.common;

import com.weixin.platform.domain.state.ResponseEnum;
import com.weixin.platform.operate.BaseOperate;
import com.weixin.platform.service.SuggestService;
import com.weixin.platform.wxtools.Tools;
import com.weixin.platform.wxtools.vo.recv.WxRecvMsg;
import com.weixin.platform.wxtools.vo.recv.WxRecvTextMsg;
import com.weixin.platform.wxtools.vo.send.WxSendMsg;
import com.weixin.platform.wxtools.vo.send.WxSendTextMsg;

/**
 * Created by ruanzf on 2015/5/8.
 */
public class Suggest extends BaseOperate {

    private static SuggestService suggestService;

    public Suggest() {
        if(suggestService == null) {
            suggestService = new SuggestService();
        }
    }

    @Override
    public WxSendMsg execute(WxRecvMsg recvMsg, WxSendMsg sendMsg) {
        if(recvMsg instanceof WxRecvTextMsg) {
            WxRecvTextMsg textMsg = (WxRecvTextMsg)recvMsg;
            String content = textMsg.getContent();
            int start = content.indexOf(Tools.division) + 1;
            String detail = content.substring(start);
            ResponseEnum responseEnum = suggestService.save(detail, textMsg.getFromUser());
            return new WxSendTextMsg(sendMsg, responseEnum.getMsg());
        }else {
            refresh = false;
            return new WxSendTextMsg(sendMsg, Tools.SUGGEST_ERROR);
        }
    }

    @Override
    public void refresh(WxRecvMsg msg) {
        super.refresh(msg);
    }
}
