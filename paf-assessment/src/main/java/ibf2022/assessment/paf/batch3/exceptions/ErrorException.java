package ibf2022.assessment.paf.batch3.exceptions;

import ibf2022.assessment.paf.batch3.models.Beer;

public class ErrorException extends Exception{

    private Beer beerInfo;

    public ErrorException(){
        super();
    }

    public ErrorException (String msg){
        super(msg);
    }

    public Beer getBeerInfo() {
        return beerInfo;
    }

    public void setBeerInfo(Beer beerInfo) {
        this.beerInfo = beerInfo;
    }

    
    
}
