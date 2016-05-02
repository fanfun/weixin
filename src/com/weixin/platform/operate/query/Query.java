package com.weixin.platform.operate.query;

import com.weixin.platform.domain.Owner;
import com.weixin.platform.domain.state.ProductEnum;
import com.weixin.platform.domain.state.QueryEnum;
import com.weixin.platform.operate.BaseOperate;
import com.weixin.platform.wxtools.Tools;
import com.weixin.platform.wxtools.vo.recv.WxRecvMsg;
import com.weixin.platform.wxtools.vo.recv.WxRecvTextMsg;
import com.weixin.platform.wxtools.vo.send.WxSendMsg;
import com.weixin.platform.wxtools.vo.send.WxSendNewsMsg;
import com.weixin.platform.wxtools.vo.send.WxSendTextMsg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruanzf on 2015/4/28.
 */
public class Query extends BaseOperate {

    @Override
    public WxSendMsg execute(WxRecvMsg recvMsg, WxSendMsg sendMsg) {
        WxRecvTextMsg textMsg = (WxRecvTextMsg)recvMsg;
        String content = textMsg.getContent();
        QueryEnum queryEnum = QueryEnum.getByName(content);
        switch (queryEnum) {
            case ID:
                return queryId(content, sendMsg);
            case QUERY:
                stateService.operateCount(QueryEnum.QUERY.getCode());
                return new WxSendNewsMsg(sendMsg)
                        .addItem(Tools.QUERY_USED, Tools.QUERY_USED, Tools.PIC_MAIN, Tools.queryUrl(ProductEnum.SELL))
                        .addItem(Tools.QUERY_ASKBUY, Tools.QUERY_ASKBUY, Tools.PIC_ASK_BUY, Tools.queryUrl(ProductEnum.ASK_BUY))
                        .addItem(Tools.QUERY_MINE, Tools.QUERY_MINE_DETAIL, Tools.PIC_MINE, Tools.mineUrl(recvMsg.getFromUser()));

            case MINE:
                stateService.operateCount(QueryEnum.MINE.getCode());
                return new WxSendNewsMsg(sendMsg)
                        .addItem(Tools.QUERY_MINE, Tools.QUERY_MINE_DETAIL, Tools.PIC_MINE, Tools.mineUrl(recvMsg.getFromUser()));
            default:
                return new WxSendTextMsg(sendMsg, "shae没看懂唉，亲的查询格式对了么");
        }
    }

    @Override
    public void refresh(WxRecvMsg msg) {
        //todo:保存到数据库，并清redis
    }

    private WxSendMsg queryId(String content, WxSendMsg sendMsg) {
        int start = content.indexOf(Tools.division) + 1;
        String[] idsStr = content.substring(start).split(Tools.partition);
        List<Integer> ids = new ArrayList<Integer>();
        for(String idStr : idsStr) {
            try {
                int id = Integer.parseInt(idStr);
                ids.add(id);
            }catch (Exception e) {
                continue;
            }
        }
        if(ids.size() == 0) {
            return new WxSendTextMsg(sendMsg, Tools.NO_NO);
        }else {
            List<Owner> owners = productService.getByProductId(ids);
            if(owners.isEmpty()) {
                return new WxSendTextMsg(sendMsg, Tools.NO_GOODS);
            }
            StringBuilder sb = new StringBuilder("查询编号:");
            for (Owner owner : owners) {
                stateService.operateCount(QueryEnum.ID.getCode());
                sb.append(owner.getId())
                        .append(" 的联系方式是")
                        .append(owner.getPhone())
                        .append(",")
                        .append(owner.getNick())
                        .append("。");
            }
            return new WxSendTextMsg(sendMsg, sb.toString());
        }
    }
}
