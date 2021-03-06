package holdem.components;

import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class RoundedCornerBorder extends AbstractBorder {
    private static final Color ALPHA_ZERO = new Color(0, 0, 0, 0);

    private Color color;

    public RoundedCornerBorder(Color color) {
        this.color =  color;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

        if (c.isVisible()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Shape border = new RoundRectangle2D.Double(x, y, width - 1, height - 1, height, height);
            g2.setStroke(new BasicStroke(1));

            g2.setPaint(ALPHA_ZERO);
            Area corner = new Area(new Rectangle2D.Double(x, y, width, height));
            corner.subtract(new Area(border));
            g2.fill(corner);

            g2.setPaint(color);
            g2.draw(border);
            g2.dispose();
        }
    }

    @Override public Insets getBorderInsets(Component c) {
        return new Insets(4, 8, 4, 8);
    }
    @Override public Insets getBorderInsets(Component c, Insets insets) {
        insets.set(4, 8, 4, 8);
        return insets;
    }
}
