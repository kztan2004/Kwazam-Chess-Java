import java.util.*;

public class Controller {
    private Model model;
    private View view;
    
    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
        this.view.connectModel(model);
    }
    
    public void viewing(){
        this.view.viewing();
    }
}