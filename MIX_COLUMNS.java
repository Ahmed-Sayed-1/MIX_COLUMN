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

    static String ToBinary(String HEX_Num) {
        String bin_num = "";
        String Temp;
        int Value;
        String zeros;

        for (int i = 0; i < HEX_Num.length(); i++) {
            Value = Integer.parseInt(HEX_Num.substring(i, i + 1), 16);
            Temp = Integer.toBinaryString(Value);
            zeros = "";
            while (Temp.length() != 4) {
                zeros += "0";
                if (Temp.length() + zeros.length() == 4) {
                    Temp = zeros + Temp;
                }
            }
            bin_num += Temp;

        }
        return bin_num;

    }

    static String XOR(String text1, String text2) {
        String result = "";

        for (int i = 0; i < text2.length(); i++) {
            if (text2.charAt(i) == text1.charAt(i)) {
                result += "0";
            } else {
                result += "1";
            }
        }

        return result;

    }

    static String ToHex(String bin_num) {
        String Hex_Num = "";
        for (int i = 0; i < bin_num.length() / 4; i++) {
            Hex_Num += Integer.toHexString(Integer.parseInt(bin_num.substring(i * 4, i * 4 + 4), 2));
        }

        return Hex_Num;

    }
    //multiply two binary numper in FINITE FIELDS  GF(2^8)

    static String mul(String g, String f) {
        String temp = f;
        String arr[] = new String[8];
        String mod = "00011011";//x^8+x^4+x^3+x+1
        arr[0] = f;
        for (int i = 1; i < 8; i++) {

            if (temp.charAt(0) == '0') {
                temp = temp.substring(1) + "0";
                arr[i] = temp;
            } else {
                temp = temp.substring(1) + "0";
                temp = XOR(temp, mod);
                arr[i] = temp;
            }
        }

        temp = "00000000";
        for (int i = 0; i < 8; i++) {
            if (g.charAt(7 - i) == '1') {
                temp = XOR(temp, arr[i]);
            }
        }

        return temp;

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
