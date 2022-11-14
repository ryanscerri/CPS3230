package Task1.Spy;

import MarketAlertUmAssignment.Interfaces.CheckTimePostRequestWasDone;

public class TimePostReqDoneControllerSpy implements CheckTimePostRequestWasDone {


     int numTimePostReqDone = 0;

    public int MakePostRequest() {
        numTimePostReqDone++;
        return numTimePostReqDone;
    }
}
