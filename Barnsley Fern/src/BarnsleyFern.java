import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BarnsleyFern extends JPanel {

    BufferedImage img;

    public BarnsleyFern() {
        final int dim = 640;
        setPreferredSize(new Dimension(dim, dim));
        setBackground(Color.white);
        img = new BufferedImage(dim, dim, BufferedImage.TYPE_INT_ARGB);
        createFern(dim, dim);
    }


void createFern(int w, int h) {
    double x = 0;
    double y = 0;

    for (int i = 0; i < 200_000; i++) {
        double nextX, nextY;
        double r = Math.random();


        if (r <= 0.01) {
            nextX = 0;
            nextY = 0.16 * y;
        } else if (r <= 0.08) {
            nextX = 0.02 * x - 0.26 * y;
            nextY = 0.23 * x + 0.22 * y + 1.6;
        } else if (r <= 0.15) {
            nextX = -0.15 * x + 0.28 * y;
            nextY = 0.26 * x + 0.24 * y + 0.44;
        } else {
            nextX = 0.85 * x + 0.04 * y;
            nextY = -0.04 * x + 0.85 * y + 1.6;
        }

        x = nextX;
        y = nextY;

        img.setRGB((int) Math.round(w / 2 + x * w / 11), (int) Math.round(h - y * h / 11), 0xFF32CD32);
    }
}

public void paintComponent(Graphics gg) {
    super.paintComponent(gg);
    Graphics2D g = (Graphics2D) gg;
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.drawImage(img, 0,0,null);
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setTitle("Barnsley Fern");
            f.setResizable(false);
            f.add(new BarnsleyFern(), BorderLayout.CENTER);
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}