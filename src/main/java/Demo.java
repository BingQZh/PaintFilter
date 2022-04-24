
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class Demo {


    /************************************************************************************/
    /******** For Program efficiency, please use image size less than 1000*10000 ********/
    /************************************************************************************/

    //image loading (use bufferedImage)
    public static BufferedImage readImage(String imageName) {
        File imageFile = new File(imageName);
        BufferedImage bufferedImage=null;
        try {
            bufferedImage = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedImage;

    }
    public static void writeImage(BufferedImage bi, String imageName) {
        File skinImageOut = new File(imageName);
        try {
            ImageIO.write(bi, "png", skinImageOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //function that turn image to gray scale
    public static int rgb2gray(int rgb) {
        int r = (rgb & 0xff0000) >> 16;
        int g = (rgb & 0xff00) >> 8;
        int b = (rgb & 0xff);
        int gray = (int)(r * 0.3 + g * 0.59 + b * 0.11);
        return gray;
    }

    public static void main(String[] args) {
        //filter data
        int radius =0;
        int tank=0;
        //output image path
        String savePath="D:\\CMPT461\\project\\tests\\output\\";
        //input image path
        Scanner userInput= new Scanner(System.in);
        String name = null;
        String type = null;
        String path = null;
        String imgtype=null;
        String filter=null;
        String ans=null;
        String ans1=null;

        System.out.println("\n\nplease enter your file name: \n");
        if (userInput.hasNextLine()){
            name=userInput.nextLine();
        }
        System.out.println("\n\nIs it portrait or landscape? (Answer p / l)\n ");
        if (userInput.hasNextLine()){
            imgtype=userInput.nextLine();
            //System.out.println(imgtype);
        }
        if(imgtype.equals("p")){
            filter="Chiaroscuro";
            System.out.println();
            System.out.println("\n\nReading image from path...\n");
            System.out.println("...\n");
            System.out.println();
            System.out.println("\nChiaroscuro painting style have been applied.");
            radius=4;//2
            tank=7;//5
        }
        else {
            System.out.println();
            System.out.println("\n\nAre there a lots of objects in the frame? (y/n)\n");
            if (userInput.hasNextLine()){
                ans=userInput.nextLine();
                if(ans.equals("y")){
                    System.out.println();
                    System.out.println("\n\nIt is colorful? (y/n)\n");
                    if (userInput.hasNextLine()){
                        ans1=userInput.nextLine();
                        if(ans1.equals("y")){
                            filter="Gouache Painting";
                            System.out.println();
                            System.out.println("\n\nReading image from path...");
                            System.out.println("\n...");
                            System.out.println();
                            System.out.println("\n\nGouache painting style have been applied.");
                            radius=6;//5
                            tank=15;//15
                        }
                        else{
                            filter="Landscape Painting";
                            System.out.println();
                            System.out.println("\n\nReading image from path...");
                            System.out.println("\n...");
                            System.out.println();
                            System.out.println("\n\nLandscape painting style have been applied.");
                            radius=4;
                            tank=4;
                        }


                    }

                }
                else{
                    filter="Pastel Painting";
                    System.out.println();
                    System.out.println("\n\nReading image from path...");
                    System.out.println("\n...");
                    System.out.println();
                    System.out.println("\n\nPastel painting style have been applied.");
                    radius=3;
                    tank=10;
                }


            }
        }

        path="D:\\CMPT461\\project\\tests\\input\\"+name;
        System.out.println();
        System.out.println();
        System.out.println(path);
        String openPath=path;
        userInput.close();
        //load in the original image
        BufferedImage colorImage=readImage(openPath);
        //get the image data
        int width=colorImage.getWidth();
        int height=colorImage.getHeight();
        //applying filter
        BufferedImage outRes = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 1; y < height - 1; ++y){
            for (int x = 1; x < width - 1; ++x){
                ColorTank ctank=new ColorTank(tank);
                for (int tx = x-radius; tx < x+radius; tx++) {
                    for (int ty = y-radius; ty < y+radius; ty++) {
                        if (tx>0 && tx<width && ty>0 && ty<height) {
                            ctank.tankInsert(colorImage.getRGB(tx, ty));
                        }
                    }
                }
                outRes.setRGB(x, y,ctank.getColor());
            }
        }
        String filename=null;
        filename="R"+radius+"T"+tank;
        writeImage(outRes, savePath+filename+".jpg");
        System.out.println();
        System.out.println();
        System.out.println("\n*******success*******\n\n");
    }

}