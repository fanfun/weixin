package com.weixin.platform.operate;

import com.weixin.platform.domain.state.ExecEnum;
import com.weixin.platform.operate.askbuy.AskBuy;
import com.weixin.platform.operate.common.*;
import com.weixin.platform.operate.lucency.Lucency;
import com.weixin.platform.operate.sell.SellRecord;
import com.weixin.platform.operate.query.Query;
import com.weixin.platform.wxtools.vo.recv.WxRecvMsg;
import com.weixin.platform.wxtools.vo.recv.WxRecvTextMsg;
import com.weixin.platform.wxtools.vo.send.WxSendMsg;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ruanzf on 2015/4/23.
 */
public class Shunt extends BaseOperate {

    private Map<ExecEnum, Operate> operateMap;

    public Shunt() {
        operateMap = new HashMap<ExecEnum, Operate>();
        operateMap.put(ExecEnum.SELL, new SellRecord());
        operateMap.put(ExecEnum.QUERY, new Query());
        operateMap.put(ExecEnum.LUCENCY, new Lucency());
        operateMap.put(ExecEnum.ASK_BUY, new AskBuy());
        operateMap.put(ExecEnum.SOLD_OUT, new SoldOut());
        operateMap.put(ExecEnum.SUGGEST, new Suggest());
        operateMap.put(ExecEnum.MANUAL, new Manual());
        operateMap.put(ExecEnum.REINFO, new Reinfo());
    }

    @Override
    public WxSendMsg execute(WxRecvMsg recvMsg, WxSendMsg sendMsg) {
        return null;
    }

    @Override
    public WxSendMsg Operate(WxRecvMsg msg) {
        ExecEnum execEnum = parseOperate(msg);
        Operate operate = operateMap.get(execEnum);
        return operate.Operate(msg);
    }

    public ExecEnum parseOperate(WxRecvMsg msg) {
        if (msg instanceof WxRecvTextMsg) {
            WxRecvTextMsg textMsg = (WxRecvTextMsg) msg;
            String content = wrapContent(textMsg.getContent());
            return stateService.shunt(content);
        }
        return ExecEnum.LUCENCY;
    }

    public String wrapContent(String content) {
        if(content.startsWith("求购")) {
            String msg = content.replace("求购", "求");
            return msg;
        }
        return content;
    }
}
