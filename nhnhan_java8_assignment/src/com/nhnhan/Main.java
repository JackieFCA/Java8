package com.nhnhan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {
	private static final String JAVA = "Java";
	private static final String DOT_NET = ".NET";
	
	private static Function<Integer, Integer> randomFunction = max -> new Random().nextInt(max + 1);
	private static Supplier<Integer> randomSupplier = () -> new Random().nextInt(10);
	private static BiFunction<Integer, Integer, Integer> randomBiFunction = (min, max) -> new Random().nextInt((max + 1) - min);
	
	private static Consumer<Dev> devDetail = dev -> System.out.println("Name:" + dev.getName() + " - Skill: " + dev.getSkill() + " - exp: " + dev.getExp());
	private static Consumer<Dev2> devDetail2 = dev -> System.out.println("Name:" + dev.getName() + " Skill: "
			+ dev.getSkillList().get(0).getSkill() + " exp: " + dev.getSkillList().get(0).getExp() + " Skill: "
			+ dev.getSkillList().get(1).getSkill() + " exp: " + dev.getSkillList().get(1).getExp());

	public static void main(String[] args) {
//		exercise1();
//		exercise2();
//		exercise3();
//		exercise4();
//		exercise5();
		
//		exercise1_1();
//		exercise2_1();
//		exercise3_1();
		exercise4_1();
	}

	/**
	 * Get devs who has Java skill and exp >= 3
	 */
	public static void exercise1() {
		List<Dev> devs = createData();
		System.out.println("=========== Before ==========");
		devs.forEach(devDetail);
		
		devs = devs.stream().filter(dev -> dev.getSkill().equals(JAVA) && dev.getExp() >= 3).collect(Collectors.toList());
		
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
		
		devs = devs.stream().filter(dev -> dev.getSkill().equals(JAVA) && dev.getExp() >= 3)
				.sorted((dev1, dev2) -> dev1.getExp() - dev2.getExp())
				.skip(4).limit(6).collect(Collectors.toList());
		
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
	
	/**
	 * List out all devs have exp, exp >= 5 or none exp
	 */
	public static void exercise4() {
		List<Dev> devs = createData();
		System.out.println("=========== Before ==========");
		devs.forEach(devDetail);
		
		// All devs have exp
		boolean hasExp = devs.stream().parallel().allMatch(dev -> dev.getExp() >= 0);
		
		// All devs have exp >= 5
		boolean expGE5 = devs.stream().parallel().anyMatch(dev -> dev.getExp() >= 5);
		
		// All devs have none exp
		boolean noneExp = devs.stream().parallel().noneMatch(dev -> dev.getExp() != 0);
		
		System.out.println("\n=========== After ==========");
		System.out.println("All devs have exp: " + hasExp);
		System.out.println("All devs have exp >= 5: " + expGE5);
		System.out.println("All devs have none exp: " + noneExp);
	}
	
	public static void exercise5() {
		List<Dev> devs = createData();
		System.out.println("=========== Before ==========");
		devs.forEach(devDetail);
		
		System.out.println("\n=========== After ==========");
		// list out numbers of dev per skill
		Map<String, Long> numbersPerSkill = devs.stream().collect(Collectors.groupingBy(Dev::getSkill, Collectors.counting()));
        numbersPerSkill.forEach((key,value) -> System.out.println("Skill: " + key + " total:" + value));
        
		// list out average experience per skill
        Map<String, Double> averageExp = devs.stream().collect(Collectors.groupingBy(Dev::getSkill, Collectors.averagingDouble(Dev::getExp)));
        averageExp.forEach((key,value) -> System.out.println("Skill: " + key + " average exp:" + value));
        
		// list out experience number and numbers dev
        Map<Integer, Long> statisticExp = devs.stream().collect(Collectors.groupingBy(Dev::getExp, Collectors.counting()));
        statisticExp.forEach((key,value) -> System.out.println("Exp: " + key + " total:" + value));
        
		// list out numbers dev per skill
        Map<String, Long> statisticExpAndSkill = devs.stream().collect(Collectors.groupingBy(dev -> dev.getSkill() + " with " + dev.getExp() + " exp", Collectors.counting()));
        statisticExpAndSkill.forEach((key,value) -> System.out.println("Dev " + key + " total:" + value));
        
		// list out highest exp dev per skill
        Map<String, Optional<Dev>> statisticSkillAndMaxExp = devs.stream().collect(Collectors.groupingBy(dev -> dev.getSkill(), Collectors.maxBy(Comparator.comparing(Dev::getExp))));
        statisticSkillAndMaxExp.forEach((key,value) -> System.out.println(key + ": " + value.get().getName() + " with " + value.get().getExp() + " exp"));
        
		// list out numbers of Senior, Junior. Senior must be > 5 exp
        Map<Boolean, Long> statisticPartion = devs.stream().collect(Collectors.partitioningBy(dev -> dev.getExp() > 5, Collectors.counting()));
        statisticPartion.forEach((key,value) -> System.out.println((key? "Senior: ":"Junior: ") + value));
	}
	
	public static void exercise1_1() {
		List<Dev2> devs = createData2();
		System.out.println("=========== Before ==========");
		devs.forEach(devDetail2);
		
		Predicate<Skill> javaSkill = skill -> skill.getSkill().equals(JAVA);
		Predicate<Skill> expGE3 = skill -> skill.getExp() >= 3;
		Predicate<Skill> javaExp3 = skill -> javaSkill.test(skill) && expGE3.test(skill);
		
		List<Dev2> result = devs.stream().filter(dev -> {
			List<Skill> skills = dev.getSkillList();
			skills = skills.stream().filter(javaExp3).collect(Collectors.toList());
			return !skills.isEmpty();
		}).collect(Collectors.toList());
		
		System.out.println("\n=========== After ==========");
		result.forEach(devDetail2);
	}
	
	public static void exercise2_1() {
		List<Dev2> devs = createData2();
		System.out.println("=========== Before ==========");
		devs.forEach(devDetail2);
		
		// get top 5 devs who has Java Skill and exp >= 3 order by exp desc
		Predicate<Skill> javaSkill = skill -> skill.getSkill().equals(JAVA);
		Predicate<Skill> expGE3 = skill -> skill.getExp() >= 3;
		Predicate<Skill> javaExp3 = skill -> javaSkill.test(skill) && expGE3.test(skill);
		
		List<Dev2> result = devs.stream().filter(dev -> {
			List<Skill> skills = dev.getSkillList();
			skills = skills.stream().filter(javaExp3).collect(Collectors.toList());
			return !skills.isEmpty();
		}).collect(Collectors.toList());

		Collections.sort(result, (dev1, dev2) -> {
			int javaExpDev1 = dev1.getSkillList().stream().filter(javaSkill).findFirst().map(skill -> skill.getExp()).get();
			int javaExpDev2 = dev2.getSkillList().stream().filter(javaSkill).findFirst().map(skill -> skill.getExp()).get();
			return javaExpDev2 - javaExpDev1;
		});
		
		System.out.println("\n=========== After ==========");
		result.stream().limit(5).forEach(devDetail2);
	}
	
	public static void exercise3_1() {
		List<Dev2> devs = createData2();
		System.out.println("=========== Before ==========");
		devs.forEach(devDetail2);
		
		// list out highest, lowest, average experience of devs
		IntSummaryStatistics summary = devs.stream().filter(dev -> true)
				.map(dev -> dev.getSkillList().stream().max((java, net) -> java.getExp() - net.getExp()).get())
				.mapToInt(skill -> skill.getExp())
				.summaryStatistics();
		
		int highestExp = summary.getMax();
		int lowestExp = summary.getMin();
		double averageExp = summary.getAverage();
		
		System.out.println("\n=========== After ==========");
		System.out.println("Highest exp in devs : " + highestExp);
        System.out.println("Lowest exp in devs : " + lowestExp);
        System.out.println("Average exp of all devs : " + averageExp);
	}
	
	public static void exercise4_1() {
		List<Dev2> devs = createData2();
		System.out.println("=========== Before ==========");
		devs.forEach(devDetail2);
		
		// All devs have exp
		boolean hasExp = devs.stream().parallel().anyMatch(
					dev -> dev.getSkillList().stream().parallel()
					.anyMatch(skill -> skill.getExp() > 0)
				);
		
		// All devs have exp >= 5
		boolean expGE5 = devs.stream().parallel().anyMatch(
					dev -> dev.getSkillList().stream().parallel()
					.anyMatch(skill -> skill.getExp() >= 5)
				);
		
		// All devs have none exp
		boolean noneExp = devs.stream().parallel().allMatch(
					dev -> dev.getSkillList().stream().parallel()
					.allMatch(skill -> skill.getExp() == 0)
				);
		
		System.out.println("\n=========== After ==========");
		System.out.println("All devs have exp: " + hasExp);
		System.out.println("All devs have exp >= 5: " + expGE5);
		System.out.println("All devs have none exp: " + noneExp);
	}
	
	private static List<Dev> createData() {
		List<Dev> data = new ArrayList<>();
		for (int i = 1; i < 40; i++) {
			Dev dev = new Dev(i, "Dev " + i, i % 2 == 0 ? JAVA : DOT_NET, randomFunction.apply(9));
			data.add(dev);
		}
		return data;
	}

	private static List<Dev2> createData2() {
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
