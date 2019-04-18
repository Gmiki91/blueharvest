package blueharvest.action;

public class ActionResult {
    private String act;

    public ActionResult(String status) {
        this.act = status;
    }

    public int receivedFood(){
        if (act.equals("Vadászat")){
            return 2;
        }
        return 1;
    }
    public int receivedMoney(){
        if (act.equals("Vadászat")){
            return 1;
        }
        return 0;
    }
}
