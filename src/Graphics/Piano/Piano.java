package Graphics.Piano;

import java.util.LinkedList;

import Graphics.PianoProject;
import Graphics.Controll.ID;

public class Piano {
    private LinkedList<Key> keys = new LinkedList<Key>();
    private LinkedList<SharpKey> sharpKeys = new LinkedList<SharpKey>();

    public Piano(PianoProject pp)
    {
        //sharpKeys.add(new SharpKey(this.getWidth()*count/52, this.getHeight()*4/5, ID.a0,i,count));
        //keys.add(new Key(this.getWidth()*(i-count)/52, this.getHeight()*4/5, ID.a0,i,(i-count)));
        int width = (int)(pp.getWidth() *29/30.0);
        keys.add(new Key(width*0/52, pp.getHeight()*4/5, ID.a0,             0,0));
        sharpKeys.add(new SharpKey(width*0/52, pp.getHeight()*4/5, ID.as0,  1,0));
        keys.add(new Key(width*1/52, pp.getHeight()*4/5, ID.b0,             2,1));

        keys.add(new Key(width*2/52, pp.getHeight()*4/5, ID.c1,             3,2));
        sharpKeys.add(new SharpKey(width*1/52, pp.getHeight()*4/5, ID.cs1,  4,1));
        keys.add(new Key(width*3/52, pp.getHeight()*4/5, ID.d1,             5,3));
        sharpKeys.add(new SharpKey(width*2/52, pp.getHeight()*4/5, ID.ds1,  6,2));
        keys.add(new Key(width*4/52, pp.getHeight()*4/5, ID.e1,             7,4));
        keys.add(new Key(width*5/52, pp.getHeight()*4/5, ID.f1,             8,5));
        sharpKeys.add(new SharpKey(width*3/52, pp.getHeight()*4/5, ID.fs1,  9,3));
        keys.add(new Key(width*6/52, pp.getHeight()*4/5, ID.g1,             10,6));
        sharpKeys.add(new SharpKey(width*4/52, pp.getHeight()*4/5, ID.gs1,  11,4));
        keys.add(new Key(width*7/52, pp.getHeight()*4/5, ID.a1,             12,7));
        sharpKeys.add(new SharpKey(width*3/52, pp.getHeight()*4/5, ID.as1,  13,5));
        keys.add(new Key(width*8/52, pp.getHeight()*4/5, ID.b1,             14,8));

        keys.add(new Key(width*9/52, pp.getHeight()*4/5, ID.c2,             15,9));
        sharpKeys.add(new SharpKey(width*1/52, pp.getHeight()*4/5, ID.cs2,  16,6));
        keys.add(new Key(width*10/52, pp.getHeight()*4/5, ID.d2,             17,10));
        sharpKeys.add(new SharpKey(width*2/52, pp.getHeight()*4/5, ID.ds2,  18,7));
        keys.add(new Key(width*11/52, pp.getHeight()*4/5, ID.e2,             19,11));
        keys.add(new Key(width*12/52, pp.getHeight()*4/5, ID.f2,             20,12));
        sharpKeys.add(new SharpKey(width*3/52, pp.getHeight()*4/5, ID.fs2,  21,8));
        keys.add(new Key(width*13/52, pp.getHeight()*4/5, ID.g2,             22,13));
        sharpKeys.add(new SharpKey(width*4/52, pp.getHeight()*4/5, ID.gs2,  23,9));
        keys.add(new Key(width*14/52, pp.getHeight()*4/5, ID.a2,             24,14));
        sharpKeys.add(new SharpKey(width*3/52, pp.getHeight()*4/5, ID.as2,  25,10));
        keys.add(new Key(width*15/52, pp.getHeight()*4/5, ID.b2,             26,15));

        keys.add(new Key(width*16/52, pp.getHeight()*4/5, ID.c3,             27,16));
        sharpKeys.add(new SharpKey(width*1/52, pp.getHeight()*4/5, ID.cs3,  28,11));
        keys.add(new Key(width*17/52, pp.getHeight()*4/5, ID.d3,             29,17));
        sharpKeys.add(new SharpKey(width*2/52, pp.getHeight()*4/5, ID.ds3,  30,12));
        keys.add(new Key(width*18/52, pp.getHeight()*4/5, ID.e3,             31,18));
        keys.add(new Key(width*19/52, pp.getHeight()*4/5, ID.f3,             32,19));
        sharpKeys.add(new SharpKey(width*3/52, pp.getHeight()*4/5, ID.fs3,  33,13));
        keys.add(new Key(width*20/52, pp.getHeight()*4/5, ID.g3,             34,20));
        sharpKeys.add(new SharpKey(width*4/52, pp.getHeight()*4/5, ID.gs3,  35,14));
        keys.add(new Key(width*21/52, pp.getHeight()*4/5, ID.a3,             36,21));
        sharpKeys.add(new SharpKey(width*3/52, pp.getHeight()*4/5, ID.as3,  37,15));
        keys.add(new Key(width*22/52, pp.getHeight()*4/5, ID.b3,             38,22));

        keys.add(new Key(width*23/52, pp.getHeight()*4/5, ID.c4,             39,23));
        sharpKeys.add(new SharpKey(width*1/52, pp.getHeight()*4/5, ID.cs4,  40,16));
        keys.add(new Key(width*24/52, pp.getHeight()*4/5, ID.d4,             41,24));
        sharpKeys.add(new SharpKey(width*2/52, pp.getHeight()*4/5, ID.ds4,  42,17));
        keys.add(new Key(width*25/52, pp.getHeight()*4/5, ID.e4,             43,25));
        keys.add(new Key(width*26/52, pp.getHeight()*4/5, ID.f4,             44,26));
        sharpKeys.add(new SharpKey(width*3/52, pp.getHeight()*4/5, ID.fs4,  45,18));
        keys.add(new Key(width*27/52, pp.getHeight()*4/5, ID.g4,             46,27));
        sharpKeys.add(new SharpKey(width*4/52, pp.getHeight()*4/5, ID.gs4,  47,19));
        keys.add(new Key(width*28/52, pp.getHeight()*4/5, ID.a4,             48,28));
        sharpKeys.add(new SharpKey(width*3/52, pp.getHeight()*4/5, ID.as4,  49,20));
        keys.add(new Key(width*29/52, pp.getHeight()*4/5, ID.b4,             50,29));

        keys.add(new Key(width*30/52, pp.getHeight()*4/5, ID.c5,             51,30));
        sharpKeys.add(new SharpKey(width*1/52, pp.getHeight()*4/5, ID.cs5,  52,21));
        keys.add(new Key(width*31/52, pp.getHeight()*4/5, ID.d5,             53,31));
        sharpKeys.add(new SharpKey(width*2/52, pp.getHeight()*4/5, ID.ds5,  54,22));
        keys.add(new Key(width*32/52, pp.getHeight()*4/5, ID.e5,             55,32));
        keys.add(new Key(width*33/52, pp.getHeight()*4/5, ID.f5,             56,33));
        sharpKeys.add(new SharpKey(width*3/52, pp.getHeight()*4/5, ID.fs5,  57,23));
        keys.add(new Key(width*34/52, pp.getHeight()*4/5, ID.g5,             58,34));
        sharpKeys.add(new SharpKey(width*4/52, pp.getHeight()*4/5, ID.gs5,  59,24));
        keys.add(new Key(width*35/52, pp.getHeight()*4/5, ID.a5,             60,35));
        sharpKeys.add(new SharpKey(width*3/52, pp.getHeight()*4/5, ID.as5,  61,25));
        keys.add(new Key(width*36/52, pp.getHeight()*4/5, ID.b5,             62,36));

        keys.add(new Key(width*37/52, pp.getHeight()*4/5, ID.c6,             63,37));
        sharpKeys.add(new SharpKey(width*1/52, pp.getHeight()*4/5, ID.cs6,  64,26));
        keys.add(new Key(width*38/52, pp.getHeight()*4/5, ID.d6,             65,38));
        sharpKeys.add(new SharpKey(width*2/52, pp.getHeight()*4/5, ID.ds6,  66,27));
        keys.add(new Key(width*39/52, pp.getHeight()*4/5, ID.e6,             67,39));
        keys.add(new Key(width*40/52, pp.getHeight()*4/5, ID.f6,             68,40));
        sharpKeys.add(new SharpKey(width*3/52, pp.getHeight()*4/5, ID.fs6,  69,28));
        keys.add(new Key(width*41/52, pp.getHeight()*4/5, ID.g6,             70,41));
        sharpKeys.add(new SharpKey(width*4/52, pp.getHeight()*4/5, ID.gs6,  71,29));
        keys.add(new Key(width*42/52, pp.getHeight()*4/5, ID.a6,             72,42));
        sharpKeys.add(new SharpKey(width*3/52, pp.getHeight()*4/5, ID.as6,  73,30));
        keys.add(new Key(width*43/52, pp.getHeight()*4/5, ID.b6,             74,43));

        keys.add(new Key(width*44/52, pp.getHeight()*4/5, ID.c7,             75,44));
        sharpKeys.add(new SharpKey(width*1/52, pp.getHeight()*4/5, ID.cs7,  76,31));
        keys.add(new Key(width*45/52, pp.getHeight()*4/5, ID.d7,             77,45));
        sharpKeys.add(new SharpKey(width*2/52, pp.getHeight()*4/5, ID.ds7,  78,32));
        keys.add(new Key(width*46/52, pp.getHeight()*4/5, ID.e7,             79,46));
        keys.add(new Key(width*47/52, pp.getHeight()*4/5, ID.f7,             80,47));
        sharpKeys.add(new SharpKey(width*3/52, pp.getHeight()*4/5, ID.fs7,  81,33));
        keys.add(new Key(width*48/52, pp.getHeight()*4/5, ID.g7,             82,48));
        sharpKeys.add(new SharpKey(width*4/52, pp.getHeight()*4/5, ID.gs7,  83,34));
        keys.add(new Key(width*49/52, pp.getHeight()*4/5, ID.a7,             84,49));
        sharpKeys.add(new SharpKey(width*3/52, pp.getHeight()*4/5, ID.as7,  85,35));
        keys.add(new Key(width*50/52, pp.getHeight()*4/5, ID.b7,             86,50));

        keys.add(new Key(width*51/52, pp.getHeight()*4/5, ID.c8,             87,51));
    }
    public LinkedList<Key> getKeys()
    {
        return keys;
    }
    public LinkedList<SharpKey> getSharpKeys()
    {
        return sharpKeys;
    }
}
