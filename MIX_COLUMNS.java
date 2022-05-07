/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mix_columns;

/**
 *
 * @author ahmed
 */
public class MIX_COLUMNS {

   static String[][] MIX_Matrix = {{"02", "03", "01", "01"},
    {"01", "02", "03", "01"},
    {"01", "01", "02", "03"},
    {"03", "01", "01", "02"}};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[][]s={{"ab","8b","89","35"},
                     {"40","7f","f1","05"}
                    ,{"f0","fc","18","3f"}
                    ,{"c4","e4","4e","2f"}};
        String[][]s1=mix_column(s);
       for (String[] s11 : s1) {
           for (String item : s11) {
               System.out.print(item + " ");
           }
           System.out.println("");
       }
        
    }

    static String ToBinary(String Key) {
        String Key_bin = "";
        String Temp;
        int Value;
        String zeros;

        for (int i = 0; i < Key.length(); i++) {
            Value = Integer.parseInt(Key.substring(i, i + 1), 16);
            Temp = Integer.toBinaryString(Value);
            zeros = "";
            while (Temp.length() != 4) {
                zeros += "0";
                if (Temp.length() + zeros.length() == 4) {
                    Temp = zeros + Temp;
                }
            }
            Key_bin += Temp;

        }
        return Key_bin;

    }

    static String XOR(String text1, String text2) {
        String Permutation = "";

        for (int i = 0; i < text2.length(); i++) {
            if (text2.charAt(i) == text1.charAt(i)) {
                Permutation += "0";
            } else {
                Permutation += "1";
            }
        }

        return Permutation;

    }

    static String ToHex(String bin_KEY) {
        String HexKEY = "";
        for (int i = 0; i < bin_KEY.length() / 4; i++) {
            HexKEY += Integer.toHexString(Integer.parseInt(bin_KEY.substring(i * 4, i * 4 + 4), 2));
        }

        return HexKEY;

    }
    //multiply two binary numper in FINITE FIELDS  GF(2^8)

    static String mul(String g, String f) {
        String HexKEY = f;
        String arr[] = new String[8];
        String mod = "00011011";
        arr[0] = f;
        for (int i = 1; i < 8; i++) {

            if (HexKEY.charAt(0) == '0') {
                HexKEY = HexKEY.substring(1) + "0";
                arr[i] = HexKEY;
            } else {
                HexKEY = HexKEY.substring(1) + "0";
                HexKEY = XOR(HexKEY, mod);
                arr[i] = HexKEY;
            }
        }

        HexKEY = "00000000";
        for (int i = 0; i < 8; i++) {
            if (g.charAt(7 - i) == '1') {
                HexKEY = XOR(HexKEY, arr[i]);
            }
        }

        return HexKEY;

    }

    static String[][] mix_column(String g[][]) {
        String c[][] = new String[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                c[i][j] = "00000000";
                for (int k = 0; k < 4; k++) {

                    c[i][j] = XOR(c[i][j], mul(ToBinary(MIX_Matrix[i][k]), ToBinary(g[k][j])));
                }
                c[i][j] = ToHex(c[i][j]);

            }

        }
        return c;

    }

}
