package sample;
import java.util.Comparator;

public class PrComparator implements Comparator<Products> {
    @Override
    public int compare(Products p1, Products p2){
        return (int)p1.getPrice()-(int)p2.getPrice();
    }
}
