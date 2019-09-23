package com.ieeecsvit.hfdelivery.model;

public class OrderClass {
    String orderID, from , toLongitude, toLatitutde , fromLongitude, fromLatitude, to;
    public OrderClass(String orderID, String from,String fromLongitude, String fromLatitutde, String to, String toLongitude, String toLatitutde ){
        this.orderID = orderID;
        this.from = from;
        this.to = to ;
        this.toLatitutde = toLatitutde;
        this.toLongitude = toLongitude;
        this.fromLatitude = fromLatitutde;
        this.fromLongitude = fromLongitude;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getFrom() {
        return from;
    }

    public String getToLatitutde() {
        return toLatitutde;
    }

    public String getToLongitude() {
        return toLongitude;
    }

    public String getTo() {
        return to;
    }

    public String getFromLatitude() {
        return fromLatitude;
    }

    public String getFromLongitude() {
        return fromLongitude;
    }
}
