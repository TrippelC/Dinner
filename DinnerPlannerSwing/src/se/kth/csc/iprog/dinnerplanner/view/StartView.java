package se.kth.csc.iprog.dinnerplanner.view;

import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpringLayout;
import javax.swing.JButton;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.ScrollablePanel;

import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import javax.swing.JTextField;

public class StartView extends JPanel {
	private JTextField starterSearch, mainSearch, desertSearch;

	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public StartView(DinnerModel model) {
		setBounds(100, 100, 680, 440);
		SpringLayout springLayout = new SpringLayout();

		setLayout(springLayout);

		// Create leftPanel [TabbedPane]
		JTabbedPane leftPanel = new JTabbedPane();
		springLayout.putConstraint(SpringLayout.NORTH, leftPanel, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, leftPanel, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, leftPanel, -10, SpringLayout.SOUTH, this);

////////////////////////////////////////////////////////////////////////////////////////////////////
		// Add subPanel inside tab
		JPanel starter = new JPanel(false);
		leftPanel.addTab("Starter", starter);
		SpringLayout sl_starter = new SpringLayout();
		starter.setLayout(sl_starter);
		
		starterSearch = new JTextField("Search");
		sl_starter.putConstraint(SpringLayout.NORTH, starterSearch, 10, SpringLayout.NORTH, starter);
		sl_starter.putConstraint(SpringLayout.WEST, starterSearch, 10, SpringLayout.WEST, starter);
		sl_starter.putConstraint(SpringLayout.SOUTH, starterSearch, 40, SpringLayout.NORTH, starter);
		sl_starter.putConstraint(SpringLayout.EAST, starterSearch, 330, SpringLayout.WEST, starter);
		starter.add(starterSearch);
		starterSearch.setColumns(10);
		
		ScrollablePanel starterPanel = new ScrollablePanel(model.getDishesOfType(Dish.STARTER).size());
		
		JScrollPane jspStarter = new JScrollPane(starterPanel);
		jspStarter.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jspStarter.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		sl_starter.putConstraint(SpringLayout.NORTH, jspStarter, 5, SpringLayout.SOUTH, starterSearch);
		sl_starter.putConstraint(SpringLayout.WEST, jspStarter, 0, SpringLayout.WEST, starter);
		sl_starter.putConstraint(SpringLayout.SOUTH, jspStarter, 0, SpringLayout.SOUTH, starter);
		sl_starter.putConstraint(SpringLayout.EAST, jspStarter, 0, SpringLayout.EAST, starter);
		starter.add(jspStarter);
		starterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
////////////////////////////////////////////////////////////////////////////////////////////////////
		JPanel main = new JPanel(false);
		leftPanel.addTab("Main", main);
		SpringLayout sl_main = new SpringLayout();
		main.setLayout(sl_main);
		
		mainSearch = new JTextField("Search");
		sl_main.putConstraint(SpringLayout.NORTH, mainSearch, 10, SpringLayout.NORTH, main);
		sl_main.putConstraint(SpringLayout.WEST, mainSearch, 10, SpringLayout.WEST, main);
		sl_main.putConstraint(SpringLayout.SOUTH, mainSearch, 40, SpringLayout.NORTH, main);
		sl_main.putConstraint(SpringLayout.EAST, mainSearch, 330, SpringLayout.WEST, main);
		main.add(mainSearch);
		mainSearch.setColumns(10);
		
		ScrollablePanel mainPanel = new ScrollablePanel(model.getDishesOfType(Dish.MAIN).size());
		
		JScrollPane jspMain = new JScrollPane(mainPanel);
		jspMain.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jspMain.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		sl_main.putConstraint(SpringLayout.NORTH, jspMain, 5, SpringLayout.SOUTH, mainSearch);
		sl_main.putConstraint(SpringLayout.WEST, jspMain, 0, SpringLayout.WEST, main);
		sl_main.putConstraint(SpringLayout.SOUTH, jspMain, 0, SpringLayout.SOUTH, main);
		sl_main.putConstraint(SpringLayout.EAST, jspMain, 0, SpringLayout.EAST, main);
		main.add(jspMain);
		mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
////////////////////////////////////////////////////////////////////////////////////////////////////
		JPanel desert = new JPanel(false);
		leftPanel.addTab("Desert", desert);
		SpringLayout sl_desert = new SpringLayout();
		desert.setLayout(sl_desert);
		
		desertSearch = new JTextField("Search");
		sl_desert.putConstraint(SpringLayout.NORTH, desertSearch, 10, SpringLayout.NORTH, desert);
		sl_desert.putConstraint(SpringLayout.WEST, desertSearch, 10, SpringLayout.WEST, desert);
		sl_desert.putConstraint(SpringLayout.SOUTH, desertSearch, 40, SpringLayout.NORTH, desert);
		sl_desert.putConstraint(SpringLayout.EAST, desertSearch, 330, SpringLayout.WEST, desert);
		desert.add(desertSearch);
		desertSearch.setColumns(10);
		
		ScrollablePanel desertPanel = new ScrollablePanel(model.getDishesOfType(Dish.DESERT).size());		
		
		
		JScrollPane jspDesert = new JScrollPane(desertPanel);
		jspDesert.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jspDesert.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		sl_desert.putConstraint(SpringLayout.NORTH, jspDesert, 5, SpringLayout.SOUTH, desertSearch);
		sl_desert.putConstraint(SpringLayout.WEST, jspDesert, 0, SpringLayout.WEST, desert);
		sl_desert.putConstraint(SpringLayout.SOUTH, jspDesert, 0, SpringLayout.SOUTH, desert);
		sl_desert.putConstraint(SpringLayout.EAST, jspDesert, 0, SpringLayout.EAST, desert);
		desert.add(jspDesert);
		desertPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

////////////////////////////////////////////////////////////////////////////////////////////////////
		
		for(Dish dish : model.getDishes()){
			ImageIcon ic = null;
			try {
				ic = new ImageIcon(ImageIO.read(new File("images/"+dish.getImage())).getScaledInstance(100, 100, 0));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JLabel tempDish = new JLabel(dish.getName(), ic, JLabel.CENTER);
			tempDish.setVerticalTextPosition(JLabel.BOTTOM);
			tempDish.setHorizontalTextPosition(JLabel.CENTER);
			
			switch(dish.getType()){
				case 1:
					starterPanel.add(tempDish);
					break;
				case 2:
					mainPanel.add(tempDish);
					break;
				case 3:
					desertPanel.add(tempDish);
			}
		}

		add(leftPanel);

////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// Create rightPanel
		JPanel rightPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.EAST, leftPanel, -6, SpringLayout.WEST, rightPanel);
		springLayout.putConstraint(SpringLayout.WEST, rightPanel, -250, SpringLayout.EAST, this);

		springLayout.putConstraint(SpringLayout.EAST, rightPanel, -10, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, rightPanel, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, rightPanel, -10, SpringLayout.SOUTH, this);
		this.add(rightPanel);
		SpringLayout sl_rightPanel = new SpringLayout();
		rightPanel.setLayout(sl_rightPanel);

		JLabel nopLabel = new JLabel("Number of people:");
		sl_rightPanel.putConstraint(SpringLayout.WEST, nopLabel, 10, SpringLayout.WEST, rightPanel);
		sl_rightPanel.putConstraint(SpringLayout.NORTH, nopLabel, 40, SpringLayout.NORTH, rightPanel);
		rightPanel.add(nopLabel);

		JSpinner guestCountSpinner = new JSpinner();
		guestCountSpinner.setValue(model.getNumberOfGuests());
		sl_rightPanel.putConstraint(SpringLayout.NORTH, guestCountSpinner, 40, SpringLayout.NORTH, rightPanel);
		sl_rightPanel.putConstraint(SpringLayout.EAST, guestCountSpinner, -10, SpringLayout.EAST, rightPanel);
		sl_rightPanel.putConstraint(SpringLayout.WEST, guestCountSpinner, 50, SpringLayout.EAST, nopLabel);
		rightPanel.add(guestCountSpinner);

		JLabel costTagLabel = new JLabel("Total cost:");
		sl_rightPanel.putConstraint(SpringLayout.NORTH, costTagLabel, 20, SpringLayout.SOUTH, nopLabel);
		sl_rightPanel.putConstraint(SpringLayout.WEST, costTagLabel, 10, SpringLayout.WEST, rightPanel);
		rightPanel.add(costTagLabel);

		JLabel costLabel = new JLabel("$ "+model.getTotalMenuPrice());
		sl_rightPanel.putConstraint(SpringLayout.NORTH, costLabel, 0, SpringLayout.NORTH, costTagLabel);
		sl_rightPanel.putConstraint(SpringLayout.EAST, costLabel, -20, SpringLayout.EAST, rightPanel);
		rightPanel.add(costLabel);

		JLabel dinnerMenuLabel = new JLabel("Dinner menu", JLabel.CENTER);
		sl_rightPanel.putConstraint(SpringLayout.NORTH, dinnerMenuLabel, 20, SpringLayout.SOUTH, costTagLabel);
		sl_rightPanel.putConstraint(SpringLayout.WEST, dinnerMenuLabel, 10, SpringLayout.WEST, rightPanel);
		sl_rightPanel.putConstraint(SpringLayout.EAST, dinnerMenuLabel, -10, SpringLayout.EAST, rightPanel);
		dinnerMenuLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rightPanel.add(dinnerMenuLabel);
		
		
		
		JLabel[] dishIcon = new JLabel[model.getSelectedCount()];
		JLabel[] lblNewLabel = new JLabel[model.getSelectedCount()];
		JButton[] btnNewButton = new JButton[model.getSelectedCount()];
		
		for(int i = 0; i < model.getSelectedCount(); i++){
			dishIcon[i] = new JLabel(new ImageIcon("images/"+model.getSelectedDishes()[i].getImage()));
			lblNewLabel[i] = new JLabel("<html>"+Dish.getDishTypeName(model.getSelectedDishes()[i].getType())+": "+model.getSelectedDishes()[i].getName()
					+"<br>Cost: $ "+model.getDishPrice(model.getSelectedDishes()[i])+"</html>");
			btnNewButton[i] = new JButton(new ImageIcon("images/delete_icon.gif"));
		
		
			sl_rightPanel.putConstraint(SpringLayout.NORTH, dishIcon[i], i==0?20:15, SpringLayout.SOUTH, i==0?dinnerMenuLabel:dishIcon[i-1]);
			sl_rightPanel.putConstraint(SpringLayout.SOUTH, dishIcon[i], i==0?60:55, SpringLayout.SOUTH, i==0?dinnerMenuLabel:dishIcon[i-1]);
			sl_rightPanel.putConstraint(SpringLayout.WEST, dishIcon[i], 10, SpringLayout.WEST, rightPanel);
			sl_rightPanel.putConstraint(SpringLayout.EAST, dishIcon[i], 50, SpringLayout.WEST, rightPanel);
			rightPanel.add(dishIcon[i]);
			
			sl_rightPanel.putConstraint(SpringLayout.NORTH, lblNewLabel[i], 0, SpringLayout.NORTH, dishIcon[i]);
			sl_rightPanel.putConstraint(SpringLayout.SOUTH, lblNewLabel[i], 0, SpringLayout.SOUTH, dishIcon[i]);
			sl_rightPanel.putConstraint(SpringLayout.WEST, lblNewLabel[i], 10, SpringLayout.EAST, dishIcon[i]);
			sl_rightPanel.putConstraint(SpringLayout.EAST, lblNewLabel[i], -10, SpringLayout.WEST, btnNewButton[i]);
			rightPanel.add(lblNewLabel[i]);
			
			sl_rightPanel.putConstraint(SpringLayout.NORTH, btnNewButton[i], 5, SpringLayout.NORTH, lblNewLabel[i]);
			sl_rightPanel.putConstraint(SpringLayout.SOUTH, btnNewButton[i], -5, SpringLayout.SOUTH, lblNewLabel[i]);
			sl_rightPanel.putConstraint(SpringLayout.EAST, btnNewButton[i], -10, SpringLayout.EAST, rightPanel);
			sl_rightPanel.putConstraint(SpringLayout.WEST, btnNewButton[i], -50, SpringLayout.EAST, rightPanel);
			rightPanel.add(btnNewButton[i]);
		}
		
		
		
		JButton preparationButton = new JButton("Preparation");
		sl_rightPanel.putConstraint(SpringLayout.WEST, preparationButton, 10, SpringLayout.WEST, rightPanel);
		sl_rightPanel.putConstraint(SpringLayout.SOUTH, preparationButton, -10, SpringLayout.SOUTH, rightPanel);
		rightPanel.add(preparationButton);
		
		JButton ingredientsButton = new JButton("Ingredients");
		sl_rightPanel.putConstraint(SpringLayout.EAST, ingredientsButton, -10, SpringLayout.EAST, rightPanel);
		sl_rightPanel.putConstraint(SpringLayout.SOUTH, ingredientsButton, -10, SpringLayout.SOUTH, rightPanel);
		rightPanel.add(ingredientsButton);
	}
}
