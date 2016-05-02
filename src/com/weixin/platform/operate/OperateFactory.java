package com.weixin.platform.operate;

import com.weixin.platform.domain.state.ExecEnum;
import com.weixin.platform.domain.state.ShuntEnum;
import com.weixin.platform.operate.common.*;
import com.weixin.platform.operate.sell.Category;
import com.weixin.platform.operate.sell.MoreSell;
import com.weixin.platform.service.StateService;
import com.weixin.platform.wxtools.vo.recv.WxRecvMsg;
import com.weixin.platform.wxtools.vo.recv.WxRecvTextMsg;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ruanzf on 2015/4/23.
 */
public class OperateFactory {

    private StateService stateService;
    private Map<ExecEnum, Operate> operateMap;

    public OperateFactory() {
        this.stateService = new StateService();
        init();
    }

    public void init() {
        operateMap = new HashMap<ExecEnum, Operate>();
        operateMap.put(ExecEnum.SHUNT, new Shunt());
        operateMap.put(ExecEnum.SELL_TYPE, new Category());
        operateMap.put(ExecEnum.MORE_SELL, new MoreSell());
        operateMap.put(ExecEnum.INFORMATION, new Information());
    }

    public Operate build(WxRecvMsg msg) {
        if(msg instanceof WxRecvTextMsg) {
            String content = ((WxRecvTextMsg) msg).getContent();
            if(content.equals(ShuntEnum.RESET.getName())) {
                return new Reset();
            }
        }
        ExecEnum main = stateService.getState(msg.getFromUser());
        return operateMap.get(main);
    }

}
