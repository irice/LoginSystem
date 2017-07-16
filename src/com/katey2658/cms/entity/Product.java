package com.katey2658.cms.entity;

/**
 * Created by 11456 on 2016/11/27.
 */
public class Product {

    /**
     * 无参构造
     */
    public Product(){

    }

    /**
     * 有参构造
     * @param proId 产品编号
     * @param proName 产品名
     * @param proPrice 产品价格
     * @param leftNum 产品数量
     * @param sale 是否促销
     * @param proSupplier 供货商
     */
    public Product(String proId,String proName,int proPrice,int leftNum,boolean sale,String proSupplier){
        this.proId=proId;
        this.proName=proName;
        this.proPrice=proPrice;
        this.leftNum=leftNum;
        this.sale=sale;
        this.proSupplier=proSupplier;
    }

    /**
     * 产品编号
     */
    private String proId;

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    /**
     * 产品名字
     */
    private String proName;

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }


    /**
     * 产品价格
     */
    private int proPrice;

    public int getProPrice() {
        return proPrice;
    }

    public void setProPrice(int proPrice) {
        this.proPrice = proPrice;
    }

    /**
     * 数量
     */
    private int leftNum;

    public int getLeftNum() {
        return leftNum;
    }

    public void setLeftNum(int leftNum) {
        this.leftNum = leftNum;
    }

    /**
     * 是否促销
     */
    private boolean sale;

    public boolean isSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }

    /**
     * 供货商
     */
    private String proSupplier;

    public String getProSupplier() {
        return proSupplier;
    }

    public void setProSupplier(String proSupplier) {
        this.proSupplier = proSupplier;
    }
}
