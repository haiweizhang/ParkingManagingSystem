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
    public ParkingLot getAvailableParkLot(Vector<ParkingLot> parkingLotVector)
    {
        if(parkingLotVector.size() == 0)
            return null;

        ParkingLot highVacancyRateParkingLot = parkingLotVector.get(0);
        float vacancyRate = highVacancyRateParkingLot.vacancyRate();
        for(int i = 1; i < parkingLotVector.size(); i++)
        {
            ParkingLot tempLot = parkingLotVector.get(i);
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
