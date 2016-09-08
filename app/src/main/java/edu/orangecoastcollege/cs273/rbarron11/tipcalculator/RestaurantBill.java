package edu.orangecoastcollege.cs273.rbarron11.tipcalculator;

/**
 * Created by rbarron11 on 9/8/2016.
 */
public class RestaurantBill {

    private double mAmount;
    private double mTipPercent;
    private double mTipAmount;
    private double mTotalAmount;

    public double getAmount() {
        return mAmount;
    }

    public double getTipPercent() {
        return mTipPercent;
    }

    public double getTipAmount() {
        return mTipAmount;
    }

    public void setAmount(double mAmount) {
        this.mAmount = mAmount;
        recalculateAmount();
    }

    public void setTipPercent(double mTipPercent) {
        this.mTipPercent = mTipPercent;
        recalculateAmount();
    }

    public double getTotalAmount() {
        return mTotalAmount;
    }

    public RestaurantBill(double mAmount, double mTipPercent) {
        this.mAmount = mAmount;
        this.mTipPercent = mTipPercent;
        recalculateAmount();
    }

    public RestaurantBill() {
        this.mAmount = 0;
        this.mTipPercent = 0;
        this.mTipAmount = 0;
        this.mTotalAmount = 0;
    }

    private void recalculateAmount()
    {
        mTipAmount = mAmount * mTipPercent;
        mTotalAmount = mAmount + mTipAmount;
    }
}
