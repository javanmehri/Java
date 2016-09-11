package mos;

/**
 * Created by MOS on 2016-07-15.
 */
public class Report implements java.io.Serializable{

    public static void report(Object object) {
        System.out.println(object);
    }

    public static void report(Spot[] spots) {
        report("  report: ");

        for (int i = 0; i < spots.length; i++) {
            System.out.print("  sopts[" + i + "] = " + spots[i]);
        }
    }

    public static void report(int from_i, int from_j, int to_i, int to_j) {
        report("  report: ");
        report("  from_i = " + from_i + " |  from_j = " + from_j + " || to_i = " + to_i + " | to_j = " + to_j);
    }


    public static void open_report(int level, String str) {
        String gap = shift(level);
        System.out.print(gap + str + ":");
        System.out.print("\n" + gap + "{"+"\n");
    }


    public static void close_report(int level) {
        String gap = shift(level);
        System.out.print(gap + "}"+"\n");

        if (level == 1)
            System.out.print("--------------------------------------------------------------------------------"+"\n");
        else
            System.out.print("\n");

    }


    public static void report(int level, String str)
    {
        String gap = shift(level+1);
        System.out.print(gap + " > "+str+"\n");
    }

    private static String shift(int level)
    {
        String gap = "";

        switch (level)
        {
            case 1: break;
            case 2: gap = "      "; break;
            case 3: gap = "            "; break;
            case 4: gap = "                  "; break;
            case 5: gap = "                        "; break;
            case 6: gap = "                              "; break;
        }
        return gap;
    }


}


