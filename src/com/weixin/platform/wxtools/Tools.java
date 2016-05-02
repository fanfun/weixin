package com.weixin.platform.wxtools;

import com.weixin.platform.dao.redis.RedisOperator;
import com.weixin.platform.domain.state.ProductEnum;
import com.weixin.platform.domain.state.TradeEnum;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/4/19.
 */
public class Tools {

    /**
     * web.xml
     * db.config
     * token标识
     */

    public static final String PICTURE = "PICTURE";
    public static String PIC_MAIN = "";
    public static String PIC_ASK_BUY = "";
    public static String PIC_MINE = "";
    public static String PIC_MANUAL= "";

    static {
        RedisOperator jedis = new RedisOperator();
        PIC_MAIN = jedis.hget(PICTURE, "PIC_MAIN");
        PIC_ASK_BUY = jedis.hget(PICTURE, "PIC_ASK_BUY");
        PIC_MINE = jedis.hget(PICTURE, "PIC_MINE");
        PIC_MANUAL = jedis.hget(PICTURE, "PIC_MANUAL");
    }

    public static final int MAX_PIC = 2;
    public static final int EXPIRE_TIME = 10 * 60;

    public static final String dateFormat = "yyyy-MM-dd HH:mm";
    public static final SimpleDateFormat format = new SimpleDateFormat(dateFormat);

    public static final String empty = "";
    public static final String division = "#";
    public static final String partition = "/";
    public static final String pic = "@#@#";
    public static final String NONE = "#@$#@%&$#@#";


    public static final String STATE = "state";
    public static final String SELL_TYPE = "sell";
    public static final String ITEM = "item";
    public static final String OPERATE = "operate";
    public static final String MANUAL = "manual";

    public static final String OK = "好了";
    public static final String WITHOUT = "无";
    public static final String INFORMATION = "好的，请留下你的校区和联系方式吧，例如“本部#QQ1234560,静静”[愉快]";
    public static final String PICTURE_DONE = "如果照片发好了，请回复我“好了”，没照片的话回复“无”[得意]";
    public static final String PICTURE_NO = "也许是因为网络慢，还没收到图片呢，你可以稍等或再发一次，再回复“好了”[调皮]";
    public static final String INFORMATION_ERROR = "发校区和联系方式呢，中间加个#[调皮]";
    public static final String SELL_TYPE_ERROR = "亲，直接回分类的数字就好啦，如果属于多个分类，请用“/”分隔，例如1/2/3，最多属于3个分类[玫瑰]";
    public static final String SELL_TYPE_ERROR2 = "超出分类范围啦~~";

    public static final String GUIDE = "使用手册";
    public static final String GUIDE_URL = "http://www.busyer.com/shae/manual";
    public static final String SUBSCRIBE = "感谢你的关注，我是Shae，这里是我的使用手册^_^";
    public static final String INTRODUCE = "亲，Shae没能理解您的意思，这里是我的使用手册^_^";

    public static final String QUERY_USED = "这里是我们的二手市场";
    public static final String QUERY_ASKBUY= "需求的商品";
    public static final String QUERY_MINE = "我的发布";
    public static final String QUERY_MINE_DETAIL = "我发布的东西";
    public static final String QUERY_URL = "http://www.busyer.com/shae/query?type=";
    public static final String MINE_URL = "http://www.busyer.com/shae/mine?mine=";
    public static final String INFO_URL = "http://www.busyer.com/shae/info?name=";

    public static final String NO_NO = "输入的编号不正确啦，如果是查多个的话，请用/分割。例如：编号#12/34";
    public static final String NO_GOODS = "亲要找的商品不存在或者已经下架啦[玫瑰]";
    public static final String SEND_PIC = "好的，请把照片发给我吧，请一定要稍等照片发完成后回复“好了”，否则可能会看不到照片。（如果没有照片回复“无”）[愉快]";

    public static final String SOLDOUT_ERROR = "要下架物品的数字编号呢，例如:下架#103";
    public static final String SUGGEST_ERROR = "建议失败，我也不知道为啥[凋谢]";
    public static final String MANUAL_DETAIL = "这里是我的使用手册^_^";

    public static final String REINFO_ERROR = "重置联系方式的格式是，设置#本部#QQ123456，静静，中间要有#[调皮]";
    public static final String RESET_MSG = "已经把刚才发送内容都清空啦，可以重新开始[愉快]";

    public static String recvPicture(int count) {
        return "收到第" + count + "张照片，可以接着发照片或者回复“好了”";
    }

    public static String thankyou(String nick, String contact, String name) {
        return "谢谢你的配合[愉快], 你的联系方式：" + contact + "，校区为：" + nick + "，我们记牢啦[得意]如果联系方式不对，<a href=\"" + infoUrl(name) + "\">点击这里</a>修改[玫瑰]";
    }

    public static String filterContent(String content) {
        int start = content.indexOf(Tools.division);
        if(start <= 0) {
            return "";
        }
        return content.substring(start + 1);
    }

    public static String SellType() {
        StringBuffer sb = new StringBuffer("请选择出售的类型吧[愉快]\n");
        for(TradeEnum typeEnum : TradeEnum.values()) {
            sb.append(typeEnum.getId())
                    .append(":")
                    .append(typeEnum.getName())
                    .append("\r");
        }
        sb.append("如果属于多个分类，请用“/”分隔，例如1/2/3，最多属于3个分类[得意]");
        return sb.toString();
    }

    public static String queryUrl(ProductEnum productEnum) {
        String base64 = Base64Util.encode(productEnum.getIdString().getBytes());
        return QUERY_URL + base64;
    }

    public static String mineUrl(String name) {
        String base64 = Base64Util.encode(name.getBytes());
        return MINE_URL + base64;
    }

    public static String infoUrl(String name) {
        return INFO_URL + name;
    }

    public static String date2String(long time) {
        return format.format(new Date(time));
    }

    public static boolean validate(String content) {
        int start = content.indexOf(Tools.division);
        if(start <= 0) {
            return false;
        }
        if(content.length() <= start + 1) {
            return false;
        }
        return true;

    }
}
