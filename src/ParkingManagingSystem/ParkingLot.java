package ParkingManagingSystem;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 11-11-12
 * Time: 下午3:15
 * To change this template use File | Settings | File Templates.
 */
public class ParkingLot {
    public int getParkCapacity() {
        return parkCapacity;
    }

    private int parkCapacity;
    private HashMap<ParkingReceipt, Car> parkingCarList;

    public void clear()
    {
        parkingCarList.clear();
    }
    public  ParkingLot(int capacity)
    {
        parkCapacity = capacity;
        parkingCarList = new HashMap<ParkingReceipt, Car>();
    }
    public int availableParkingSpace()
    {
        return (parkCapacity - parkingCarList.size());  //To change body of created methods use File | Settings | File Templates.
    }

    public ParkingReceipt park(Car aCar)
    {
        if(parkingCarList.size() < parkCapacity)
        {
            ParkingReceipt receipt = new ParkingReceipt();
            parkingCarList.put(receipt, aCar);
            return receipt;
        }
        else
        {
            return null ;
        }
    }


    public Car fetchCar(ParkingReceipt pReceipt) {
        if(parkingCarList.containsKey(pReceipt))
        {
            return  parkingCarList.remove(pReceipt);
        }
        else
        {
            return  null;
        }

    }
    public float vacancyRate()
    {
        int availableSpace = availableParkingSpace();
        float rate = (float)availableSpace/parkCapacity;
        return rate;
    }

    public void Print(int tabs) {
        for(int i=0; i<tabs; i++) {
            System.out.print('\t');
        }
        System.out.print("车位数：");
        System.out.println(parkCapacity);
        for(int i=0; i<tabs; i++) {
            System.out.print('\t');
        }
        System.out.print("空位数：");
        System.out.println(availableParkingSpace());
    }
}
