package Mangement_Style;
import java.awt.Font;
public class style {
    public  Font title;
    public  Font account;
    public  Font prompt;
    public  Font button;
    public style(){
        title = new Font("Algerian",Font.BOLD,30);
        account = new Font(Font.DIALOG_INPUT,Font.BOLD,18);
        prompt = new Font(Font.DIALOG,Font.BOLD,20);
        button = new Font(Font.SANS_SERIF,Font.BOLD,18);
    }
    
}
