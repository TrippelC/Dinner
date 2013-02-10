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
import se.kth.csc.iprog.dinnerplanner.view.IngredientView;
import se.kth.csc.iprog.dinnerplanner.view.PreparationView;
import se.kth.csc.iprog.dinnerplanner.view.StartView;

public class MainController implements ActionListener, ChangeListener, MouseListener {
	DinnerModel model;
	StartView view;

	public MainController(DinnerModel model, StartView view) {
		this.model = model;
		this.view = view;

		view.ingredientsButton.addActionListener(this);
		view.preparationButton.addActionListener(this);

		view.guestCountSpinner.addChangeListener(this);

		view.starter.panelSearch.addActionListener(this);
		view.desert.panelSearch.addActionListener(this);
		view.main.panelSearch.addActionListener(this);
		
		for (Component dishes :view.starter.scrollPanel.getComponents()){
			dishes.addMouseListener(this);
		}
		for (Component dishes :view.desert.scrollPanel.getComponents()){
			dishes.addMouseListener(this);
		}
		for (Component dishes :view.main.scrollPanel.getComponents()){
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

		}
		if (e.getSource() == view.main.panelSearch) {

			view.updateDishes(model.filterDishesOfType(2,
					view.main.panelSearch.getText()), 2);

		}
		if (e.getSource() == view.desert.panelSearch) {
			view.updateDishes(
					model.filterDishesOfType(3,
							view.desert.panelSearch.getText()), 3);

		}

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == view.guestCountSpinner) {
			model.setNumberOfGuests((Integer) view.guestCountSpinner.getModel()
					.getValue());

		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		
		System.out.println(arg0.getComponent().getName());	
		for (Dish dish: model.getDishes()){
			if(dish.getName().equals(arg0.getComponent().getName())){

				if (dish.getType() == Dish.STARTER
						&& !DinnerPlanner.choosenFlag[Dish.STARTER - 1]) {
					DinnerPlanner.choosenFlag[Dish.STARTER - 1] = true;
					model.selectDish(dish);
				} else if (dish.getType() == Dish.MAIN
						&& !DinnerPlanner.choosenFlag[Dish.MAIN - 1]) {
					DinnerPlanner.choosenFlag[Dish.MAIN - 1] = true;
					model.selectDish(dish);
				} else if (dish.getType() == Dish.DESERT
						&& !DinnerPlanner.choosenFlag[Dish.DESERT - 1]) {
					DinnerPlanner.choosenFlag[Dish.DESERT - 1] = true;
					model.selectDish(dish);
				} else {
						System.out.println("full");
					}

				view.addModel(model);//TODO
			}
		}
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
