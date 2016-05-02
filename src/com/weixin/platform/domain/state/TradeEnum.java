package com.weixin.platform.domain.state;


/**
 * Created by ruanzf on 2015/4/22.
 */
public enum TradeEnum {

    FCLOTHES(1, "FCLOTHES", "女生鞋服"),
    MCLOTHES(2, "MCLOTHES", "男生鞋服"),
    ELECTRIC(3, "ELECTRIC", "数码电器"),
    COSMETIC(4, "COSMETIC", "护肤彩妆"),
    BOOK(5, "BOOK", "书刊学品"),
    GOODS(6, "GOODS", "生活用品"),
    TOOLS(7, "TOOLS", "代步运动"),
    TICKET(8, "TICKET", "票券"),
    BOX(9, "BOX", "箱包"),
    FOOD(10, "FOOD", "食品"),
    OTHER(11, "OTHER", "其它"),
    ;

    private int id;
    private String code;
    private String name;

    TradeEnum(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getIdString() {
        return id + "";
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int size() {
        return 11;
    }

    public static TradeEnum getById(int id) {
        for(TradeEnum typeEnum : TradeEnum.values()) {
            if(typeEnum.getId() == id) {
                return typeEnum;
            }
        }
        return OTHER;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static String getCodeById(int id) {
        return getById(id).getCode();
    }

    public static int getIdByCode(String code) {
        for(TradeEnum typeEnum : TradeEnum.values()) {
            if(typeEnum.getCode().equals(code)) {
                return typeEnum.getId();
            }
        }
        return 0;
    }
}
