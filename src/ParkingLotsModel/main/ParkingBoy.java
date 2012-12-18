package main;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-12-2
 * Time: 下午3:50
 * To change this template use File | Settings | File Templates.
 */
public class ParkingBoy {
    Vector<ParkingLot> parkingLotVector;
    ParkingLotChooser parkingLotChooser;

    public int getTotalPlaces() {
        int totalPlaces = 0;
        for(ParkingLot parkingLot: parkingLotVector) {
            totalPlaces += parkingLot.getParkCapacity();
        }
        return totalPlaces;
    }

    public int getAvailablePlaces() {
        int availablePlaces = 0;
        for(ParkingLot parkingLot: parkingLotVector) {
            availablePlaces += parkingLot.availableParkingSpace();
        }
        return availablePlaces;
    }


    public ParkingBoy(Vector<ParkingLot> parkingLots, ParkingLotChooser parkingLotChooser) {
        parkingLotVector = parkingLots;
        this.parkingLotChooser = parkingLotChooser;
    }

    public ParkingReceipt park(Car aCar) {
        if(parkingLotChooser.getAvailableParkLot(parkingLotVector) != null) {
           return parkingLotChooser.getAvailableParkLot(parkingLotVector).park(aCar);
        }
        return null;
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

    public Vector<ParkingLot> getParkingLotVector() {
        return parkingLotVector;
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

        for(int i=0; i<tabs; i++) {
            System.out.print('\t');
        }
        System.out.print("Total 车位数：");

        System.out.println(getTotalPlaces());
        for(int i=0; i<tabs; i++) {
            System.out.print('\t');
        }
        System.out.print("Total 空位数：");
        System.out.println(getAvailablePlaces());
    }
}
