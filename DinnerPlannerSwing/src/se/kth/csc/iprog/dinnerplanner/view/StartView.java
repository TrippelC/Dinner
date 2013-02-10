package se.kth.csc.iprog.dinnerplanner.view;

import java.awt.Font;

import javax.imageio.ImageIO;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpringLayout;
import javax.swing.JButton;

import se.kth.csc.iprog.dinnerplanner.controller.MainController;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SpinnerNumberModel;

public class StartView extends JPanel implements Observer {

	public JButton preparationButton = new JButton("Preparation");
	public JButton ingredientsButton = new JButton("Ingredients");
	public JTabbedPane leftPanel = new JTabbedPane();
	public JSpinner guestCountSpinner = new JSpinner();
	public JLabel costLabel = new JLabel("$ 0.0");
	public TabPanel starter;
	public TabPanel main;
	public TabPanel desert;
	/**
	 * Create the panel.
	 * 
	 * @throws IOException
	 */
	MainController controller;

	public StartView(DinnerModel model) {
		model.addObserver(this);
		controller = new MainController(model, this);
		setBounds(100, 100, 680, 440);
		SpringLayout springLayout = new SpringLayout();

		setLayout(springLayout);

		// Create leftPanel [TabbedPane]
		springLayout.putConstraint(SpringLayout.NORTH, leftPanel, 10,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, leftPanel, 10,
				SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, leftPanel, -10,
				SpringLayout.SOUTH, this);

		// //////////////////////////////////////////////////////////////////////////////////////////////////
		// Add subPanel inside tab
		starter = new TabPanel("Starter", model);
		leftPanel.addTab("Starter", starter);
		// //////////////////////////////////////////////////////////////////////////////////////////////////
		main = new TabPanel("Main", model);
		leftPanel.addTab("Main", main);
		// //////////////////////////////////////////////////////////////////////////////////////////////////
		desert = new TabPanel("Desert", model);
		leftPanel.addTab("Desert", desert);
		// //////////////////////////////////////////////////////////////////////////////////////////////////
		// ADDS MODEL AS JLABELS

		// //////////////////////////////////////////////////////////////////////////////////////////////////
		// //////////////////////////////////////////////////////////////////////////////////////////////////
		// //////////////////////////////////////////////////////////////////////////////////////////////////
		// //////////////////////////////////////////////////////////////////////////////////////////////////
		// //////////////////////////////////////////////////////////////////////////////////////////////////

		// Create rightPanel
		JPanel rightPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.EAST, leftPanel, -6,
				SpringLayout.WEST, rightPanel);
		springLayout.putConstraint(SpringLayout.WEST, rightPanel, -250,
				SpringLayout.EAST, this);

		springLayout.putConstraint(SpringLayout.EAST, rightPanel, -10,
				SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, rightPanel, 10,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, rightPanel, -10,
				SpringLayout.SOUTH, this);
		this.add(rightPanel);
		SpringLayout sl_rightPanel = new SpringLayout();
		rightPanel.setLayout(sl_rightPanel);

		JLabel nopLabel = new JLabel("Number of people:");
		sl_rightPanel.putConstraint(SpringLayout.WEST, nopLabel, 10,
				SpringLayout.WEST, rightPanel);
		sl_rightPanel.putConstraint(SpringLayout.NORTH, nopLabel, 40,
				SpringLayout.NORTH, rightPanel);
		rightPanel.add(nopLabel);

		guestCountSpinner.setModel(new SpinnerNumberModel(new Integer(0),
				new Integer(0), null, new Integer(1)));
		sl_rightPanel.putConstraint(SpringLayout.NORTH, guestCountSpinner, 40,
				SpringLayout.NORTH, rightPanel);
		sl_rightPanel.putConstraint(SpringLayout.EAST, guestCountSpinner, -10,
				SpringLayout.EAST, rightPanel);
		sl_rightPanel.putConstraint(SpringLayout.WEST, guestCountSpinner, 50,
				SpringLayout.EAST, nopLabel);
		rightPanel.add(guestCountSpinner);
		JLabel costTagLabel = new JLabel("Total cost:");
		sl_rightPanel.putConstraint(SpringLayout.NORTH, costTagLabel, 20,
				SpringLayout.SOUTH, nopLabel);
		sl_rightPanel.putConstraint(SpringLayout.WEST, costTagLabel, 10,
				SpringLayout.WEST, rightPanel);
		rightPanel.add(costTagLabel);

		sl_rightPanel.putConstraint(SpringLayout.NORTH, costLabel, 0,
				SpringLayout.NORTH, costTagLabel);
		sl_rightPanel.putConstraint(SpringLayout.EAST, costLabel, -20,
				SpringLayout.EAST, rightPanel);
		rightPanel.add(costLabel);

		JLabel dinnerMenuLabel = new JLabel("Dinner menu", JLabel.CENTER);
		sl_rightPanel.putConstraint(SpringLayout.NORTH, dinnerMenuLabel, 20,
				SpringLayout.SOUTH, costTagLabel);
		sl_rightPanel.putConstraint(SpringLayout.WEST, dinnerMenuLabel, 10,
				SpringLayout.WEST, rightPanel);
		sl_rightPanel.putConstraint(SpringLayout.EAST, dinnerMenuLabel, -10,
				SpringLayout.EAST, rightPanel);
		dinnerMenuLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rightPanel.add(dinnerMenuLabel);

		JLabel[] dishIcon = new JLabel[model.getSelectedCount()];
		JLabel[] lblNewLabel = new JLabel[model.getSelectedCount()];
		JButton[] btnNewButton = new JButton[model.getSelectedCount()];
		
		for (int i = 0; i < model.getSelectedCount(); i++) {
			dishIcon[i] = new JLabel(new ImageIcon("images/"
					+ model.getSelectedDishes()[i].getImage()));
			lblNewLabel[i] = new JLabel("<html>"
					+ Dish.getDishTypeName(model.getSelectedDishes()[i]
							.getType()) + ": "
					+ model.getSelectedDishes()[i].getName() + "<br>Cost: $ "
					+ model.getDishPrice(model.getSelectedDishes()[i])
					+ "</html>");
			btnNewButton[i] = new JButton(new ImageIcon(
					"images/delete_icon.gif"));
			btnNewButton[i].addActionListener(controller);
			btnNewButton[i].setName("name" + i);

			sl_rightPanel.putConstraint(SpringLayout.NORTH, dishIcon[i],
					i == 0 ? 20 : 15, SpringLayout.SOUTH,
					i == 0 ? dinnerMenuLabel : dishIcon[i - 1]);
			sl_rightPanel.putConstraint(SpringLayout.SOUTH, dishIcon[i],
					i == 0 ? 60 : 55, SpringLayout.SOUTH,
					i == 0 ? dinnerMenuLabel : dishIcon[i - 1]);
			sl_rightPanel.putConstraint(SpringLayout.WEST, dishIcon[i], 10,
					SpringLayout.WEST, rightPanel);
			sl_rightPanel.putConstraint(SpringLayout.EAST, dishIcon[i], 50,
					SpringLayout.WEST, rightPanel);
			rightPanel.add(dishIcon[i]);

			sl_rightPanel.putConstraint(SpringLayout.NORTH, lblNewLabel[i], 0,
					SpringLayout.NORTH, dishIcon[i]);
			sl_rightPanel.putConstraint(SpringLayout.SOUTH, lblNewLabel[i], 0,
					SpringLayout.SOUTH, dishIcon[i]);
			sl_rightPanel.putConstraint(SpringLayout.WEST, lblNewLabel[i], 10,
					SpringLayout.EAST, dishIcon[i]);
			sl_rightPanel.putConstraint(SpringLayout.EAST, lblNewLabel[i], -10,
					SpringLayout.WEST, btnNewButton[i]);
			rightPanel.add(lblNewLabel[i]);

			sl_rightPanel.putConstraint(SpringLayout.NORTH, btnNewButton[i], 5,
					SpringLayout.NORTH, lblNewLabel[i]);
			sl_rightPanel.putConstraint(SpringLayout.SOUTH, btnNewButton[i],
					-5, SpringLayout.SOUTH, lblNewLabel[i]);
			sl_rightPanel.putConstraint(SpringLayout.EAST, btnNewButton[i],
					-10, SpringLayout.EAST, rightPanel);
			sl_rightPanel.putConstraint(SpringLayout.WEST, btnNewButton[i],
					-50, SpringLayout.EAST, rightPanel);
			rightPanel.add(btnNewButton[i]);
		}

		// preparationButton
		sl_rightPanel.putConstraint(SpringLayout.WEST, preparationButton, 10,
				SpringLayout.WEST, rightPanel);
		sl_rightPanel.putConstraint(SpringLayout.SOUTH, preparationButton, -10,
				SpringLayout.SOUTH, rightPanel);
		rightPanel.add(preparationButton);

		// ingredientsButton
		sl_rightPanel.putConstraint(SpringLayout.EAST, ingredientsButton, -10,
				SpringLayout.EAST, rightPanel);
		sl_rightPanel.putConstraint(SpringLayout.SOUTH, ingredientsButton, -10,
				SpringLayout.SOUTH, rightPanel);
		rightPanel.add(ingredientsButton);

		// update(null, model);

		starter.scrollPanel.removeAll();
		main.scrollPanel.removeAll();
		desert.scrollPanel.removeAll();
		// DinnerModel model = (DinnerModel) object;
		for (Dish dish : model.getDishes()) {
			ImageIcon ic = null;
			try {
				ic = new ImageIcon(ImageIO.read(
						new File("images/" + dish.getImage()))
						.getScaledInstance(100, 100, 0));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JLabel tempDish = new JLabel(dish.getName(), ic, JLabel.CENTER);
			tempDish.setVerticalTextPosition(JLabel.BOTTOM);
			tempDish.setHorizontalTextPosition(JLabel.CENTER);

			switch (dish.getType()) {
			case 1:
				starter.scrollPanel.add(tempDish);
				break;
			case 2:
				main.scrollPanel.add(tempDish);
				break;
			case 3:
				desert.scrollPanel.add(tempDish);
			}
		}

		add(leftPanel);

	}

	@Override
	public void update(Observable arg0, Object object) {
		if (object.equals("numberOfGuests")) {
			DinnerModel model = (DinnerModel) arg0;
			costLabel.setText("$ " + model.getTotalMenuPrice());
		}

	}

}
