public class MAIN{
    Model model = new Model();
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
        controller.viewing();
    }
}
