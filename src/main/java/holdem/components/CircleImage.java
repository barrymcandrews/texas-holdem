package holdem.components;

import holdem.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class CircleImage extends JPanel {
    private final Dimension dimension = Constants.PLAYER_IMAGE_DIMENSION;

    private BufferedImage clipped;

    public CircleImage() {
        super(true);
        setSize(dimension);
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);
        setLayout(null);
        setBackground(new Color(0, 0, 0, 0));
    }

    public void setImage(ImageIcon imageIcon) {
        Image image = imageIcon.getImage();
        Image scaled = image.getScaledInstance(dimension.width, dimension.height, Image.SCALE_SMOOTH);
        MediaTracker tracker = new MediaTracker(this);
        tracker.addImage(scaled, 1);
        try {
            tracker.waitForAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clipped = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = clipped.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setClip(new Ellipse2D.Double(1, 1, dimension.width - 2, dimension.height - 2));

        g2.drawImage(scaled, 0, 0, this);
        g2.dispose();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(clipped, 0, 0, this);
    }

    @Override
    protected void paintBorder(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        Shape border = new Ellipse2D.Double(1, 1, dimension.width - 2, dimension.height - 2);
        g2.setStroke(new BasicStroke(1.5f));

        g2.setPaint(Color.WHITE);
        g2.draw(border);
        g2.dispose();
    }
}