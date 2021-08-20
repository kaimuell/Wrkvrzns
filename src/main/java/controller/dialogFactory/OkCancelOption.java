package controller.dialogFactory;

public enum OkCancelOption {
    OK(0), CANCEL(1), UNDECIDED(-1);

    int value;

    OkCancelOption(int value){
        this.value = value;
    }

    int getIntValue(){
        return this.value;
    }

}
