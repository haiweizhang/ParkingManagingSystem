package ParkingManagingSystem;

import java.util.List;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-12-16
 * Time: 下午3:09
 * To change this template use File | Settings | File Templates.
 */


public class ParkingManager {
    Vector<ParkingBoy> parkingBoyVector;
    Vector<ParkingLot> parkingLotVector;
    final int MAX_RANDOM_TIMES = 100;

    public int getTotalPlaces() {
        int totalPlaces = 0;
        for(ParkingLot parkingLot: parkingLotVector) {
            totalPlaces += parkingLot.getParkCapacity();
        }
        for(ParkingBoy parkingBoy: parkingBoyVector) {
            totalPlaces += parkingBoy.getTotalPlaces();
        }
        return totalPlaces;
    }

    public int getAvailablePlaces() {
        int availablePlaces = 0;
        for(ParkingLot parkingLot: parkingLotVector) {
            availablePlaces += parkingLot.availableParkingSpace();
        }
        for(ParkingBoy parkingBoy: parkingBoyVector) {
            availablePlaces += parkingBoy.getAvailablePlaces();
        }
        return availablePlaces;
    }

    public ParkingManager(Vector<ParkingLot> parkingLotVector) {
        parkingBoyVector = new Vector<ParkingBoy>();
        this.parkingLotVector = parkingLotVector;
    }
    public ParkingManager() {
        parkingBoyVector = new Vector<ParkingBoy>();
    }
    public boolean managerAParkingBoy(ParkingBoy aBoy) {
        return parkingBoyVector.add(aBoy);
    }
    public ParkingReceipt park(int index, Car aCar) {
        if(parkingBoyVector.isEmpty()){
            return null;
        }
        return parkingBoyVector.get(index).park(aCar);
    }

    public Vector<ParkingLot> getParkingLotVector() {
        return parkingLotVector;
    }
    public ParkingReceipt park(ParkingBoy aBoy, Car aCar) {
        if(aBoy == null){
            if(parkingLotVector.isEmpty()){
                return null;
            }
            for(int i=0; i<MAX_RANDOM_TIMES; i++){
                ParkingLot parkingLot = parkingLotVector.get((int) Math.round(Math.random()));
                if(parkingLot.availableParkingSpace() == 0){
                    continue;
                } else {
                    return parkingLot.park(aCar);
                }
            }
            return null;
        } else {
            if(parkingBoyVector.contains(aBoy)){
                return aBoy.park(aCar);
            }
            return null;
        }
    }

    public Car fetchCar(ParkingReceipt pReceipt) {
        for(int i=0; i< parkingLotVector.size(); i++) {
            Car aCar;
            if((aCar = parkingLotVector.get(i).fetchCar(pReceipt)) != null) {
                return aCar;
            }
        }
        return null;
    }

    public void Print(int tabs) {
        for(int i=0; i<parkingLotVector.size(); i++) {
            for(int j=0; j<tabs; j++) {
                System.out.print('\t');
            }
            System.out.print("停车场编号：");
            System.out.println(i);
            parkingLotVector.get(i).Print(tabs+1);
        }
        for(int i=0; i<parkingBoyVector.size(); i++) {
            for(int j=0; j<tabs; j++) {
                System.out.print('\t');
            }
            System.out.print("停车仔编号：");
            System.out.println(i);
            parkingBoyVector.get(i).Print(tabs+1);
        }

        for(int i=0; i<tabs+1; i++) {
            System.out.print('\t');
        }
        System.out.print("Total 车位数：");
        System.out.println(getTotalPlaces());
        for(int i=0; i<tabs+1; i++) {
            System.out.print('\t');
        }
        System.out.print("Total 空位数：");
        System.out.println(getAvailablePlaces());
    }
}
