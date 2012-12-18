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
}
