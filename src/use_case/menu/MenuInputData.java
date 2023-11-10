package use_case.menu;

public class MenuInputData {

    //Variable Declarations:
    final private String buttonName;

    //Constructor:
    public MenuInputData(String buttonName) {
        this.buttonName = buttonName;
    }

    //Getter Methods:
    String getButtonName() {
        return buttonName;
    }
}
