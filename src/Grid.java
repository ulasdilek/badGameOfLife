import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.*;
import javax.swing.*;

public class Grid extends JLabel {

    private final int SCALE = 10;
    private final double LIFE_TRESHOLD = 0.7;
    private final int DEAD = 0xFFFFFF, ALIVE = 0x000000;

    private boolean initial;
    private BufferedImage bimage, backup;
    private ImageIcon icon;
    private int[] pixels, next;
    private int gridHeight, gridWidth;

    public Grid (int gridWidth, int gridHeight)
    {
        initial = true;
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;
        generate();
        addMouseListener(new GridMouseListener());
    }

    public boolean getInıtıal ()
    {
        return initial;
    }

    public int getGridHeight ()
    {
        return gridHeight;
    }

    public int getGridWidth ()
    {
        return gridWidth;
    }

    public void setGridHeight (int gridHeight)
    {
        this.gridHeight = gridHeight;
    }

    public void setGridWidth (int gridWidth)
    {
        this.gridWidth = gridWidth;
    }

    public void generate ()
    {
        bimage = new BufferedImage(gridWidth, gridHeight, BufferedImage.TYPE_INT_RGB);
        backup = new BufferedImage(gridWidth, gridHeight, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) bimage.getRaster().getDataBuffer()).getData();
        for (int i = 0; i < pixels.length; i++) {
            paint(i, DEAD);
        }
        renewIcon();
        initial = true;
    }

    //TODO this is not yet implemented
    public void proceed ()
    {
        if (initial) {
            backup = new BufferedImage(bimage.getColorModel(), bimage.copyData(null), bimage.getColorModel().isAlphaPremultiplied(), null);
        }
        next = pixels.clone();
        for (int y = 0; y < gridHeight; y++) {
            for (int x = 0; x < gridWidth; x++) {
                int sum = checkNeighbours(x, y);
                if (pixels[x + y * gridWidth] == ALIVE && (sum < 2 || sum > 3)) {
                    change(next, x, y);
                } else if (pixels[x + y * gridWidth] == DEAD && sum == 3) {
                    change(next, x, y);
                }
            }
        }
        for (int index = 0; index < pixels.length; index++) {
            pixels[index] = next[index];
        }
        renewIcon();
        initial = false;
    }

    public void randomise ()
    {
        for (int i = 0; i < pixels.length; i++) {
            double d = Math.random();
            if (d > LIFE_TRESHOLD) {
                paint(i, ALIVE);
            }
        }
        renewIcon();
        initial = true;
    }

    public void reset ()
    {
        bimage = backup;
        pixels = ((DataBufferInt) bimage.getRaster().getDataBuffer()).getData();
        renewIcon();
        initial = true;
    }

    //TODO this is not yet fully implemented, requires thread knowledge
    public void play () {
        if (initial) {
            backup = new BufferedImage(bimage.getColorModel(), bimage.copyData(null), bimage.getColorModel().isAlphaPremultiplied(), null);
        }
        // do stuff
    }

    //TODO see play ()
    public void pause () {

    }

    //a placeholder method for practising BufferedImage
    public void test ()
    {
        for (int y = 0; y < gridHeight; y++) {
            for (int x = 0; x < gridWidth; x++) {
                if ((x + y) % 2 == 0) {
                    paint(x, y, DEAD);
                }
            }
        }
        renewIcon();
    }

    private void renewIcon ()
    {
        icon = new ImageIcon(bimage.getScaledInstance(gridWidth * SCALE, gridHeight * SCALE, Image.SCALE_SMOOTH));
        setIcon(icon);
    }

    private void paint (int index, int color)
    {
        pixels[index] = color;
    }

    private void paint (int x, int y, int color)
    {
        pixels[x + y * gridWidth] = color;
    }

    private void change (int[] array, int index)
    {
        array[index] = (array[index] == ALIVE) ? DEAD : ALIVE;
    }

    private void change (int[] array, int x, int y)
    {
        array[x + y * gridWidth] = (array[x + y * gridWidth] == ALIVE) ? DEAD : ALIVE;
    }

    private int checkNeighbours(int x, int y) {
        byte yStart = 1, yEnd = 2, xStart = 1, xEnd = 2;
        if (y == 0) {
            yStart = 0;
        }
        if (y == gridHeight - 1) {
            yEnd = 1;
        }
        if (x == 0) {
            xStart = 0;
        }
        if (x == gridWidth - 1) {
            xEnd = 1;
        }
        int sum = 0;
        for (int i = y - yStart; i < y + yEnd; i++) {
            for (int j = x - xStart; j < x + xEnd; j++) {
                if ((i != y || j != x) && pixels[j + i * gridWidth] == ALIVE) {
                    sum++;
                }
            }
        }
        return sum;
    }

    class GridMouseListener implements MouseListener {
        @Override
        public void mouseClicked (MouseEvent e)
        {
        }

        @Override
        public void mouseEntered (MouseEvent e)
        {
        }

        @Override
        public void mouseExited (MouseEvent e)
        {
        }

        @Override
        public void mouseReleased (MouseEvent e)
        {
        }

        @Override
        public void mousePressed (MouseEvent e)
        {
            int x = e.getX() / SCALE;
            int y = e.getY() / SCALE;
            change(pixels, x, y);
            renewIcon();
            initial = true;
        }
    }

}
