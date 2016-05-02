package com.weixin.platform.domain.state;

/**
 * Created by ruanzf on 2015/4/23.
 */
public enum ExecEnum {
    SHUNT(1, "SHUNT"),
    LUCENCY(20, "LUCENCY"),
    CHAT(21, "CHAT"),
    SELL(30, "SELL"),
    SELL_TYPE(31, "CHOOSE_TYPE_SELL"),
//    PICTURE_SELL(32, "PICTURE_SELL"),
    MORE_SELL(33, "MORE_SELL"),
    QUERY(40, "QUERY"),
    ASK_BUY(60, "ASK_BUY"),
    SELL_MATE(70, "SELL_MATE"),
    CLASSIFY(80, "CLASSIFY"),
    INFORMATION(100, "INFORMATION"),
    SOLD_OUT(110, "SOLD_OUT"),
    SUGGEST(120, "SUGGEST"),
    MANUAL(130, "MANUAL"),
    REINFO(140, "REINFO"),
    RESET(150, "RESET"),
    DONE(999, "DONE"),
    ;

    private int number;
    private String name;

    ExecEnum(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ExecEnum getExe(String name) {
        for (ExecEnum execEnum : ExecEnum.values()) {
            if (execEnum.getName().equals(name)) {
                return execEnum;
            }
        }
        return ExecEnum.SHUNT;
    }
}
