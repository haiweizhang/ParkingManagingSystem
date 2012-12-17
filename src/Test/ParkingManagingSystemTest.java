package Test;

import ParkingManagingSystem.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Vector;

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
   }
   @After
   public void tearDown(){
   }
   @Test
   public void parking_should_succeed(){
       parkingLot = new ParkingLot(100);
       Car aCar = new Car();
       int leftParkingSpace = parkingLot.availableParkingSpace();
       ParkingReceipt receipt = parkingLot.park(aCar);
       Assert.assertEquals(leftParkingSpace-1, parkingLot.availableParkingSpace());
   }
    @Test
    public void parking_should_fail(){
        parkingLot = new ParkingLot(100);
        for(int i = 0; i < 100; i++)
        {
            Car aCar = new Car(String.valueOf(i));
            parkingLot.park(aCar);
        }
        Car aCar = new Car();
        ParkingReceipt receipt = parkingLot.park(aCar);
        Assert.assertEquals(null, receipt);
    }
    @Test
    public  void  fetching_two_car_should_succeed(){
        parkingLot = new ParkingLot(100);
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
        parkingLot = new ParkingLot(100);
        Car aCar = new Car();
        Car bCar = new Car();
       ParkingReceipt receipt =  parkingLot.park(aCar);
        Car cCar = parkingLot.fetchCar(receipt);
        Assert.assertEquals(cCar,aCar);
    }
    @Test
    public  void  should_not_fetch_the_same_car(){
        parkingLot = new ParkingLot(100);
        Car aCar = new Car();
        Car bCar = new Car();
        parkingLot.park(aCar);
        ParkingReceipt otherReceipt = new ParkingReceipt();
        Assert.assertEquals(parkingLot.fetchCar(otherReceipt), null);
    }

    @Test
    public  void  should_not_fetch_the_same_car_twice(){
        parkingLot = new ParkingLot(100);
        Car aCar = new Car();
        ParkingReceipt receipt = parkingLot.park(aCar);
        ParkingReceipt otherReceipt = new ParkingReceipt();
        Assert.assertEquals(parkingLot.fetchCar(receipt), aCar);
        Assert.assertEquals(parkingLot.fetchCar(otherReceipt), null);
    }

    @Test
    public  void  should_park_in_the_first_parking_lot_for_normal(){
        Vector<ParkingLot> parkingLotVector = new Vector<ParkingLot>();
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        ParkingBoy aBoy = new ParkingBoy(parkingLotVector, new FirstAvailableParkingLotChooser());
        Car aCar = new Car();

        ParkingReceipt receipt = aBoy.park(aCar);
        Assert.assertNotNull(receipt);
        Assert.assertEquals(aBoy.getParkingLotVector().get(0).availableParkingSpace(), 99);
       // ParkingReceipt otherReceipt = new ParkingReceipt();
        //Assert.assertEquals(aBoy.fetchCar(receipt), aCar);
        //Assert.assertEquals(aBoy.fetchCar(otherReceipt), null);
    }

    @Test
    public  void  should_park_in_the_second_parking_lot_for_normal(){
        Vector<ParkingLot> parkingLotVector = new Vector<ParkingLot>();
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        for(int i=0; i<100; i++) {
            parkingLotVector.get(0).park(new Car());
            //parkingLotVector.get(1).park(new Car());
        }
        ParkingBoy aBoy = new ParkingBoy(parkingLotVector, new FirstAvailableParkingLotChooser());
        Car aCar = new Car();

        ParkingReceipt receipt = aBoy.park(aCar);
        Assert.assertNotNull(receipt);
        Assert.assertEquals(aBoy.getParkingLotVector().get(1).availableParkingSpace(), 99);
    }

    @Test
    public  void  should_park_in_the_third_parking_lot_for_normal(){
        Vector<ParkingLot> parkingLotVector = new Vector<ParkingLot>();
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        for(int i=0; i<100; i++) {
            parkingLotVector.get(0).park(new Car());
            parkingLotVector.get(1).park(new Car());
        }
        ParkingBoy aBoy = new ParkingBoy(parkingLotVector, new FirstAvailableParkingLotChooser());
        Car aCar = new Car();

        ParkingReceipt receipt = aBoy.park(aCar);
        Assert.assertNotNull(receipt);
        Assert.assertEquals(aBoy.getParkingLotVector().get(2).availableParkingSpace(), 99);
    }

    @Test
    public  void  park_should_fail_for_normal(){
        Vector<ParkingLot> parkingLotVector = new Vector<ParkingLot>();
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        for(int i=0; i<100; i++) {
            parkingLotVector.get(0).park(new Car());
            parkingLotVector.get(1).park(new Car());
            parkingLotVector.get(2).park(new Car());
        }
        ParkingBoy aBoy = new ParkingBoy(parkingLotVector, new FirstAvailableParkingLotChooser());
        Car aCar = new Car();
        ParkingReceipt receipt = aBoy.park(aCar);
        Assert.assertNull(receipt);
    }

    @Test
    public  void  should_fetch_the_car_in_the_first_parking_lot_for_normal(){
        Vector<ParkingLot> parkingLotVector = new Vector<ParkingLot>();
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        ParkingBoy aBoy = new ParkingBoy(parkingLotVector, new FirstAvailableParkingLotChooser());
        Car aCar = new Car();

        ParkingReceipt receipt = aBoy.park(aCar);
        Assert.assertNotNull(receipt);
        Assert.assertSame(aBoy.fetchCar(receipt), aCar);
    }

    @Test
    public  void  should_fetch_the_car_in_the_third_parking_lot_for_normal(){
        Vector<ParkingLot> parkingLotVector = new Vector<ParkingLot>();
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        Car aCar = new Car();
        Car bCar = new Car();
        ParkingReceipt receipt0 = parkingLotVector.get(0).park(aCar);
        ParkingReceipt receipt2 = parkingLotVector.get(2).park(bCar);
        ParkingBoy aBoy = new ParkingBoy(parkingLotVector, new FirstAvailableParkingLotChooser());

        Assert.assertNotNull(aBoy.fetchCar(receipt0));
        Assert.assertSame(aBoy.fetchCar(receipt2), bCar);

    }
}
