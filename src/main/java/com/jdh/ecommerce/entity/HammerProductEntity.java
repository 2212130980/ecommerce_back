package com.jdh.ecommerce.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("hammer_product")
public class HammerProductEntity implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String productName;
    private Integer categoryLevel1;
    private Integer categoryLevel2;
    private Integer isDelete;
    private String productImg;
    private String productRepertory;
    private Integer price;
    private String title;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("HammerProductEntity{");
        sb.append("id=").append(id);
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", categoryLevel1=").append(categoryLevel1);
        sb.append(", categoryLevel2=").append(categoryLevel2);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", productImg='").append(productImg).append('\'');
        sb.append(", productRepertory='").append(productRepertory).append('\'');
        sb.append(", price=").append(price);
        sb.append(", title='").append(title).append('\'');
        sb.append('}');
        sb.append("\n");
        return sb.toString();
    }

    public HammerProductEntity(Integer id,String productName, Integer categoryLevel1, Integer categoryLevel2, Integer isDelete, String productImg, String productRepertory, Integer price, String title) {
        this.id = id;
        this.productName = productName;
        this.categoryLevel1 = categoryLevel1;
        this.categoryLevel2 = categoryLevel2;
        this.isDelete = isDelete;
        this.productImg = productImg;
        this.productRepertory = productRepertory;
        this.price = price;
        this.title = title;
    }

    public HammerProductEntity() {
    }
}
