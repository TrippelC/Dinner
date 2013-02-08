package se.kth.csc.iprog.dinnerplanner.view;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

import java.awt.Font;

public class PreparationView extends JPanel {

	/**
	 * Create the panel.
	 */
	public PreparationView(Dish dish) {
		setBounds(100, 100, 480, 400);
		
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel emi = new JLabel("<html><center>"+dish.getDescription()+"</center></html>", JLabel.CENTER);
		springLayout.putConstraint(SpringLayout.NORTH, emi, 5, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, emi, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, emi, -10, SpringLayout.EAST, this);
		add(emi);
	}
		
	public PreparationView(DinnerModel model) {
		setBounds(100, 100, 520, 480);
		
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
				
		JLabel dmpLabel = new JLabel("Dinner menu preparation", JLabel.CENTER);
		dmpLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		springLayout.putConstraint(SpringLayout.NORTH, dmpLabel, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, dmpLabel, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, dmpLabel, -10, SpringLayout.EAST, this);
		add(dmpLabel);
		
		Dish[] dishes = new Dish[model.getFullMenu().size()];
		int gangnamStyle = 0;
		for(Dish dish : model.getFullMenu()){
			dishes[gangnamStyle++] = dish;
		}
		
		JLabel[] labels = new JLabel[dishes.length];
		JLabel[] preps = new JLabel[dishes.length];
		
		for(int i = 0; i < dishes.length; ++i){
			labels[i] = new JLabel(Dish.getDishTypeName(dishes[i].getType())+": "+dishes[i].getName(), JLabel.CENTER);
			labels[i].setFont(new Font("Tahoma", Font.PLAIN, 18));
			springLayout.putConstraint(SpringLayout.NORTH, labels[i], 15, SpringLayout.SOUTH, i==0?dmpLabel:preps[i-1]);
			springLayout.putConstraint(SpringLayout.WEST, labels[i], 10, SpringLayout.WEST, this);
			springLayout.putConstraint(SpringLayout.EAST, labels[i], -10, SpringLayout.EAST, this);
			add(labels[i]);
			
			preps[i] = new JLabel("<html><center>"+dishes[i].getDescription()+"</center></html>", JLabel.CENTER);
			springLayout.putConstraint(SpringLayout.NORTH, preps[i], 5, SpringLayout.SOUTH, labels[i]);
			springLayout.putConstraint(SpringLayout.WEST, preps[i], 10, SpringLayout.WEST, this);
			springLayout.putConstraint(SpringLayout.EAST, preps[i], -10, SpringLayout.EAST, this);
			add(preps[i]);
		}
		
		
		
		
		
//		JLabel lblStarterDishName = new JLabel("Starter: Dish name", JLabel.CENTER);
//		lblStarterDishName.setFont(new Font("Tahoma", Font.PLAIN, 18));
//		springLayout.putConstraint(SpringLayout.NORTH, lblStarterDishName, 15, SpringLayout.SOUTH, dmpLabel);
//		springLayout.putConstraint(SpringLayout.WEST, lblStarterDishName, 10, SpringLayout.WEST, this);
//		springLayout.putConstraint(SpringLayout.EAST, lblStarterDishName, -10, SpringLayout.EAST, this);
//		add(lblStarterDishName);
//		
//		JLabel lblStarterPrep = new JLabel("<html><center>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</center></html>", JLabel.CENTER);
//		springLayout.putConstraint(SpringLayout.NORTH, lblStarterPrep, 5, SpringLayout.SOUTH, lblStarterDishName);
//		springLayout.putConstraint(SpringLayout.WEST, lblStarterPrep, 10, SpringLayout.WEST, this);
//		springLayout.putConstraint(SpringLayout.EAST, lblStarterPrep, -10, SpringLayout.EAST, this);
//		add(lblStarterPrep);
//
//
//		
//		JLabel lblMainDishName = new JLabel("Main: Dish name", JLabel.CENTER);
//		lblMainDishName.setFont(new Font("Tahoma", Font.PLAIN, 18));
//		springLayout.putConstraint(SpringLayout.NORTH, lblMainDishName, 15, SpringLayout.SOUTH, lblStarterPrep);
//		springLayout.putConstraint(SpringLayout.WEST, lblMainDishName, 10, SpringLayout.WEST, this);
//		springLayout.putConstraint(SpringLayout.EAST, lblMainDishName, -10, SpringLayout.EAST, this);
//		add(lblMainDishName);
//		
//		JLabel lblMainPrep = new JLabel("<html><center>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</center></html>", JLabel.CENTER);
//		springLayout.putConstraint(SpringLayout.NORTH, lblMainPrep, 5, SpringLayout.SOUTH, lblMainDishName);
//		springLayout.putConstraint(SpringLayout.WEST, lblMainPrep, 10, SpringLayout.WEST, this);
//		springLayout.putConstraint(SpringLayout.EAST, lblMainPrep, -10, SpringLayout.EAST, this);
//		add(lblMainPrep);
//		
//		
//		
//		JLabel lblDesertDishName = new JLabel("Desert: Dish name", JLabel.CENTER);
//		lblDesertDishName.setFont(new Font("Tahoma", Font.PLAIN, 18));
//		springLayout.putConstraint(SpringLayout.NORTH, lblDesertDishName, 15, SpringLayout.SOUTH, lblMainPrep);
//		springLayout.putConstraint(SpringLayout.WEST, lblDesertDishName, 10, SpringLayout.WEST, this);
//		springLayout.putConstraint(SpringLayout.EAST, lblDesertDishName, -10, SpringLayout.EAST, this);
//		add(lblDesertDishName);
//		
//		JLabel lblDesertPrep = new JLabel("<html><center>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</center></html>", JLabel.CENTER);
//		springLayout.putConstraint(SpringLayout.NORTH, lblDesertPrep, 5, SpringLayout.SOUTH, lblDesertDishName);
//		springLayout.putConstraint(SpringLayout.WEST, lblDesertPrep, 10, SpringLayout.WEST, this);
//		springLayout.putConstraint(SpringLayout.EAST, lblDesertPrep, -10, SpringLayout.EAST, this);
//		add(lblDesertPrep);

	}
}
