package se.kth.csc.iprog.dinnerplanner;

import java.io.IOException;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JFrame;

import se.kth.csc.iprog.dinnerplanner.model.*;
import se.kth.csc.iprog.dinnerplanner.view.*;


public class DinnerPlanner extends JFrame {
	
	private DinnerModel model = new DinnerModel();

	public DinnerModel getModel() {
		return model;
	}

	public void setModel(DinnerModel model) {
		this.model = model;
	}
	
	public DinnerPlanner(){
		
	}

	public static void main(String[] args) throws IOException {
		//Initiating the main JFrame
		DinnerPlanner dinnerPlanner = new DinnerPlanner();
		dinnerPlanner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dinnerPlanner.setResizable(false);
		
		//test
		dinnerPlanner.setTitle("Dinner Planner");
			
		//Getting some dishes for setup
		Dish[] dishes = new Dish[dinnerPlanner.getModel().getDishes().size()];
		int i = 0;
		for(Dish dish : dinnerPlanner.model.getDishes())
			{dishes[i]=dish;i++;}
		
		
		/* ******************************************************************
		 * HERE FOLLOWS THE VIEWS, UNCOMMENT ONE AT A TIME TO RUN
		 * ******************************************************************/
		
		///StartView///////////////////////////////////////////////////////
//		JComponent view = new StartView(dinnerPlanner.getModel());
		///END OF StartView////////////////////////////////////////////////
		
		
		
		///DishView////////////////////////////////////////////////////////
//		JComponent view = new DishView(dinnerPlanner.getModel(), dishes[1]);
		///END OF DishView/////////////////////////////////////////////////
		
		
		
		///StarterChoosenView//////////////////////////////////////////////
//			//ADD STARTER TO MENU:
//			for(Dish dish : dishes)
//				if(dish.getType() == Dish.STARTER){
//					dinnerPlanner.getModel().selectDish(dish);
//					break;
//				}
//		JComponent view = new StartView(dinnerPlanner.getModel());
		///END OF StarterChoosenView///////////////////////////////////////
		
		
		
		///FullMenuView////////////////////////////////////////////////////
//			ADD FULL MENU:
//			boolean[] choosenFlag = new boolean[3];
//			for(Dish dish : dishes)
//				if(dish.getType() == Dish.STARTER && !choosenFlag[Dish.STARTER-1]){
//					dinnerPlanner.getModel().selectDish(dish);
//				}else if(dish.getType() == Dish.MAIN && !choosenFlag[Dish.MAIN-1]){
//					dinnerPlanner.getModel().selectDish(dish);
//				}else if(dish.getType() == Dish.DESERT && !choosenFlag[Dish.DESERT-1]){
//					dinnerPlanner.getModel().selectDish(dish);
//				}else{
//					break;
//				}
//		
//		JComponent view = new StartView(dinnerPlanner.getModel());
		///END OF FullMenuView/////////////////////////////////////////////

		
		
		///IngredientView//////////////////////////////////////////////////
//		JComponent view = new IngredientView(dishes[1].getIngredients());
		///END OF IngredientView///////////////////////////////////////////		

		
		
		///PreparationView[1] (FOR SINGLE DISH)////////////////////////////
//		JComponent view = new PreparationView(dishes[1]);
		///END OF PreparationView[1]///////////////////////////////////////		
		
		
		
		///PreparationView[2] (FOR MENU)///////////////////////////////////
		//ADD FULL MENU:
		boolean[] choosenFlag = new boolean[3];
		for(Dish dish : dishes)
			if(dish.getType() == Dish.STARTER && !choosenFlag[Dish.STARTER-1]){
				dinnerPlanner.getModel().selectDish(dish);
			}else if(dish.getType() == Dish.MAIN && !choosenFlag[Dish.MAIN-1]){
				dinnerPlanner.getModel().selectDish(dish);
			}else if(dish.getType() == Dish.DESERT && !choosenFlag[Dish.DESERT-1]){
				dinnerPlanner.getModel().selectDish(dish);
			}else{
				break;
			}
	
		JComponent view = new PreparationView(dinnerPlanner.getModel());
		///END OF PreparationView[2]///////////////////////////////////////
		
		
		
		//Setting the bounds of the frame to the one specified in the view
		dinnerPlanner.setBounds(view.getBounds());
		
		//Adding the view to the main JFrame
		dinnerPlanner.getContentPane().add(view);
		
//		dinnerPlanner.pack();
				
		//and starting the JFrame
		dinnerPlanner.setVisible(true);
	}
}
