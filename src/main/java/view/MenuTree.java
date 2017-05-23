package view;

public class MenuTree expands Tree{
	
	public MenuTree() {


		// An initial planet tree
		Tree<String> tree = new Tree<>();
		TreeData<String> treeData = new TreeData<>();

		// Couple of childless root items
		treeData.addItem(null,"Mercury");
		treeData.addItem(null,"Venus");

		// Items with hierarchy
		treeData.addItem(null,"Earth");
		treeData.addItem("Earth","The Moon");

		inMemoryDataProvider = new TreeDataProvider<>(treeData);
		tree.setDataProvider(inMemoryDataProvider);
		tree.expand("Earth"); // Expand programmatically


	}
	
	

}
