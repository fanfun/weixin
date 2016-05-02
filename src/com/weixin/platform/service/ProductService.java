package com.weixin.platform.service;

import com.weixin.platform.dao.postgresql.ProductDao;
import com.weixin.platform.dao.postgresql.StudentDao;
import com.weixin.platform.domain.Owner;
import com.weixin.platform.domain.Product;
import com.weixin.platform.domain.Student;
import com.weixin.platform.domain.state.ProductEnum;
import com.weixin.platform.domain.state.ResponseEnum;
import com.weixin.platform.wxtools.DateUtil;
import com.weixin.platform.wxtools.PictureUtil;
import com.weixin.platform.wxtools.Tools;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2015/5/2.
 */
public class ProductService extends BaseService {

    private ProductDao productDao;
    private StudentDao studentDao;

    public ProductService() {
        this.productDao = new ProductDao();
        this.studentDao = new StudentDao();
    }

    public Integer save(Product product, String items) {
        Student student = studentDao.getByName(product.getOwner());
        product.setNick(student.getNick());
        product.setPhone(student.getPhone());
        zipPicture(product);
        Integer id = productDao.save(product);
        productDao.savaProductItem(id, items);
        return id;
    }

    public ResponseEnum soldOut(int id, String name) {
        return productDao.soldOut(id, name);
    }

    public List<Owner> getByProductId(List<Integer> ids) {
//        return productDao.getByProductId(ids);
        return studentDao.getByProductId(ids);
    }

    public void zipPicture(Product product) {
        String photo = product.getPhoto();
        if(photo.equals("")) {
            return;
        }
        String time = DateUtil.formatDate(new Date(product.getCreatetime()), "yyyy_MM_dd");
        String[] photoArray = photo.split(Tools.pic);
        String thumbPic = "";
        for(String pic : photoArray) {
            if(pic.equals("")) {
                continue;
            }
            String thumbnail;
            String suffix = UUID.randomUUID().toString() + ".jpg";
            String file = time + PictureUtil.sp;
            String download = PictureUtil.DownPictureToInternet(file, suffix, pic);
            if(pic.equals(download)) {
                thumbnail = download;
            }else {
                thumbnail = PictureUtil.createThumbnail(file, suffix, download);
            }

            if(thumbPic == "") {
                thumbPic = thumbnail;
            }else {
                thumbPic = thumbPic + Tools.pic + thumbnail;
            }
        }
        product.setPhoto(thumbPic);
    }
}
