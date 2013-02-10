package se.kth.csc.iprog.dinnerplanner.controller;

import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;
import se.kth.csc.iprog.dinnerplanner.view.DishView;
import se.kth.csc.iprog.dinnerplanner.view.IngredientView;
import se.kth.csc.iprog.dinnerplanner.view.PreparationView;
import se.kth.csc.iprog.dinnerplanner.view.StartView;

public class MainController implements ActionListener {
	DinnerModel model;
	StartView view;

	public MainController(DinnerModel model, StartView view) {
		this.model = model;
		this.view = view;

		view.ingredientsButton.addActionListener(this);
		view.preparationButton.addActionListener(this);
		

		
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
		
		
	}
	
}
