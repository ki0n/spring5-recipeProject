package guru.springframework.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repository.CategoryRepository;
import guru.springframework.repository.RecipeRepository;
import guru.springframework.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent>{

	private final CategoryRepository categoryRepository;
	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;

	public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository,
			UnitOfMeasureRepository unitOfMeasureRepository) {
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		recipeRepository.saveAll(getRecipes());
		log.debug("Loading Bootstrap Data");
	}

	private List<Recipe> getRecipes(){
		List<Recipe> recipes = new ArrayList<>(2);
		
		//get UOMs
//		Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
//		
//		if (!eachUomOptional.isPresent()) {
//			throw new RuntimeException("Exception OUM not Found");
//		}
		
		Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
		
		if(!tableSpoonUomOptional.isPresent()) {
			throw new RuntimeException("Exception OUM not Found");	
		}
		
		Optional<UnitOfMeasure> teaSpoonOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
		
		//--
		if (!teaSpoonOptional.isPresent()) {
			throw new RuntimeException("Exception OUM not Found");
		}
		
//		Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
//		
//		if(!dashUomOptional.isPresent()) {
//			throw new RuntimeException("Exception OUM not Found");	
//		}
		
//		Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
//		
//		if (!pintUomOptional.isPresent()) {
//			throw new RuntimeException("Exception OUM not Found");
//		}
//		
		Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");
		
		if (!cupsUomOptional.isPresent()) {
			throw new RuntimeException("Exception UOM not Found");
		}
		
		
		//get Optionals
		UnitOfMeasure eachUom = tableSpoonUomOptional.get();
		UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
		UnitOfMeasure teapoonUom = teaSpoonOptional.get();
		UnitOfMeasure dashUom = teaSpoonOptional.get();
		UnitOfMeasure pintUom = teaSpoonOptional.get();
		UnitOfMeasure coupsUom = cupsUomOptional.get();
		
		//get Categories
		Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
		if(!americanCategoryOptional.isPresent()) {
			throw new RuntimeException("Exception Category Not Found");
		}
		
		Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
		
		if (!mexicanCategoryOptional.isPresent()) {
			throw new RuntimeException("Exception Category Not Found");
		}
		
		Category americanCategory = americanCategoryOptional.get();
		Category mexicanCategory = mexicanCategoryOptional.get();
		
		//Yumy Guac
		Recipe guacRecipe = new Recipe();
		guacRecipe.setDescription("Perfect Guacamole");
		guacRecipe.setPrepTime(10);
		guacRecipe.setCookTime(0);
		guacRecipe.setDifficulty(Difficulty.EASY);
		guacRecipe.setDireccions("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl."+
		"\n" + 
		"2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)"+
		"\n" +
		"3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\r\n" + 
		"\r\n" + 
		"Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\r\n" + 
		"\r\n" + 
		"Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste."+
		"\n"+
		"4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\r\n" + 
		"\r\n" + 
		"Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving."+
		"\n"+
		"\n"+
		"Read more: http://www.simplerecipes.com/recipes/perfect_guacamole/#ixzz4jpvpiV9Sd");
		
		Notes guacNotes = new Notes();
		guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\r\n" + 
				"\r\n" + 
				"Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\r\n" + 
				"\r\n" + 
				"The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\r\n" + 
				"\r\n" + 
				"To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\r\n" + 
				"\r\n" + 
				"For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!"+
				"\n"+
				"\n"+
				"Read more: http://www.simplerecipes.com/recipes/perfect_guacamole/#ixzz4jpvpiV9Sd");
		
		guacNotes.setRecipe(guacRecipe);
		guacRecipe.setNotes(guacNotes);
		
		guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
		guacRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), teapoonUom));
		guacRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tableSpoonUom));
		guacRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUom));
		guacRecipe.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom));
		guacRecipe.addIngredient(new Ingredient("cilantro", new BigDecimal(2), tableSpoonUom));
		guacRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUom));
		guacRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUom));
		
		guacRecipe.getCategories().add(americanCategory);
		guacRecipe.getCategories().add(mexicanCategory);
		
		//guacRecipe.setNotes(guacNotes);
		
		guacRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
		guacRecipe.setServings(4);
		guacRecipe.setSource("Simple Recipes");
		
		//add to return list
		recipes.add(guacRecipe);
		
		//Yummy Tacos
		Recipe tacosRecipe = new Recipe();
		tacosRecipe.setDescription("Spicy Frilled Chicken Taco");
		tacosRecipe.setCookTime(9);
		tacosRecipe.setPrepTime(20);
		tacosRecipe.setDifficulty(Difficulty.MODERATE);
		
		tacosRecipe.setDireccions("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
		"2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\r\n" + 
		"\r\n" + 
		"Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n"+
		"\n"+
		"\n"+
		"3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n"+
		"4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\r\n" + 
		"\r\n" + 
		"Wrap warmed tortillas in a tea towel to keep them warm until serving.\n"+
		"5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n"+
		"\n"+
		"\n"+
		"Read more: http://www.simplerecipes.com/recipes/perfect_guacamole/#ixzz4jpvpiV9Sd");
		
		Notes tacoNotes = new Notes();
		tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n"+
		"Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n"+
		"Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n"+
		"First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n"+
		"Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n"+
		"\n"+
		"\n"+
		"Read more: http://www.simplerecipes.com/recipes/perfect_guacamole/#ixzz4jpvpiV9Sd");
		
		tacoNotes.setRecipe(tacosRecipe);
		tacosRecipe.setNotes(tacoNotes);
		
		tacosRecipe.getIngredients().add(new Ingredient("Ancho chili powder", new BigDecimal(2), tableSpoonUom, tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("Dried oregano", new BigDecimal(1), teapoonUom, tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("Dried cumin", new BigDecimal(1), teapoonUom, tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("Sugar", new BigDecimal(1), teapoonUom, tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("Salt", new BigDecimal(".5"), teapoonUom,tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("Clove garlic, finely chopped", new BigDecimal(1), eachUom,tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("Finely grated orange zest", new BigDecimal(1), tableSpoonUom,tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("Fresh-squeezed orange juice", new BigDecimal(3), tableSpoonUom,tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("Olive oil", new BigDecimal(2), tableSpoonUom,tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("Boneless chicken thighs", new BigDecimal(4), tableSpoonUom,tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("Small corn tortillas", new BigDecimal(8), eachUom,tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("Packed baby arugula ", new BigDecimal(3), coupsUom,tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("Medium ripe avocados, sliced ", new BigDecimal(2), eachUom,tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("Radishes, thinly sliced ", new BigDecimal(4), eachUom,tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("Cherry tomatoes, halved ", new BigDecimal(".5"), pintUom,tacosRecipe));
		tacosRecipe.getIngredients().add(new Ingredient("red onion, thinly sliced ", new BigDecimal(".25"), eachUom,tacosRecipe));
		
		tacosRecipe.getCategories().add(americanCategory);
		tacosRecipe.getCategories().add(mexicanCategory);
		
		recipes.add(tacosRecipe);
		return recipes;
	}

}
