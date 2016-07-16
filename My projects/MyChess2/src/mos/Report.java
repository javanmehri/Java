package mos;

/**
 * Created by MOS on 2016-07-15.
 */
public class Report {

    public static void report(Object object)
    {
        System.out.println(object);
    }

    public static void report(Spot[] spots)
    {
        report("  report: ");

        for (int i=0; i<spots.length; i++)
        {
            System.out.print("  sopts["+i+"] = "+spots[i]);
        }
    }

    public static void report(int from_i, int from_j, int to_i, int to_j)
    {
        report("  report: ");
        report("  from_i = "+ from_i+" |  from_j = "+from_j+" || to_i = "+to_i+" | to_j = "+to_j);
    }

}
