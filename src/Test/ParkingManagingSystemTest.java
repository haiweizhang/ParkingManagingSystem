package Test;

import Main.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        Assert.assertEquals(parkingLotVector.get(0).availableParkingSpace(), 99);
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
        Assert.assertEquals(parkingLotVector.get(1).availableParkingSpace(), 99);
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
        Assert.assertEquals(parkingLotVector.get(2).availableParkingSpace(), 99);
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

    @Test
    public  void  should_park_in_the_first_parking_lot_for_smart(){
        Vector<ParkingLot> parkingLotVector = new Vector<ParkingLot>();
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        for(int i=0; i<20; i++) {
            parkingLotVector.get(0).park(new Car());
        }
        for(int i=0; i<30; i++) {
            parkingLotVector.get(1).park(new Car());
        }
        for(int i=0; i<40; i++) {
            parkingLotVector.get(2).park(new Car());
        }
        ParkingBoy aBoy = new ParkingBoy(parkingLotVector, new MaxAvailableParkingLotChooser());
        Car aCar = new Car();

        ParkingReceipt receipt = aBoy.park(aCar);
        Assert.assertNotNull(receipt);
        Assert.assertEquals(parkingLotVector.get(0).availableParkingSpace(), 79);
        // ParkingReceipt otherReceipt = new ParkingReceipt();
        //Assert.assertEquals(aBoy.fetchCar(receipt), aCar);
        //Assert.assertEquals(aBoy.fetchCar(otherReceipt), null);
    }

    @Test
    public  void  should_park_in_the_second_parking_lot_for_smart(){
        Vector<ParkingLot> parkingLotVector = new Vector<ParkingLot>();
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        for(int i=0; i<30; i++) {
            parkingLotVector.get(0).park(new Car());
        }
        for(int i=0; i<20; i++) {
            parkingLotVector.get(1).park(new Car());
        }
        for(int i=0; i<40; i++) {
            parkingLotVector.get(2).park(new Car());
        }
        ParkingBoy aBoy = new ParkingBoy(parkingLotVector, new MaxAvailableParkingLotChooser());
        Car aCar = new Car();

        ParkingReceipt receipt = aBoy.park(aCar);
        Assert.assertNotNull(receipt);
        Assert.assertEquals(parkingLotVector.get(1).availableParkingSpace(), 79);
    }

    @Test
    public  void  should_park_in_the_third_parking_lot_for_smart(){
        Vector<ParkingLot> parkingLotVector = new Vector<ParkingLot>();
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        for(int i=0; i<30; i++) {
            parkingLotVector.get(0).park(new Car());
        }
        for(int i=0; i<40; i++) {
            parkingLotVector.get(1).park(new Car());
        }
        for(int i=0; i<20; i++) {
            parkingLotVector.get(2).park(new Car());
        }
        ParkingBoy aBoy = new ParkingBoy(parkingLotVector, new MaxAvailableParkingLotChooser());
        Car aCar = new Car();

        ParkingReceipt receipt = aBoy.park(aCar);
        Assert.assertNotNull(receipt);
        Assert.assertEquals(parkingLotVector.get(2).availableParkingSpace(), 79);
    }

    @Test
    public  void  park_should_fail_for_smart(){
        Vector<ParkingLot> parkingLotVector = new Vector<ParkingLot>();
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        for(int i=0; i<100; i++) {
            parkingLotVector.get(0).park(new Car());
            parkingLotVector.get(1).park(new Car());
            parkingLotVector.get(2).park(new Car());
        }
        ParkingBoy aBoy = new ParkingBoy(parkingLotVector, new MaxAvailableParkingLotChooser());
        Car aCar = new Car();
        ParkingReceipt receipt = aBoy.park(aCar);
        Assert.assertNull(receipt);
    }

    @Test
    public  void  should_fetch_the_car_in_the_first_parking_lot_for_smart(){
        Vector<ParkingLot> parkingLotVector = new Vector<ParkingLot>();
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.get(1).park(new Car());
        parkingLotVector.get(2).park(new Car());
        ParkingBoy aBoy = new ParkingBoy(parkingLotVector, new MaxAvailableParkingLotChooser());
        Car aCar = new Car();

        ParkingReceipt receipt = aBoy.park(aCar);
        Assert.assertNotNull(receipt);
        Assert.assertSame(aBoy.fetchCar(receipt), aCar);
    }

    @Test
    public  void  should_fetch_the_car_in_the_third_parking_lot_for_smart(){
        Vector<ParkingLot> parkingLotVector = new Vector<ParkingLot>();
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        Car aCar = new Car();
        Car bCar = new Car();
        ParkingReceipt receipt0 = parkingLotVector.get(0).park(aCar);
        ParkingReceipt receipt2 = parkingLotVector.get(2).park(bCar);
        ParkingBoy aBoy = new ParkingBoy(parkingLotVector, new MaxAvailableParkingLotChooser());

        Assert.assertNotNull(aBoy.fetchCar(receipt0));
        Assert.assertSame(aBoy.fetchCar(receipt2), bCar);
    }

    @Test
    public  void  should_park_in_the_first_parking_lot_for_super(){
        Vector<ParkingLot> parkingLotVector = new Vector<ParkingLot>();
        parkingLotVector.add(new ParkingLot(50));
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(80));
        for(int i=0; i<10; i++) {
            parkingLotVector.get(0).park(new Car());
        }
        for(int i=0; i<25; i++) {
            parkingLotVector.get(1).park(new Car());
        }
        for(int i=0; i<20; i++) {
            parkingLotVector.get(2).park(new Car());
        }
        ParkingBoy aBoy = new ParkingBoy(parkingLotVector, new HighVacancyRateParkingLotChooser());
        Car aCar = new Car();

        ParkingReceipt receipt = aBoy.park(aCar);
        Assert.assertNotNull(receipt);
        Assert.assertEquals(parkingLotVector.get(0).availableParkingSpace(), 39);
        // ParkingReceipt otherReceipt = new ParkingReceipt();
        //Assert.assertEquals(aBoy.fetchCar(receipt), aCar);
        //Assert.assertEquals(aBoy.fetchCar(otherReceipt), null);
    }

    @Test
    public  void  should_park_in_the_second_parking_lot_for_super(){
        Vector<ParkingLot> parkingLotVector = new Vector<ParkingLot>();
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(50));
        parkingLotVector.add(new ParkingLot(80));
        for(int i=0; i<25; i++) {
            parkingLotVector.get(0).park(new Car());
        }
        for(int i=0; i<10; i++) {
            parkingLotVector.get(1).park(new Car());
        }
        for(int i=0; i<20; i++) {
            parkingLotVector.get(2).park(new Car());
        }
        ParkingBoy aBoy = new ParkingBoy(parkingLotVector, new HighVacancyRateParkingLotChooser());
        Car aCar = new Car();

        ParkingReceipt receipt = aBoy.park(aCar);
        Assert.assertNotNull(receipt);
        Assert.assertEquals(parkingLotVector.get(1).availableParkingSpace(), 39);
    }

    @Test
    public  void  should_park_in_the_third_parking_lot_for_super(){
        Vector<ParkingLot> parkingLotVector = new Vector<ParkingLot>();
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(80));
        parkingLotVector.add(new ParkingLot(50));
        for(int i=0; i<25; i++) {
            parkingLotVector.get(0).park(new Car());
        }
        for(int i=0; i<20; i++) {
            parkingLotVector.get(1).park(new Car());
        }
        for(int i=0; i<10; i++) {
            parkingLotVector.get(2).park(new Car());
        }
        ParkingBoy aBoy = new ParkingBoy(parkingLotVector, new HighVacancyRateParkingLotChooser());
        Car aCar = new Car();

        ParkingReceipt receipt = aBoy.park(aCar);
        Assert.assertNotNull(receipt);
        Assert.assertEquals(parkingLotVector.get(2).availableParkingSpace(), 39);
    }

    @Test
    public  void  park_should_fail_for_super(){
        Vector<ParkingLot> parkingLotVector = new Vector<ParkingLot>();
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        for(int i=0; i<100; i++) {
            parkingLotVector.get(0).park(new Car());
            parkingLotVector.get(1).park(new Car());
            parkingLotVector.get(2).park(new Car());
        }
        ParkingBoy aBoy = new ParkingBoy(parkingLotVector, new HighVacancyRateParkingLotChooser());
        Car aCar = new Car();
        ParkingReceipt receipt = aBoy.park(aCar);
        Assert.assertNull(receipt);
    }

    @Test
    public  void  should_fetch_the_car_in_the_first_parking_lot_for_super(){
        Vector<ParkingLot> parkingLotVector = new Vector<ParkingLot>();
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.get(1).park(new Car());
        parkingLotVector.get(2).park(new Car());
        ParkingBoy aBoy = new ParkingBoy(parkingLotVector, new HighVacancyRateParkingLotChooser());
        Car aCar = new Car();

        ParkingReceipt receipt = aBoy.park(aCar);
        Assert.assertNotNull(receipt);
        Assert.assertSame(aBoy.fetchCar(receipt), aCar);
    }

    @Test
    public  void  should_fetch_the_car_in_the_third_parking_lot_for_super(){
        Vector<ParkingLot> parkingLotVector = new Vector<ParkingLot>();
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        parkingLotVector.add(new ParkingLot(100));
        Car aCar = new Car();
        Car bCar = new Car();
        ParkingReceipt receipt0 = parkingLotVector.get(0).park(aCar);
        ParkingReceipt receipt2 = parkingLotVector.get(2).park(bCar);
        ParkingBoy aBoy = new ParkingBoy(parkingLotVector, new HighVacancyRateParkingLotChooser());

        Assert.assertNotNull(aBoy.fetchCar(receipt0));
        Assert.assertSame(aBoy.fetchCar(receipt2), bCar);
    }

    @Test
    public void should_park_in_the_first_parking_lot_of_normal_boy() {
        Vector<ParkingLot> parkingLotVector0 = new Vector<ParkingLot>();
        parkingLotVector0.add(new ParkingLot(100));
        for(int i=0; i<100; i++) {
            parkingLotVector0.get(0).park(new Car());
        }
        parkingLotVector0.add(new ParkingLot(100));
        parkingLotVector0.add(new ParkingLot(100));
        Vector<ParkingLot> parkingLotVector1 = new Vector<ParkingLot>();
        parkingLotVector1.add(new ParkingLot(100));
        parkingLotVector1.add(new ParkingLot(100));
        parkingLotVector1.add(new ParkingLot(100));
        Vector<ParkingLot> parkingLotVector2 = new Vector<ParkingLot>();
        parkingLotVector2.add(new ParkingLot(100));
        parkingLotVector2.add(new ParkingLot(100));
        parkingLotVector2.add(new ParkingLot(100));
        ParkingBoy aBoy = new ParkingBoy(parkingLotVector0, new FirstAvailableParkingLotChooser());
        ParkingBoy bBoy = new ParkingBoy(parkingLotVector1, new MaxAvailableParkingLotChooser());
        ParkingBoy cBoy = new ParkingBoy(parkingLotVector2, new HighVacancyRateParkingLotChooser());
        ParkingManager aManager = new ParkingManager();
        aManager.managerAParkingBoy(aBoy);
        aManager.managerAParkingBoy(bBoy);
        aManager.managerAParkingBoy(cBoy);
        ParkingReceipt parkingReceipt = aManager.park(aBoy, new Car());
        Assert.assertNotNull(parkingReceipt);
        Assert.assertEquals(aBoy.getParkingLotVector().get(1).availableParkingSpace(), 99);
    }

    @Test
    public void should_park_in_the_max_available_parking_lot_of_smart_boy() {
        Vector<ParkingLot> parkingLotVector0 = new Vector<ParkingLot>();
        parkingLotVector0.add(new ParkingLot(100));
        parkingLotVector0.add(new ParkingLot(100));
        parkingLotVector0.add(new ParkingLot(100));
        Vector<ParkingLot> parkingLotVector1 = new Vector<ParkingLot>();
        parkingLotVector1.add(new ParkingLot(100));
        for(int i=0; i<20; i++) {
            parkingLotVector1.get(0).park(new Car());
        }
        parkingLotVector1.add(new ParkingLot(100));
        for(int i=0; i<30; i++) {
            parkingLotVector1.get(1).park(new Car());
        }
        parkingLotVector1.add(new ParkingLot(100));
        Vector<ParkingLot> parkingLotVector2 = new Vector<ParkingLot>();
        parkingLotVector2.add(new ParkingLot(100));
        parkingLotVector2.add(new ParkingLot(100));
        parkingLotVector2.add(new ParkingLot(100));
        ParkingBoy aBoy = new ParkingBoy(parkingLotVector0, new FirstAvailableParkingLotChooser());
        ParkingBoy bBoy = new ParkingBoy(parkingLotVector1, new MaxAvailableParkingLotChooser());
        ParkingBoy cBoy = new ParkingBoy(parkingLotVector2, new HighVacancyRateParkingLotChooser());
        ParkingManager aManager = new ParkingManager();
        aManager.managerAParkingBoy(aBoy);
        aManager.managerAParkingBoy(bBoy);
        aManager.managerAParkingBoy(cBoy);
        ParkingReceipt parkingReceipt = aManager.park(bBoy, new Car());
        Assert.assertNotNull(parkingReceipt);
        Assert.assertEquals(bBoy.getParkingLotVector().get(2).availableParkingSpace(), 99);
    }

    @Test
    public void should_park_in_the_highest_vacancy_parking_lot_of_super_boy() {
        Vector<ParkingLot> parkingLotVector0 = new Vector<ParkingLot>();
        parkingLotVector0.add(new ParkingLot(100));
        parkingLotVector0.add(new ParkingLot(100));
        parkingLotVector0.add(new ParkingLot(100));
        Vector<ParkingLot> parkingLotVector1 = new Vector<ParkingLot>();
        parkingLotVector1.add(new ParkingLot(100));
        parkingLotVector1.add(new ParkingLot(100));
        parkingLotVector1.add(new ParkingLot(100));
        Vector<ParkingLot> parkingLotVector2 = new Vector<ParkingLot>();
        parkingLotVector2.add(new ParkingLot(100));
        for(int i=0; i<40; i++) {
            parkingLotVector2.get(0).park(new Car());
        }
        parkingLotVector2.add(new ParkingLot(100));
        for(int i=0; i<30; i++) {
            parkingLotVector2.get(1).park(new Car());
        }
        parkingLotVector2.add(new ParkingLot(100));
        for(int i=0; i<50; i++) {
            parkingLotVector2.get(2).park(new Car());
        }
        ParkingBoy aBoy = new ParkingBoy(parkingLotVector0, new FirstAvailableParkingLotChooser());
        ParkingBoy bBoy = new ParkingBoy(parkingLotVector1, new MaxAvailableParkingLotChooser());
        ParkingBoy cBoy = new ParkingBoy(parkingLotVector2, new HighVacancyRateParkingLotChooser());
        ParkingManager aManager = new ParkingManager();
        aManager.managerAParkingBoy(aBoy);
        aManager.managerAParkingBoy(bBoy);
        aManager.managerAParkingBoy(cBoy);
        ParkingReceipt parkingReceipt = aManager.park(cBoy, new Car());
        Assert.assertNotNull(parkingReceipt);
        Assert.assertEquals(cBoy.getParkingLotVector().get(1).availableParkingSpace(), 69);
    }

    @Test
    public void should_park_in_the_max_available_parking_lot_of_manager() {
        Vector<ParkingLot> parkingLotVector0 = new Vector<ParkingLot>();
        parkingLotVector0.add(new ParkingLot(100));
        parkingLotVector0.add(new ParkingLot(100));
        parkingLotVector0.add(new ParkingLot(100));
        Vector<ParkingLot> parkingLotVector1 = new Vector<ParkingLot>();
        parkingLotVector1.add(new ParkingLot(100));
        parkingLotVector1.add(new ParkingLot(100));
        parkingLotVector1.add(new ParkingLot(100));
        Vector<ParkingLot> parkingLotVector2 = new Vector<ParkingLot>();
        parkingLotVector2.add(new ParkingLot(100));
        parkingLotVector2.add(new ParkingLot(100));
        parkingLotVector2.add(new ParkingLot(100));
        Vector<ParkingLot> parkingLotVector3 = new Vector<ParkingLot>();
        parkingLotVector3.add(new ParkingLot(100));
        parkingLotVector3.add(new ParkingLot(100));
        ParkingBoy aBoy = new ParkingBoy(parkingLotVector0, new FirstAvailableParkingLotChooser());
        ParkingBoy bBoy = new ParkingBoy(parkingLotVector1, new MaxAvailableParkingLotChooser());
        ParkingBoy cBoy = new ParkingBoy(parkingLotVector2, new HighVacancyRateParkingLotChooser());
        ParkingManager aManager = new ParkingManager(parkingLotVector3);
        aManager.managerAParkingBoy(aBoy);
        aManager.managerAParkingBoy(bBoy);
        aManager.managerAParkingBoy(cBoy);
        for(int i=0; i<99; i++) {
            ParkingReceipt parkingReceipt = aManager.park(null, new Car());
            Assert.assertNotNull(parkingReceipt);
        }

        Assert.assertTrue(aManager.getParkingLotVector().get(0).availableParkingSpace() < 100);
        Assert.assertTrue(aManager.getParkingLotVector().get(1).availableParkingSpace() < 100);
    }

    @Test
    public void should_fetch_the_same_car_from_parking_lot_of_manager() {
        Vector<ParkingLot> parkingLotVector0 = new Vector<ParkingLot>();
        parkingLotVector0.add(new ParkingLot(100));
        parkingLotVector0.add(new ParkingLot(100));
        parkingLotVector0.add(new ParkingLot(100));
        Vector<ParkingLot> parkingLotVector1 = new Vector<ParkingLot>();
        parkingLotVector1.add(new ParkingLot(100));
        parkingLotVector1.add(new ParkingLot(100));
        parkingLotVector1.add(new ParkingLot(100));
        Vector<ParkingLot> parkingLotVector2 = new Vector<ParkingLot>();
        parkingLotVector2.add(new ParkingLot(100));
        parkingLotVector2.add(new ParkingLot(100));
        parkingLotVector2.add(new ParkingLot(100));
        Vector<ParkingLot> parkingLotVector3 = new Vector<ParkingLot>();
        parkingLotVector3.add(new ParkingLot(100));
        parkingLotVector3.add(new ParkingLot(100));
        ParkingBoy aBoy = new ParkingBoy(parkingLotVector0, new FirstAvailableParkingLotChooser());
        ParkingBoy bBoy = new ParkingBoy(parkingLotVector1, new MaxAvailableParkingLotChooser());
        ParkingBoy cBoy = new ParkingBoy(parkingLotVector2, new HighVacancyRateParkingLotChooser());
        ParkingManager aManager = new ParkingManager(parkingLotVector3);
        aManager.managerAParkingBoy(aBoy);
        aManager.managerAParkingBoy(bBoy);
        aManager.managerAParkingBoy(cBoy);
        Car aCar = new Car();
        ParkingReceipt parkingReceipt = aManager.park(null, aCar);

        Assert.assertNotNull(parkingReceipt);
        Assert.assertSame(aManager.fetchCar(parkingReceipt), aCar);
    }

    @Test
    public void report_for_parking_lot_of_manager_should_reduce_1() {
        Vector<ParkingLot> parkingLotVector0 = new Vector<ParkingLot>();
        parkingLotVector0.add(new ParkingLot(100));
        parkingLotVector0.add(new ParkingLot(100));
        parkingLotVector0.add(new ParkingLot(100));
        Vector<ParkingLot> parkingLotVector1 = new Vector<ParkingLot>();
        parkingLotVector1.add(new ParkingLot(100));
        parkingLotVector1.add(new ParkingLot(100));
        parkingLotVector1.add(new ParkingLot(100));
        Vector<ParkingLot> parkingLotVector2 = new Vector<ParkingLot>();
        parkingLotVector2.add(new ParkingLot(100));
        parkingLotVector2.add(new ParkingLot(100));
        parkingLotVector2.add(new ParkingLot(100));
        Vector<ParkingLot> parkingLotVector3 = new Vector<ParkingLot>();
        parkingLotVector3.add(new ParkingLot(100));
        parkingLotVector3.add(new ParkingLot(100));
        ParkingBoy aBoy = new ParkingBoy(parkingLotVector0, new FirstAvailableParkingLotChooser());
        ParkingBoy bBoy = new ParkingBoy(parkingLotVector1, new MaxAvailableParkingLotChooser());
        ParkingBoy cBoy = new ParkingBoy(parkingLotVector2, new HighVacancyRateParkingLotChooser());
        ParkingManager aManager = new ParkingManager(parkingLotVector3);
        aManager.managerAParkingBoy(aBoy);
        aManager.managerAParkingBoy(bBoy);
        aManager.managerAParkingBoy(cBoy);

        int leftPlaces = aManager.getAvailablePlaces();

        aManager.Print(0);
        Car aCar = new Car();
        ParkingReceipt parkingReceipt = aManager.park(null, aCar);

        Assert.assertNotNull(parkingReceipt);
        Assert.assertEquals(aManager.getAvailablePlaces(), leftPlaces-1);
        aManager.Print(0);
    }

    @Test
    public void report_for_parking_lot_of_normal_boy_should_reduce_1() {
        Vector<ParkingLot> parkingLotVector0 = new Vector<ParkingLot>();
        parkingLotVector0.add(new ParkingLot(100));
        parkingLotVector0.add(new ParkingLot(100));
        parkingLotVector0.add(new ParkingLot(100));
        Vector<ParkingLot> parkingLotVector1 = new Vector<ParkingLot>();
        parkingLotVector1.add(new ParkingLot(100));
        parkingLotVector1.add(new ParkingLot(100));
        parkingLotVector1.add(new ParkingLot(100));
        Vector<ParkingLot> parkingLotVector2 = new Vector<ParkingLot>();
        parkingLotVector2.add(new ParkingLot(100));
        parkingLotVector2.add(new ParkingLot(100));
        parkingLotVector2.add(new ParkingLot(100));
        Vector<ParkingLot> parkingLotVector3 = new Vector<ParkingLot>();
        parkingLotVector3.add(new ParkingLot(100));
        parkingLotVector3.add(new ParkingLot(100));
        ParkingBoy aBoy = new ParkingBoy(parkingLotVector0, new FirstAvailableParkingLotChooser());
        ParkingBoy bBoy = new ParkingBoy(parkingLotVector1, new MaxAvailableParkingLotChooser());
        ParkingBoy cBoy = new ParkingBoy(parkingLotVector2, new HighVacancyRateParkingLotChooser());
        ParkingManager aManager = new ParkingManager(parkingLotVector3);
        aManager.managerAParkingBoy(aBoy);
        aManager.managerAParkingBoy(bBoy);
        aManager.managerAParkingBoy(cBoy);

        int leftPlaces = aManager.getAvailablePlaces();
        int leftPlacesForBoy = aBoy.getAvailablePlaces();

        aManager.Print(0);
        Car aCar = new Car();
        ParkingReceipt parkingReceipt = aManager.park(aBoy, aCar);

        Assert.assertNotNull(parkingReceipt);
        Assert.assertEquals(aManager.getAvailablePlaces(), leftPlaces-1);
        Assert.assertEquals(aBoy.getAvailablePlaces(), leftPlacesForBoy-1);
        aManager.Print(0);
    }

    @Test
    public void report_for_parking_lot_of_smart_boy_should_reduce_1() {
        Vector<ParkingLot> parkingLotVector0 = new Vector<ParkingLot>();
        parkingLotVector0.add(new ParkingLot(100));
        parkingLotVector0.add(new ParkingLot(100));
        parkingLotVector0.add(new ParkingLot(100));
        Vector<ParkingLot> parkingLotVector1 = new Vector<ParkingLot>();
        parkingLotVector1.add(new ParkingLot(100));
        parkingLotVector1.add(new ParkingLot(100));
        parkingLotVector1.add(new ParkingLot(100));
        Vector<ParkingLot> parkingLotVector2 = new Vector<ParkingLot>();
        parkingLotVector2.add(new ParkingLot(100));
        parkingLotVector2.add(new ParkingLot(100));
        parkingLotVector2.add(new ParkingLot(100));
        Vector<ParkingLot> parkingLotVector3 = new Vector<ParkingLot>();
        parkingLotVector3.add(new ParkingLot(100));
        parkingLotVector3.add(new ParkingLot(100));
        ParkingBoy aBoy = new ParkingBoy(parkingLotVector0, new FirstAvailableParkingLotChooser());
        ParkingBoy bBoy = new ParkingBoy(parkingLotVector1, new MaxAvailableParkingLotChooser());
        ParkingBoy cBoy = new ParkingBoy(parkingLotVector2, new HighVacancyRateParkingLotChooser());
        ParkingManager aManager = new ParkingManager(parkingLotVector3);
        aManager.managerAParkingBoy(aBoy);
        aManager.managerAParkingBoy(bBoy);
        aManager.managerAParkingBoy(cBoy);

        int leftPlaces = aManager.getAvailablePlaces();
        int leftPlacesForBoy = bBoy.getAvailablePlaces();

        aManager.Print(0);
        Car aCar = new Car();
        ParkingReceipt parkingReceipt = aManager.park(bBoy, aCar);

        Assert.assertNotNull(parkingReceipt);
        Assert.assertEquals(aManager.getAvailablePlaces(), leftPlaces-1);
        Assert.assertEquals(bBoy.getAvailablePlaces(), leftPlacesForBoy-1);
        aManager.Print(0);
    }

    @Test
    public void report_for_parking_lot_of_super_boy_should_reduce_1() {
        Vector<ParkingLot> parkingLotVector0 = new Vector<ParkingLot>();
        parkingLotVector0.add(new ParkingLot(100));
        parkingLotVector0.add(new ParkingLot(100));
        parkingLotVector0.add(new ParkingLot(100));
        Vector<ParkingLot> parkingLotVector1 = new Vector<ParkingLot>();
        parkingLotVector1.add(new ParkingLot(100));
        parkingLotVector1.add(new ParkingLot(100));
        parkingLotVector1.add(new ParkingLot(100));
        Vector<ParkingLot> parkingLotVector2 = new Vector<ParkingLot>();
        parkingLotVector2.add(new ParkingLot(100));
        parkingLotVector2.add(new ParkingLot(100));
        parkingLotVector2.add(new ParkingLot(100));
        Vector<ParkingLot> parkingLotVector3 = new Vector<ParkingLot>();
        parkingLotVector3.add(new ParkingLot(100));
        parkingLotVector3.add(new ParkingLot(100));
        ParkingBoy aBoy = new ParkingBoy(parkingLotVector0, new FirstAvailableParkingLotChooser());
        ParkingBoy bBoy = new ParkingBoy(parkingLotVector1, new MaxAvailableParkingLotChooser());
        ParkingBoy cBoy = new ParkingBoy(parkingLotVector2, new HighVacancyRateParkingLotChooser());
        ParkingManager aManager = new ParkingManager(parkingLotVector3);
        aManager.managerAParkingBoy(aBoy);
        aManager.managerAParkingBoy(bBoy);
        aManager.managerAParkingBoy(cBoy);

        int leftPlaces = aManager.getAvailablePlaces();
        int leftPlacesForBoy = cBoy.getAvailablePlaces();

        aManager.Print(0);
        Car aCar = new Car();
        ParkingReceipt parkingReceipt = aManager.park(cBoy, aCar);

        Assert.assertNotNull(parkingReceipt);
        Assert.assertEquals(aManager.getAvailablePlaces(), leftPlaces-1);
        Assert.assertEquals(cBoy.getAvailablePlaces(), leftPlacesForBoy-1);
        aManager.Print(0);
    }
}
