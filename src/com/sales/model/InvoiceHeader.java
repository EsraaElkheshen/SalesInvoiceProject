
package com.sales.model;

import java.util.ArrayList;

public class InvoiceHeader {
    private int number;
    private String date;
    private String customerName;
    private ArrayList<LineHeader> lines;
    
    public InvoiceHeader() {
    }

    public InvoiceHeader(int number, String date, String customerName) {
        this.number = number;
        this.date = date;
        this.customerName = customerName;
    }

    public double getInvoiceTotal() {
        double total = 0.0;
        for (LineHeader line : getLines()) {
            total += line.getLineTotal();
        }
        return total;
    }
    
    public ArrayList<LineHeader> getLines() {
        if (lines == null) {
            lines = new ArrayList<>();
        }
        return lines;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customer) {
        this.customerName = customer;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int num) {
        this.number = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Invoice{" + "number=" + number + ", date=" + date + ", customer=" + customerName + '}';
    }
    

    
 
}
