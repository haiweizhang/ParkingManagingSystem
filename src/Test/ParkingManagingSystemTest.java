package Test;

import ParkingManagingSystem.Car;
import ParkingManagingSystem.ParkingLot;
import ParkingManagingSystem.ParkingReceipt;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 11-11-12
 * Time: 下午3:05
 * To change this template use File | Settings | File Templates.
 */
public class ParkingManagingSystemTest{
   public ParkingLot parkingLot;

   @Before
   public void init(){
       parkingLot = new ParkingLot(100);
   }
   @After
   public void tearDown(){
       parkingLot.clear();
   }
   @Test
   public void parking_should_succeed(){
       Car aCar = new Car("ABCEDFG");
       int leftParkingSpace = parkingLot.availableParkingSpace();
       ParkingReceipt receipt = parkingLot.park(aCar);
       Assert.assertEquals(leftParkingSpace-1, parkingLot.availableParkingSpace());
   }
    @Test
    public void parking_should_failed(){
        for(int i = 0; i < 100; i++)
        {
            Car aCar = new Car(String.valueOf(i));
            parkingLot.park(aCar);
        }
        Car aCar = new Car("asdl");
        ParkingReceipt receipt = parkingLot.park(aCar);
        Assert.assertEquals(null, receipt);
    }
    @Test
    public  void  afterFetchCar_should_succeed(){
        Car aCar = new Car();
        Car bCar = new Car();
        ParkingReceipt aReceipt = parkingLot.park(aCar);
        ParkingReceipt bReceipt = parkingLot.park(bCar);
        int leftParkingSpace = parkingLot.availableParkingSpace();
        aCar = parkingLot.fetchCar(aReceipt);
        Assert.assertEquals(leftParkingSpace+1, parkingLot.availableParkingSpace());

        bCar = parkingLot.fetchCar(bReceipt);
        Assert.assertEquals(leftParkingSpace+2, parkingLot.availableParkingSpace());
    }

    @Test
    public  void  should_fetch_the_same_car(){
        Car aCar = new Car("ABCEDFG");
        Car bCar = new Car("ACCDAAE");
       ParkingReceipt receipt =  parkingLot.park(aCar);
        Car cCar = parkingLot.fetchCar(receipt);
        Assert.assertEquals(cCar,aCar);
    }
    @Test
    public  void  should_not_fetch_the_same_car(){
        Car aCar = new Car("ABCEDFG");
        Car bCar = new Car("ACCDAAE");
        parkingLot.park(aCar);
        ParkingReceipt otherReceipt = new ParkingReceipt();
        Assert.assertEquals(parkingLot.fetchCar(otherReceipt), null);
    }

    @Test
    public  void  should_not_fetch_the_same_car_twice(){
        Car aCar = new Car("ABCEDFG");
        ParkingReceipt receipt = parkingLot.park(aCar);
        ParkingReceipt otherReceipt = new ParkingReceipt();
        Assert.assertEquals(parkingLot.fetchCar(receipt), aCar);
        Assert.assertEquals(parkingLot.fetchCar(otherReceipt), null);
    }
}
