package com.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by ruanzf on 2015/5/19.
 */
public class PictureDownload {

    public static void main(String[] args) {
        String pic = "http://mmbiz.qpic.cn/mmbiz/n5btlWwIurmeB6ak9CwNU6laWNDMl6iczpN837fWfAFKicyFtUy7LCictAqoq60nUObDdcnd9gGIn9l8aribicF0R8g/0";
        String filePath = "E://aa/abc.jpg";
        new PictureDownload().DownPictureToInternet(filePath, pic);//"http://www.sc115.com/wenku/uploads/allimg/121216/2044094V0-0.jpg");
    }

    public static void DownPictureToInternet(String filePath,String strUr1){
        try {
            URL url = new URL(strUr1);
            InputStream fStream = url.openConnection().getInputStream();
            int b = 0;
            FileOutputStream fos = new FileOutputStream(new File(filePath));
            while ((b=fStream.read())!=-1) {
                fos.write(b);
            }
            fStream.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
