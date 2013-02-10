package se.kth.csc.iprog.dinnerplanner.view;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import se.kth.csc.iprog.dinnerplanner.model.Dish;

public class TabPanel extends JPanel {
	public ScrollablePanel scrollPanel = null;
	public JTextField panelSearch;
	public TabPanel(String name, DinnerModel model) {

		SpringLayout sl_panel = new SpringLayout();
		this.setLayout(sl_panel);

		panelSearch = new JTextField("Search");
		sl_panel.putConstraint(SpringLayout.NORTH, panelSearch, 10,
				SpringLayout.NORTH, this);
		sl_panel.putConstraint(SpringLayout.WEST, panelSearch, 10,
				SpringLayout.WEST, this);
		sl_panel.putConstraint(SpringLayout.SOUTH, panelSearch, 40,
				SpringLayout.NORTH, this);
		sl_panel.putConstraint(SpringLayout.EAST, panelSearch, 330,
				SpringLayout.WEST, this);
		this.add(panelSearch);
		panelSearch.setColumns(10);
		if (name.equals("Starter")) {
			scrollPanel = new ScrollablePanel(model.getDishesOfType(
					Dish.STARTER).size());
		} else if (name.equals("Desert")) {
			scrollPanel = new ScrollablePanel(model
					.getDishesOfType(Dish.DESERT).size());
		} else if (name.equals("Main")) {
			scrollPanel = new ScrollablePanel(model.getDishesOfType(Dish.MAIN)
					.size());

		}

		JScrollPane jsppanel = new JScrollPane(scrollPanel);
		jsppanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsppanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		sl_panel.putConstraint(SpringLayout.NORTH, jsppanel, 5,
				SpringLayout.SOUTH, panelSearch);
		sl_panel.putConstraint(SpringLayout.WEST, jsppanel, 0,
				SpringLayout.WEST, this);
		sl_panel.putConstraint(SpringLayout.SOUTH, jsppanel, 0,
				SpringLayout.SOUTH, this);
		sl_panel.putConstraint(SpringLayout.EAST, jsppanel, 0,
				SpringLayout.EAST, this);
		this.add(jsppanel);
		scrollPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

	}
}
