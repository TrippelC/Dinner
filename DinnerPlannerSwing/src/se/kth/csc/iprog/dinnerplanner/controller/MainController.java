package se.kth.csc.iprog.dinnerplanner.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.view.IngredientView;
import se.kth.csc.iprog.dinnerplanner.view.PreparationView;
import se.kth.csc.iprog.dinnerplanner.view.StartView;

public class MainController implements ActionListener, ChangeListener {
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

		System.out.println(e.getSource().toString());
		if (e.getSource() == view.ingredientsButton) {
			setUpView(new IngredientView(model.getAllIngredients()));
		}
		if (e.getSource() == view.preparationButton) {
			setUpView(new PreparationView(model));
		}
		if (e.getSource() == view.starter.panelSearch) {

			view.updateDishes(model.filterDishesOfType(1,
					view.starter.panelSearch.getText()));

		}
		if (e.getSource() == view.main.panelSearch) {

			view.updateDishes(model.filterDishesOfType(2,
					view.main.panelSearch.getText()));

		}
		if (e.getSource() == view.desert.panelSearch) {
			view.updateDishes(model.filterDishesOfType(3,
					view.desert.panelSearch.getText()));

		}

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == view.guestCountSpinner) {
			model.setNumberOfGuests((Integer) view.guestCountSpinner.getModel()
					.getValue());

		}
	}

}
