package org.baize;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeightRandom {
    static List<WeightCategory> categorys = new ArrayList<WeightCategory>();
	private static Random random = new Random();
	
	public static void initData() {
		WeightCategory wc1 = new WeightCategory("A",1);
		WeightCategory wc2 = new WeightCategory("B",1);
		WeightCategory wc3 = new WeightCategory("C",1);
		WeightCategory wc4 = new WeightCategory("D",1);
		WeightCategory wc5 = new WeightCategory("E",1);
		WeightCategory wc6 = new WeightCategory("F",1);
		WeightCategory wc7 = new WeightCategory("G",2);
		WeightCategory wc8 = new WeightCategory("H",2);
		WeightCategory wc9 = new WeightCategory("I",3);
		WeightCategory wc10 = new WeightCategory("J",3);
		WeightCategory wc11 = new WeightCategory("K",12);
		WeightCategory wc12 = new WeightCategory("L",12);
		WeightCategory wc13 = new WeightCategory("M",12);
		WeightCategory wc14 = new WeightCategory("N",12);
		WeightCategory wc15 = new WeightCategory("O",12);
		WeightCategory wc16 = new WeightCategory("P",12);
		categorys.add(wc1);
		categorys.add(wc2);
		categorys.add(wc3);
		categorys.add(wc4);
		categorys.add(wc5);
		categorys.add(wc6);
		categorys.add(wc7);
		categorys.add(wc8);
		categorys.add(wc9);
		categorys.add(wc10);
		categorys.add(wc11);
		categorys.add(wc12);
		categorys.add(wc13);
		categorys.add(wc14);
		categorys.add(wc15);
		categorys.add(wc16);
	}

	public static void main(String[] args) {
		  initData();

		  for(int i = 0;i<100;i++) {
			  Integer weightSum = 0;
			  for (WeightCategory wc : categorys) {
				  weightSum += wc.getWeight();
			  }

			  if (weightSum <= 0) {
				  System.err.println("Error: weightSum=" + weightSum.toString());
				  return;
			  }
			  Integer n = random.nextInt(weightSum); // n in [0, weightSum)
			  Integer m = 0;
			  for (WeightCategory wc : categorys) {
				  if (m <= n && n < m + wc.getWeight()) {
					  System.out.println("This Random Category is " + wc.getCategory());
					  break;
				  }
				  m += wc.getWeight();
			  }
		  }
		  
	}

}

class WeightCategory {
	private String category;
	private Integer weight;
	

	public WeightCategory() {
		super();
	}

	public WeightCategory(String category, Integer weight) {
		super();
		this.setCategory(category);
		this.setWeight(weight);
	}


	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}