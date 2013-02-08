package se.kth.csc.iprog.dinnerplanner.view;

import java.util.Set;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import se.kth.csc.iprog.dinnerplanner.model.Ingredient;

public class IngredientView extends JScrollPane {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public IngredientView(Set<Ingredient> ingredients) {
		setBounds(100, 100, 480, 400);

		String[] columnNames = { "Ingredient", "Quantity", "Cost"};

		Object[][] data = new Object[ingredients.size()][columnNames.length];
		
		int cocolala = 0;
		for(Ingredient ing : ingredients){
			data[cocolala][0] = ing.getName();
			data[cocolala][1] = ing.getQuantity()+ " " + ing.getUnit();
			data[cocolala][2] = ing.getPrice()+" $";
			++cocolala;
		}

		table = new JTable(data, columnNames);
		setViewportView(table);
	}
}
