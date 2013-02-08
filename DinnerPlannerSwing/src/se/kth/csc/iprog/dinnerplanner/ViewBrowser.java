package se.kth.csc.iprog.dinnerplanner;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JButton;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.view.DishView;
import se.kth.csc.iprog.dinnerplanner.view.IngredientView;
import se.kth.csc.iprog.dinnerplanner.view.PreparationView;
import se.kth.csc.iprog.dinnerplanner.view.StartView;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JLabel;

public class ViewBrowser extends JFrame {
	private DinnerModel model;
	private Dish[] dishes;
	

	private void initializeModel(int dishCount){
		model = new DinnerModel();
		model.setNumberOfGuests(3);
		
		dishes = new Dish[model.getDishes().size()];
		int i = 0;
		for(Dish dish : model.getDishes())
			{dishes[i]=dish;i++;}
		
		//ADD STARTER TO MENU
		if(dishCount == 1){
			for(Dish dish : dishes)
				if(dish.getType() == Dish.STARTER){
					model.selectDish(dish);
					break;
				}
		}
		//ADD FULL MENU:
		if(dishCount == 3){
			boolean[] choosenFlag = new boolean[3];
			for(Dish dish : dishes)
				if(dish.getType() == Dish.STARTER && !choosenFlag[Dish.STARTER-1]){
					model.selectDish(dish);
				}else if(dish.getType() == Dish.MAIN && !choosenFlag[Dish.MAIN-1]){
					model.selectDish(dish);
				}else if(dish.getType() == Dish.DESERT && !choosenFlag[Dish.DESERT-1]){
					model.selectDish(dish);
				}else{
					break;
				}
		}
	}
	
	public ViewBrowser() {
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		
		JLabel lblStartTheView = new JLabel("Start the view you'd like to see");
		springLayout.putConstraint(SpringLayout.NORTH, lblStartTheView, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblStartTheView, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblStartTheView, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(lblStartTheView);
		
		JButton btnStartview = new JButton("StartView");
		
		springLayout.putConstraint(SpringLayout.NORTH, btnStartview, 10, SpringLayout.SOUTH, lblStartTheView);
		springLayout.putConstraint(SpringLayout.WEST, btnStartview, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnStartview, -10, SpringLayout.EAST, getContentPane());
		btnStartview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initializeModel(0);
				setUpView(new StartView(model));
			}
		});
		getContentPane().add(btnStartview);
		
		JButton btnDishview = new JButton("DishView");
		btnDishview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initializeModel(0);
				setUpView(new DishView(model, dishes[1]));
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnDishview, 10, SpringLayout.SOUTH, btnStartview);
		springLayout.putConstraint(SpringLayout.WEST, btnDishview, 0, SpringLayout.WEST, btnStartview);
		springLayout.putConstraint(SpringLayout.EAST, btnDishview, 0, SpringLayout.EAST, btnStartview);
		getContentPane().add(btnDishview);
		
		JButton btnStarterchoosenview = new JButton("StarterChoosenView");
		btnStarterchoosenview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initializeModel(1);
				setUpView(new StartView(model));
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnStarterchoosenview, 10, SpringLayout.SOUTH, btnDishview);
		springLayout.putConstraint(SpringLayout.WEST, btnStarterchoosenview, 0, SpringLayout.WEST, btnStartview);
		springLayout.putConstraint(SpringLayout.EAST, btnStarterchoosenview, 0, SpringLayout.EAST, btnStartview);
		getContentPane().add(btnStarterchoosenview);
		
		JButton btnFullmenuview = new JButton("FullMenuView");
		btnFullmenuview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initializeModel(3);
				setUpView(new StartView(model));
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnFullmenuview, 10, SpringLayout.SOUTH, btnStarterchoosenview);
		springLayout.putConstraint(SpringLayout.WEST, btnFullmenuview, 0, SpringLayout.WEST, btnStartview);
		springLayout.putConstraint(SpringLayout.EAST, btnFullmenuview, 0, SpringLayout.EAST, btnStartview);
		getContentPane().add(btnFullmenuview);
		
		JButton btnIngredientsview = new JButton("IngredientsView");
		btnIngredientsview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initializeModel(0);
				setUpView(new IngredientView(dishes[1].getIngredients()));
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnIngredientsview, 10, SpringLayout.SOUTH, btnFullmenuview);
		springLayout.putConstraint(SpringLayout.WEST, btnIngredientsview, 0, SpringLayout.WEST, btnStartview);
		springLayout.putConstraint(SpringLayout.EAST, btnIngredientsview, 0, SpringLayout.EAST, btnStartview);
		getContentPane().add(btnIngredientsview);
		
		JButton btnPreparationview = new JButton("PreparationView");
		btnPreparationview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initializeModel(3);
				setUpView(new PreparationView(model));
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnPreparationview, 10, SpringLayout.SOUTH, btnIngredientsview);
		springLayout.putConstraint(SpringLayout.WEST, btnPreparationview, 0, SpringLayout.WEST, btnStartview);
		springLayout.putConstraint(SpringLayout.EAST, btnPreparationview, 0, SpringLayout.EAST, btnStartview);

		getContentPane().add(btnPreparationview);
	}
	
	private void setUpView(JComponent comp){
		JFrame jf = new JFrame();
		jf.setBounds(comp.getBounds());
		jf.setResizable(false);
		jf.getContentPane().add(comp);
		jf.setVisible(true);
	}
	
	
	public static void main(String[] args) throws IOException {
		JFrame jf = new ViewBrowser();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setTitle("ViewBrowser");
		jf.setSize(280, 280);
		jf.setResizable(false);
		jf.setVisible(true);
	}
}


