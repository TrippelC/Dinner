package se.kth.csc.iprog.dinnerplanner.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import se.kth.csc.iprog.dinnerplanner.DinnerPlanner;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.view.DishView;
import se.kth.csc.iprog.dinnerplanner.view.IngredientView;
import se.kth.csc.iprog.dinnerplanner.view.PreparationView;
import se.kth.csc.iprog.dinnerplanner.view.StartView;

public class MainController implements ActionListener, ChangeListener,
		MouseListener {
	private DinnerModel model;
	private StartView view;
	private boolean[] choosenFlag = new boolean[3];

	public MainController(DinnerModel model, StartView view) {
		this.model = model;
		this.view = view;

		view.ingredientsButton.addActionListener(this);
		view.preparationButton.addActionListener(this);

		view.guestCountSpinner.addChangeListener(this);

		view.starter.panelSearch.addActionListener(this);
		view.desert.panelSearch.addActionListener(this);
		view.main.panelSearch.addActionListener(this);
		for (int i = 0; i < 3; i++) {
			view.btnNewButton[i].addActionListener(this);
			view.dishIcon[i].addMouseListener(this);
		}

		addListenersToDishComponents();

	}

	public void removeAllDishListeners() {
		for (Component dishes : view.starter.scrollPanel.getComponents()) {
			dishes.removeMouseListener(this);
		}
		for (Component dishes : view.desert.scrollPanel.getComponents()) {
			dishes.removeMouseListener(this);
		}
		for (Component dishes : view.main.scrollPanel.getComponents()) {
			dishes.removeMouseListener(this);
		}
	}

	public void addListenersToDishComponents() {
		for (Component dishes : view.starter.scrollPanel.getComponents()) {
			dishes.addMouseListener(this);

		}
		for (Component dishes : view.desert.scrollPanel.getComponents()) {
			dishes.addMouseListener(this);
		}
		for (Component dishes : view.main.scrollPanel.getComponents()) {
			dishes.addMouseListener(this);
		}
	}

	private void setUpView(JComponent comp) {
		JFrame jf = new JFrame();
		jf.setBounds(comp.getBounds());
		jf.setResizable(false);
		jf.getContentPane().add(comp);
		jf.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == view.ingredientsButton) {
			setUpView(new IngredientView(model.getAllIngredients()));
		}
		if (e.getSource() == view.preparationButton) {
			setUpView(new PreparationView(model));
		}

		if (e.getSource() == view.starter.panelSearch) {

			view.updateDishes(
					model.filterDishesOfType(1,
							view.starter.panelSearch.getText()), 1);

			removeAllDishListeners();
			addListenersToDishComponents();
		}
		if (e.getSource() == view.main.panelSearch) {

			view.updateDishes(model.filterDishesOfType(2,
					view.main.panelSearch.getText()), 2);

			removeAllDishListeners();
			addListenersToDishComponents();
		}
		if (e.getSource() == view.desert.panelSearch) {
			view.updateDishes(
					model.filterDishesOfType(3,
							view.desert.panelSearch.getText()), 3);

			removeAllDishListeners();
			addListenersToDishComponents();
		}
		if (e.getSource() == view.btnNewButton[0]) { // 0 == type 1

			removeDish(0);
		}
		if (e.getSource() == view.btnNewButton[1]) { // 1 == type 2

			removeDish(1);
		}
		if (e.getSource() == view.btnNewButton[2]) { // 2 == type 3
			
			removeDish(2);
		}
	}

	public void removeDish(int type) {
		view.btnNewButton[type].setVisible(false);
		view.lblNewLabel[type].setVisible(false);
		view.dishIcon[type].setVisible(false);
		choosenFlag[type] = false;
		model.removeSelectedDish(type);

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == view.guestCountSpinner) {
			model.setNumberOfGuests((Integer) view.guestCountSpinner.getModel()
					.getValue());
			view.addDishes(model, choosenFlag);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource() == view.dishIcon[0]){
			setUpView(new DishView(model, model.getDishByName(view.dishIcon[0].getName(),model)));
		}
		if(arg0.getSource() == view.dishIcon[1]){
			setUpView(new DishView(model, model.getDishByName(view.dishIcon[1].getName(),model)));
		}
		if(arg0.getSource() == view.dishIcon[2]){
			setUpView(new DishView(model, model.getDishByName(view.dishIcon[2].getName(),model)));
		}
		
		Dish dish = model.getDishByName(arg0.getComponent().getName(), model);
				if (dish.getType() == Dish.STARTER
						&& !choosenFlag[Dish.STARTER - 1]) {
					choosenFlag[Dish.STARTER - 1] = true;
					model.selectDish(dish);
				} else if (dish.getType() == Dish.MAIN
						&& !choosenFlag[Dish.MAIN - 1]) {
					choosenFlag[Dish.MAIN - 1] = true;
					model.selectDish(dish);
				} else if (dish.getType() == Dish.DESERT
						&& !choosenFlag[Dish.DESERT - 1]) {
					choosenFlag[Dish.DESERT - 1] = true;
					model.selectDish(dish);
				} else {
					//System.out.println("full");
				}
				view.addDishes(model, choosenFlag);
			

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
