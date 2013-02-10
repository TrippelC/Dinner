package se.kth.csc.iprog.dinnerplanner.view;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.Scrollable;


/**
 * This class implements a scrollable panel, it's taken from
 * http://stackoverflow.com/questions/3868093/resizing-panel-with-flow-layout-does-not-invoke-the-scroll-bars 
 * @author Geoffrey Zheng
 */


public class ScrollablePanel extends JPanel implements Scrollable {
	private int numberOfItems = 0;
	
	public ScrollablePanel(int nom){
		super();
		numberOfItems = nom;
	}
	
	public Dimension getPreferredSize() {
		return getPreferredScrollableViewportSize();
	}

	public Dimension getPreferredScrollableViewportSize() {
		if (getParent() == null)
			return getSize();
		Dimension d = getParent().getSize();
		int c = (int) Math
				.floor((d.width - getInsets().left - getInsets().right) / 105.0);
		if (c == 0)
			return d;
		int r = numberOfItems / c;
		if (r * c < numberOfItems)
			++r;
		return new Dimension(c * 105, r * 125);
	}

	public int getScrollableBlockIncrement(Rectangle visibleRect,
			int orientation, int direction) {
		return 130;
	}

	public int getScrollableUnitIncrement(Rectangle visibleRect,
			int orientation, int direction) {
		return 10;
	}

	public boolean getScrollableTracksViewportHeight() {
		return false;
	}

	public boolean getScrollableTracksViewportWidth() {
		return getParent() != null ? getParent().getSize().width > getPreferredSize().width
				: true;
	}
}