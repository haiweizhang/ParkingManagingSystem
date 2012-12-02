package ParkingManagingSystem;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-12-2
 * Time: 下午3:52
 * To change this template use File | Settings | File Templates.
 */
public class FirstAvailableParkingLotChooser implements ParkingLotChooser {
    @Override
    public ParkingLot getAvailableParkLot(List<ParkingLot> parkingLotList) {
        for(ParkingLot pLot : parkingLotList){
            if(pLot.availableParkingSpace() > 0)
                return pLot;
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
