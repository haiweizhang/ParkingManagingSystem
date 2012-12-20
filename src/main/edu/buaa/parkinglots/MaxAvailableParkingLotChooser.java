package edu.buaa.parkinglots;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-12-2
 * Time: 下午3:53
 * To change this template use File | Settings | File Templates.
 */
public class MaxAvailableParkingLotChooser implements ParkingLotChooser {
    @Override
    public ParkingLot getAvailableParkLot(Vector<ParkingLot> parkingLotVector) {
        int maxIdx = 0, maxAvailable = 0;
        for(int i=0; i<parkingLotVector.size(); i++) {
            if(parkingLotVector.get(i).availableParkingSpace() > maxAvailable) {
                maxIdx = i;
                maxAvailable = parkingLotVector.get(i).availableParkingSpace();
            }
        }

        if(maxAvailable == 0){
            return null;
        } else {
            return parkingLotVector.get(maxIdx);
        }
    }
}
