package com.weixin.platform.operate.lucency;

import com.weixin.platform.domain.state.ExecEnum;
import com.weixin.platform.operate.BaseOperate;
import com.weixin.platform.wxtools.Tools;
import com.weixin.platform.wxtools.vo.recv.WxRecvMsg;
import com.weixin.platform.wxtools.vo.send.WxSendMsg;
import com.weixin.platform.wxtools.vo.send.WxSendNewsMsg;
import com.weixin.platform.wxtools.vo.send.WxSendTextMsg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/4/26.
 */
public class Lucency extends BaseOperate{

    private static List<String> talkMap;

    public Lucency() {
        if(talkMap == null) {
            this.talkMap = new ArrayList<String>();
            talkMap.add("请按照手册规则呢，毕竟我是傻。如果中途输错了可以回复“清空”重新输入。如果是要查看卖家信息的话，格式是：编号#具体的数字。例如：编号#123，“编号#”一定要在前面哦[玫瑰]");
            talkMap.add("不好意思我是机器人额，我不能很好的理解你的意思，请参照使用手册吧[愉快]");
            talkMap.add("[玫瑰][玫瑰]");
        }
    }

    @Override
    public WxSendMsg execute(WxRecvMsg recvMsg, WxSendMsg sendMsg) {
        int index = stateService.guide(recvMsg.getFromUser());
        if(index < 0) {
            stateService.manual(recvMsg.getFromUser(), "0");
            return new WxSendNewsMsg(sendMsg).addItem(Tools.GUIDE, Tools.INTRODUCE, Tools.PIC_MANUAL, Tools.GUIDE_URL);
        }else if(index < talkMap.size()){
            Integer next = index + 1;
            stateService.manual(recvMsg.getFromUser(), next.toString());
            return new WxSendTextMsg(sendMsg, getMessage(index));
        }else {
            this.execEnum = ExecEnum.DONE;
            return new WxSendTextMsg(sendMsg, getMessage(talkMap.size() - 1));
        }
    }
    
    private String getMessage(int index) {
        System.out.println(index);
        return talkMap.get(index);
    }

}
