package userInterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import javax.swing.border.AbstractBorder;

public class TetrisFieldBorder extends AbstractBorder {

    private final Color top, right, bottom, left, line;
    private final int borderWidth;

    public TetrisFieldBorder(final Color border, final Color line, final int borderWidth) {
	this.top = border.brighter();
	this.left = border;
	this.right = border.darker();
	this.bottom = border.darker().darker();
	this.line = line;
	this.borderWidth = borderWidth;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	super.paintBorder(c, g, x, y, width, height);

	int h = height;
	int w = width;
	int bw = borderWidth;
	Graphics2D g2 = (Graphics2D) g.create();
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	g2.translate(x, y);

	drawTop(w, bw, g2);
	drawLeft(h, w, bw, g2);
	drawBottom(h, w, bw, g2);
	drawRight(h, bw, g2);

	g2.dispose();
    }

    private void drawRight(int h, int bw, Graphics2D g2) {
	Polygon leftPolygon = createPolygon(new Point(0, 0), new Point(0, h), new Point(bw, h - bw), new Point(bw, bw), new Point(0, 0));
	g2.setColor(left);
	g2.fill(leftPolygon);
	g2.setColor(line);
	g2.draw(leftPolygon);
    }

    private void drawBottom(int h, int w, int bw, Graphics2D g2) {
	Polygon bottomPolygon = createPolygon(new Point(0, h - 1), new Point(w, h - 1), new Point(w - bw, h - bw - 1), new Point(bw, h - bw - 1), new Point(0, h - 1));
	g2.setColor(bottom);
	g2.fill(bottomPolygon);
	g2.setColor(line);
	g2.draw(bottomPolygon);
    }

    private void drawTop(int w, int bw, Graphics2D g2) {
	Polygon topPolygon = createPolygon(new Point(0, 0), new Point(w, 0), new Point(w - bw, bw), new Point(bw, bw), new Point(0, 0));
	g2.setColor(top);
	g2.fill(topPolygon);
	g2.setColor(line);
	g2.draw(topPolygon);
    }

    private void drawLeft(int h, int w, int bw, Graphics2D g2) {
	Polygon rightPolygon = createPolygon(new Point(w - 1, 0), new Point(w - 1, h), new Point(w - bw - 1, h - bw), new Point(w - bw - 1, bw), new Point(w - 1, 0));
	g2.setColor(right);
	g2.fill(rightPolygon);
	g2.setColor(line);
	g2.draw(rightPolygon);
    }

    @Override
    public Insets getBorderInsets(Component c) {
	return new Insets(borderWidth, borderWidth, borderWidth + 1, borderWidth + 1);
    }

    private Polygon createPolygon(Point... points) {
	Polygon polygon = new Polygon();
	for (Point point : points) {
	    polygon.addPoint(point.x, point.y);
	}
	return polygon;
    }

}
