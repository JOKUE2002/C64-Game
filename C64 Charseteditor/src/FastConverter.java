import javax.swing.*;

public class FastConverter {

    public static void main(String[] args) {
        String in = JOptionPane.showInputDialog("Startaddress");
        int start = Integer.valueOf(in);
        String out = "";

        int cnt = 0;
        while(true) {
            for (int i = 0; i < 8; i++) {
                try {
                    int nr = Integer.valueOf(JOptionPane.showInputDialog("Number please"));

                    if(nr < 0 || nr > 255){
                        i--;
                    }else{
                        out += "rom[0x" + Integer.toHexString(start + i + cnt * 8) + "] = 0x" + Integer.toHexString(nr) + "\n";
                    }
                } catch (Exception e) {
                    i--;
                }
            }

            cnt++;

            int c = JOptionPane.showConfirmDialog(null, "Is this the last one?");

            if(c == JOptionPane.YES_OPTION){
                System.out.println(out);
            }
        }

    }

}
