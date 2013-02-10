package se.kth.csc.iprog.dinnerplanner.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JSplitPane;
import javax.swing.JLabel;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

import java.awt.Font;

public class DishView extends JPanel {

	private BufferedImage bi = null;

	// private ImageIcon ic = new ImageIcon("images/toast.jpg");

	/**
	 * Create the panel.
	 */
	public DishView(DinnerModel model, Dish dish) {
		setBounds(100, 100, 680, 400);

		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		try {
			bi = ImageIO.read(new File("images/" + dish.getImage()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				true, new PreparationView(dish), new IngredientView(
						dish.getIngredients()));
		springLayout.putConstraint(SpringLayout.NORTH, splitPane, 130,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, splitPane, 10,
				SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, splitPane, -10,
				SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, splitPane, -10,
				SpringLayout.EAST, this);
		splitPane.setDividerLocation(340);
		add(splitPane);

		JLabel lblDishName = new JLabel(dish.getName());
		springLayout.putConstraint(SpringLayout.NORTH, lblDishName, 25,
				SpringLayout.NORTH, this);
		lblDishName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		add(lblDishName);

		JLabel lblPriceFor = new JLabel("$ " + model.getDishPrice(dish)
				+ " for " + model.getNumberOfGuests() + " persons");
		springLayout.putConstraint(SpringLayout.WEST, lblDishName, 0,
				SpringLayout.WEST, lblPriceFor);
		springLayout.putConstraint(SpringLayout.WEST, lblPriceFor, 150,
				SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblPriceFor, -36,
				SpringLayout.NORTH, splitPane);
		add(lblPriceFor);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bi, 15, 15, 100, 100, null); // see javadoc for more info on
													// the parameters
	}
}
