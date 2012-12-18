package Main;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-12-2
 * Time: 下午3:45
 * To change this template use File | Settings | File Templates.
 */
public interface ParkingLotChooser {
    ParkingLot getAvailableParkLot(Vector<ParkingLot> parkingLotVector);
}
