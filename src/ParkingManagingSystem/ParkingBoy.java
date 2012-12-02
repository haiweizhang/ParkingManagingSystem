package ParkingManagingSystem;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-12-2
 * Time: 下午3:50
 * To change this template use File | Settings | File Templates.
 */
public class ParkingBoy {
    List<ParkingLot> parkingLotList;
    ParkingLotChooser parkingLotChooser;

    public ParkingBoy(List<ParkingLot> parkingLots, ParkingLotChooser parkingLotChooser) {
        parkingLotList = parkingLots;
        this.parkingLotChooser = parkingLotChooser;
    }
}
