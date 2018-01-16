package Spectra;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
/**
 *
 * @author Caleb Jebadurai
 */
public class Spectra
{

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args)
    {
        //getting data
        RAT rat=new RAT();
        rat.setDataPath();
        rat.setRowCol();
        rat.setData();
        //normallizing the raw data
        List<Double> D=new ArrayList();
        D=rat.getData();
        Double[] data=D.toArray(new Double[D.size()]);
        //graphing normal data
        Grapher2 GD=new Grapher2(D);
        GD.abc();

        //generating FFT of the normal data
        List<Double> FFTD=new ArrayList();
        FFTbase ftb=new FFTbase();
        FFTD=ftb.fft(data, data, true);
        //graphing FFT data
        Grapher2 GFFTD=new Grapher2(FFTD);
        GFFTD.abc();

        //filtering
        Double[] filterData= FFTD.toArray(new Double[FFTD.size()]);
        List<Double> filteredData=new ArrayList();
        //Low Pass Filter
        filterData=ftb.lowPassFilter(99, 7000, filterData);
        //High Pass Fillter
        filterData=ftb.highPassFilter(99, 8000, filterData);
        filteredData=ftb.toList(filterData);

        //graphing filtered data
        Grapher2 Filtered=new Grapher2(filteredData);
        Filtered.abc();

        //generating IFFT of the filtered data
        List<Double> IFFTD=new ArrayList();
        IFFTD=ftb.fft(filterData, filterData, false);

        //graphing IFFT filtered data
        Grapher2 IFFTFiltered=new Grapher2(IFFTD);
        IFFTFiltered.abc();



//        List<Double> D2=new ArrayList();
//        D2=FFTD;
//        Double[] newFFTD=D2.toArray(new Double[D2.size()]);
//
//        List<Double> IFFTD=new ArrayList();
//        IFFTD=ftb.fft(newFFTD, newFFTD, false);
//
//        Grapher2 GIFFTD=new Grapher2(IFFTD);
//        GIFFTD.abc();






//        Grapher gp=new Grapher("Raw data");
//        gp.setData(rat.getData());
//        gp.graphIt();
//





//        Data D=new Data();
//        D.printData();
//        Grapher Gp=new Grapher("raw data");
//
//        try
//        {
//            double[] dd=new Data().getData();
//            Gp.setData(dd);
//        }
//        catch(Exception e)
//        {
//            System.out.println(e);
//        }
//        Gp.graphIt();
    }

}
