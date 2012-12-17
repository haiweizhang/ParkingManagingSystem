package ParkingManagingSystem;

import java.util.List;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-12-16
 * Time: 下午2:47
 * To change this template use File | Settings | File Templates.
 */
public class HighVacancyRateParkingLotChooser implements ParkingLotChooser {
    @Override
    public ParkingLot getAvailableParkLot(Vector<ParkingLot> parkingLotList)
    {
        if(parkingLotList.size() == 0)
            return null;

        ParkingLot highVacancyRateParkingLot = parkingLotList.get(0);
        float vacancyRate = highVacancyRateParkingLot.vacancyRate();
        for(int i = 1; i < parkingLotList.size(); i++)
        {
            ParkingLot tempLot = parkingLotList.get(i);
            float  tempRate = tempLot.vacancyRate();
            if(tempRate > vacancyRate)
            {
                highVacancyRateParkingLot = tempLot;
                vacancyRate = tempRate;
            }
        }
       return  highVacancyRateParkingLot;
    }
}
