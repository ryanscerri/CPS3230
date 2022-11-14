package Task1.Spy;

import MarketAlertUmAssignment.Interfaces.CheckTimeWebsiteWasOpenedController;

public class TimeWebsiteIsOpenedControllerSpy implements CheckTimeWebsiteWasOpenedController {

    int numTimesPageOpened = 0;

    public int CheckIfWebsiteWasOpened()
    {

        numTimesPageOpened++;
        return numTimesPageOpened;
    }
}
