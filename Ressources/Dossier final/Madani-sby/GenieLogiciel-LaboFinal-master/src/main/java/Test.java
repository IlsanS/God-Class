/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sebastien
 */
public class Test
{
    private int x;
    private String y;
    
    public void test1()
    {
        int i = 0;
        int R = (i == 1) ? 3 : 2;
        String z;
        Test2 test2 = new Test2();
        z = test2.getUn();
        z = test2.un;
        switch(i)
        {
            case 1:
                for(int j = 0; i< 10 && true; i++)
                {
                    i = x;
                }
                break;
            case 2:
                int k = 5;
                do{
                    
                }while(k < 10 || false);
                break;
            default:                    
                break;
        }
        R = 5;
    }
    
    public void test2()
    {
        try
        {
            if (true && false)
            {
                if (true)
                {
                    x = 9;
                }
            }
            //else if (false)
                //y = "aaa";
            else    
            {
                 
            }
        }
        catch (Exception ex)
        {
           
        }
    }    
    
    public void test3()
    {
        int z;
        y = "aaa";
        z=x;
    }
    
    public void test4()
    {
        String te;
        te = y;
        //x = 9;
        
    }
}
