package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepository {

    Map<String,Order> ordersDB = new HashMap<>();

    Map<String,DeliveryPartner> deliveryPartnersDb = new HashMap<>();

    Map<String, ArrayList<String>> partnerToOrderDb = new HashMap<>();

    Map<String,String> orderToPartnerDb = new HashMap<>();

    public void addOrder(Order order){
        ordersDB.put(order.getId(),order);
    }

    public void addPartner(String partnerId){

        deliveryPartnersDb.put(partnerId,new DeliveryPartner(partnerId));
    };

    public void addOrderPartnerPair(String orderId,String partnerId){
        orderToPartnerDb.put(orderId,partnerId);
        if(partnerToOrderDb.containsKey(partnerId)==false){
            partnerToOrderDb.put(partnerId,new ArrayList<>());
        }
        partnerToOrderDb.get(partnerId).add(orderId);
    }

    public Order getOrderById(String orderId){
        return ordersDB.get(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId){
        return deliveryPartnersDb.get(partnerId);
    }

    public int getOrderCountByPartnerId(String partnerId){

        return partnerToOrderDb.get(partnerId).size();
    }

    public List<String> getOrdersByPartnerId(String partnerId){

        return partnerToOrderDb.get(partnerId);
    }

    public List<String> getAllOrders() {
        List<String> list = new ArrayList<>();
        for(String order:ordersDB.keySet()){
            list.add(order);
        }
        return list;
    }

    public Integer getCountOfUnassignedOrders() {
        return ordersDB.size()-orderToPartnerDb.size();
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        List<String> orderList = partnerToOrderDb.get(partnerId);
        int n=orderList.size();
        Order order = ordersDB.get(orderList.get(n-1));
        return String.valueOf(order.getDeliveryTime());
    }
}
