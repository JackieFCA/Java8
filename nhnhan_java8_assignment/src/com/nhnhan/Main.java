package com.nhnhan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {
	private static final String JAVA = "Java";
	private static final String DOT_NET = ".NET";
	
	private static Function<Integer, Integer> randomFunction = max -> new Random().nextInt(max + 1);
	private static Supplier<Integer> randomSupplier = () -> new Random().nextInt(10);
	private static BiFunction<Integer, Integer, Integer> randomBiFunction = (min, max) -> new Random().nextInt((max + 1) - min);
	
	private static Consumer<Dev> devDetail = dev -> System.out.println("Name:" + dev.getName() + " - Skill: " + dev.getSkill() + " - exp: " + dev.getExp());

	public static void main(String[] args) {
//		exercise1();
//		exercise2();
		exercise3();
	}

	/**
	 * Get devs who has Java skill and exp >= 3
	 */
	public static void exercise1() {
		List<Dev> devs = createData();
		System.out.println("=========== Before ==========");
		devs.forEach(devDetail);
		
		devs = devs.stream().filter(dev -> {
			return dev.getSkill().equals(JAVA) && dev.getExp() >= 3;
		}).collect(Collectors.toList());
		
		System.out.println("\n=========== After ==========");
		devs.forEach(devDetail);
	}
	
	/**
	 * Get top 5-10 devs who has Java Skill and exp >= 3 order by exp asc
	 */
	public static void exercise2() {
		List<Dev> devs = createData();
		System.out.println("=========== Before ==========");
		devs.forEach(devDetail);
		
		devs = devs.stream().filter(dev -> {
			return dev.getSkill().equals(JAVA) && dev.getExp() >= 3;
		}).sorted((dev1, dev2) -> {
			return dev1.getExp() - dev2.getExp();
		}).skip(5).limit(5).collect(Collectors.toList());
		
		System.out.println("\n=========== After ==========");
		devs.forEach(devDetail);
	}
	
	/**
	 * List out highest, lowest, average experience of devs
	 */
	public static void exercise3() {
		List<Dev> devs = createData();
		System.out.println("=========== Before ==========");
		devs.forEach(devDetail);
		
		// highest
		int highestExp = devs.stream().filter(dev -> true).sorted((dev1, dev2) -> {
			return dev2.getExp() - dev1.getExp();
		}).limit(1).collect(Collectors.toList()).get(0).getExp();
		
		// lowest
		int lowestExp = devs.stream().filter(dev -> true).sorted((dev1, dev2) -> {
			return dev1.getExp() - dev2.getExp();
		}).limit(1).collect(Collectors.toList()).get(0).getExp();
		
		// average
		double averageExp = devs.stream().filter(dev -> true).mapToInt(dev -> dev.getExp()).average().getAsDouble();
		
		System.out.println("\n=========== After ==========");
		System.out.println("Highest experience: " + highestExp);
		System.out.println("Lowest experience: " + lowestExp);
		System.out.printf("Average experience: %.2f", averageExp);
	}

	private static List<Dev> createData() {
		List<Dev> data = new ArrayList<>();
		for (int i = 1; i < 30; i++) {
			Dev dev = new Dev(i, "Dev " + i, i % 2 == 0 ? JAVA : DOT_NET, randomFunction.apply(9));
			data.add(dev);
		}
		return data;
	}

	private static List<Dev2> data2() {
		List<Dev2> data = new ArrayList<Dev2>();
		for (int i = 1; i <= 9; i++) {
			Dev2 dev = new Dev2(i, "Dev" + i);
			dev.getSkillList().add(new Skill(JAVA, randomBiFunction.apply(0, 9)));
			dev.getSkillList().add(new Skill(DOT_NET, randomSupplier.get()));
			data.add(dev);
		}
		return data;
	}

}
