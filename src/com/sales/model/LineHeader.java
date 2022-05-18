
package com.sales.model;


public class LineHeader {
      private String item;
    private double price;
    private int count;
    private InvoiceHeader invoice;

    public LineHeader() {
    }

    public LineHeader(String item, double price, int count, InvoiceHeader invoice) {
        this.item = item;
        this.price = price;
        this.count = count;
        this.invoice = invoice;
    }

    public double getLineTotal() {
        return price * count;
    }
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Line{" + "number=" + invoice.getNumber() + ", item=" + item + ", price=" + price + ", count=" + count + '}';
    }

    public InvoiceHeader getInvoice() {
        return invoice;
    }
     
    
    
    
}
