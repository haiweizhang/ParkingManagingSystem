package ParkingManagingSystem;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 11-11-12
 * Time: 下午3:15
 * To change this template use File | Settings | File Templates.
 */
public class ParkingLot {
    private int parkCapacity;
    private Vector<Car> parkingCarList;
    public void clear()
    {
        parkingCarList.clear();
    }
    public  ParkingLot(int capacity)
    {
        parkCapacity = capacity;
        parkingCarList = new Vector<Car>();
    }
    public int availableParkingSpace()
    {
        return (parkCapacity - parkingCarList.size());  //To change body of created methods use File | Settings | File Templates.
    }

    public boolean park(Car aCar)
    {
        if(parkingCarList.size() < parkCapacity)
        {
            parkingCarList.add(aCar);
            return true;
        }
        else
        {
            return false ;
        }
    }


    public Car fetchCar(String s) {
        for(int i = 0; i < parkingCarList.size();i++)
        {
                 if(parkingCarList.elementAt(i).getID() == s)
                 {
                     return parkingCarList.remove(i);
                 }
        }
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}
