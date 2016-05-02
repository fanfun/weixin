package com.weixin.platform.wxtools;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Administrator on 2015/5/19.
 */
public class PictureUtil {

    public static String sp = "/";
    private static String downloadBasePath = "/cache/picture/";
    private static String thumbnailBasePath = "/home/job/bicycle/apache-tomcat-6.0.43/webapps/img/";
    private static String urlPath = "http://busyer.com/img/";
//    private static String downloadBasePath = "F://a/";
//    private static String thumbnailBasePath = "F://b/";

    public static String DownPictureToInternet(String filePath, String fileName, String strUr1){
        try {
            String basePath = downloadBasePath + filePath;
            File fileDir = new File(basePath);
            if(!fileDir.exists()) {
                fileDir.mkdirs();
            }
            URL url = new URL(strUr1);
            InputStream fStream = url.openConnection().getInputStream();
            int b = 0;
            String realPath = basePath + fileName;
            FileOutputStream fos = new FileOutputStream(new File(realPath));
            while ((b=fStream.read())!=-1) {
                fos.write(b);
            }
            fStream.close();
            fos.close();
            return realPath;
        } catch (Exception e) {
            e.printStackTrace();
            return strUr1;
        }
    }

    /**
     * 创建图片缩略图(等比缩放)
     *
     * @param origin
     *            源图片文件完整路径
     */
    public static String createThumbnail(String filePath, String fileName, String origin) {
        float width = 512.0f;
        double ratio = 1.0;
        String basePath = thumbnailBasePath + filePath;
        File fileDir = new File(basePath);
        if(!fileDir.exists()) {
            fileDir.mkdirs();
        }
        try {
            File srcfile = new File(origin);
            if (!srcfile.exists()) {
                System.out.println("文件不存在");
                return "";
            }
            BufferedImage image = ImageIO.read(srcfile);

            // 获得缩放的比例
            // 判断如果高、宽都不大于设定值，则不处理
            if (image.getWidth() > width) {
                ratio = width / image.getWidth();
            }
            // 计算新的图面宽度和高度
            int newWidth = (int) (image.getWidth() * ratio);
            int newHeight = (int) (image.getHeight() * ratio);

            BufferedImage bfImage = new BufferedImage(newWidth, newHeight,
                    BufferedImage.TYPE_INT_RGB);
            bfImage.getGraphics().drawImage(
                    image.getScaledInstance(newWidth, newHeight,
                            Image.SCALE_SMOOTH), 0, 0, null);

            String thumbnail = basePath + fileName;
            FileOutputStream os = new FileOutputStream(thumbnail);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
            encoder.encode(bfImage);
            os.close();
            String realPath = urlPath + filePath + fileName;
            return realPath;
        } catch (Exception e) {
            System.out.println("创建缩略图发生异常" + e.getMessage());
            return "";
        }
    }

    public static void main(String[] args) {
//        createThumbnail("F:\\pic\\408_0.jpg", "F:\\408.jpg");
    }

}
