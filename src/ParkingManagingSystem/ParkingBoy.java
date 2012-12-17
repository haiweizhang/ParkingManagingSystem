package ParkingManagingSystem;

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


}
