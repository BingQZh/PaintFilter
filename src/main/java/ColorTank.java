
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ColorTank {
    private int count=0;
    private List<List<Integer>> listcolor =new ArrayList<List<Integer>>();

    ColorTank(int count){
        this.count=count;
        for(int i=0;i<=this.count;i++){
            listcolor.add(new ArrayList<Integer>());
        }
    }

    //apply the RGB data
    void tankInsert(int rgb){
        float gray=Demo.rgb2gray(rgb);
        int index=(int) ((gray/255)*count);
        listcolor.get(index).add(rgb);
    }

    //retrieve RGB
    int getColor(){
        int max=0;
        int size = 0;
        for (int i = 0; i < listcolor.size(); i++) {
            List<Integer> temp= listcolor.get(i);
            if (temp.size()>size) {
                size=temp.size();
                max=i;
            }

        }
        List<Integer> RGBlist= listcolor.get(max);
        int colorR=0;
        int colorG=0;
        int colorB=0;
        for (Integer integer : RGBlist) {
            Color c=new Color(integer);
            colorR+=c.getRed();
            colorG+=c.getGreen();
            colorB+=c.getBlue();
        }
        colorR=colorR/RGBlist.size();
        colorG=colorG/RGBlist.size();
        colorB=colorB/RGBlist.size();
        return new Color(colorR, colorG, colorB).getRGB();

    }
}