package com.weixin.platform.operate.common;

import com.weixin.platform.domain.Student;
import com.weixin.platform.operate.BaseOperate;
import com.weixin.platform.wxtools.Tools;
import com.weixin.platform.wxtools.vo.recv.WxRecvMsg;
import com.weixin.platform.wxtools.vo.recv.WxRecvTextMsg;
import com.weixin.platform.wxtools.vo.send.WxSendMsg;
import com.weixin.platform.wxtools.vo.send.WxSendTextMsg;

/**
 * Created by Administrator on 2015/5/3.
 */
public class Reinfo extends BaseOperate{

    @Override
    public WxSendMsg execute(WxRecvMsg recvMsg, WxSendMsg sendMsg) {
        if(recvMsg instanceof WxRecvTextMsg) {
            WxRecvTextMsg textMsg = (WxRecvTextMsg)recvMsg;
            String content = textMsg.getContent();
            int shap = countShap(content);
            if(shap == 2) {
                int index = content.indexOf(Tools.division) + 1;
                String info = content.substring(index);
                Student student = initStudent(info, textMsg.getFromUser());
                student = studentService.updateOrSave(student);
                return new WxSendTextMsg(sendMsg, stuInfo(student));
            }else {
                return new WxSendTextMsg(sendMsg, Tools.REINFO_ERROR);

            }
        }else {
            refresh = false;
            return new WxSendTextMsg(sendMsg, Tools.REINFO_ERROR);
        }
    }

    @Override
    public void refresh(WxRecvMsg msg) {
    }

    public Student initStudent(String content, String name) {
        int start = content.indexOf(Tools.division);
        Student student = new Student();
        String nick = content.substring(0, start);
        String phone = content.substring(start + 1);
        student.setName(name);
        student.setNick(nick);
        student.setPhone(phone);
        student.setTime(Tools.date2String(System.currentTimeMillis()));
        return student;
    }

    public static void main(String[] args) {
        String a = "fwe#jio#ji#o";
        System.out.println(new Reinfo().countShap(a));
    }

    private int countShap(String content) {
        int index = content.indexOf(Tools.division);
        System.out.println(index);
        if(index < 0) {
            return 0;
        }
        index = content.indexOf(Tools.division, index + 1);
        System.out.println(index);
        if(index < 0) {
            return 1;
        }
        return 2;
    }

    private String stuInfo(Student student) {
        return "亲的联系方式修改为：" + student.getPhone() + ", 校区改为:" + student.getNick() + "[玫瑰]";
    }
}
