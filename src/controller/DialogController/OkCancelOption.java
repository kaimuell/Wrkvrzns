package controller.DialogController;

public enum OkCancelOption {
    OK(0), CANCEL(1), NOTDECIDED(-1);

    int value;

    OkCancelOption(int value){
        this.value = value;
    }

    int getIntValue(){
        return this.value;
    }

}
