
package Spectra;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.JFileChooser;


public class RAT
{
    private int row=0,col=0;
    private double rawData[][];
    public List<Double> Data;
    private String filePath="";


    private int getColoumn()
    {
        return col;
    }
    private int getRow()
    {
        return row;
    }
    private String getFilePath()
    {
        return filePath;
    }
    private double[][] getRawData()
    {
        return rawData;
    }
    public List<Double> getData()
    {
        return Data;
    }

    public void setDataPath()
    {
        JButton open=new JButton();
        JFileChooser fc=new JFileChooser();
        fc.setCurrentDirectory(new java.io.File(""));
        fc.setDialogTitle("Select only a (*.txt) File");
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if(fc.showOpenDialog(open)==JFileChooser.APPROVE_OPTION)
        {
            //
        }
        String fp=fc.getSelectedFile().getAbsolutePath();

        System.out.println("You have choosen: "+fp);
        filePath=fp;
//        setRowCol();
    }
    public void setRowCol()//sets row, coloumn and instantiates rawData
    {
        String s;
        StringTokenizer st;
        try
        {

            FileReader fr = new FileReader(getFilePath());
            BufferedReader br = new BufferedReader(fr);
            while((s = br.readLine()) != null)
            {
                if(row==0)
                {
                    st=new StringTokenizer(s);
                    col=st.countTokens();
                }
                row++;
            }
            br.close();
            fr.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        rawData=new double[row][col];
        System.out.println("check 1\t"+row+"\t"+col);
    }
    public void setData() //throws IOException
    {
        StringTokenizer st;
        int R=0;
        int C=0;
        try
        {
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        String s;

        while((s=br.readLine())!= null && R<row)
        {
            st=new StringTokenizer(s);
            double sum=0;
            for(C=0;C<col;C++)
            {
                try
                {
                    String temp=st.nextToken();
                    System.out.print("    :"+temp+":");
                    rawData[R][C]=Double.parseDouble(temp);
                }
                catch(Exception e)
                {
                    System.out.print("Null Pointer"+e);
                }
                sum+=rawData[R][C];
            }
            System.out.println("");
            rawData[R][0]=sum;
            R++;
        }
        br.close();
        fr.close();
        }
        catch(Exception e)
        {
            System.out.println("IOE"+e);
        }
        Data=trimmer(rawData);
    }
    public List<Double> trimmer(double[][] x)
    {
        Data=new ArrayList();
        row=2;
        int len=x.length;
        while(row<len)
        {
            row*=2;
        }
        //row/=2;
        //Data=new double[row];

        for(int i=0;i<row;i++)
        {
            //scores.add((double) random.nextDouble() * maxScore);
            if(i>=len)Data.add(0.0);
            else      Data.add((double)x[i][0]);//[i]=x[i][1];
        }
        System.out.print("\t\t"+Data);
        return Data;
    }
}
