package edu.brandeis.cosi12b.streamdemo;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {

  static class Book {
    String title;

    Book(String title) {
      this.title = title;
    }
  }

  @FunctionalInterface
  public interface IAddable<T> {
    public T add(T t1, T t2);
  }

  public static void main(String args[]) {
    // example1();
    // example2();
    // example3();
      example4();
    // example5();
    // example6();
    // example7();
    // example8();
    // example9();
    // example10();
    // example11();
    // example12();
    // example13();
    // example14();
  }

  public static void example1() {
    List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
    System.out.println("List: " + strings);

    // Count empty strings
    long count = strings.stream().filter(string -> string.isEmpty()).count();
    System.out.println("Empty Strings: " + count);

    count = strings.stream().filter(string -> string.length() == 3).count();
    System.out.println("Strings of length 3: " + count);

    List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
    System.out.println("Filtered List: " + filtered);

    String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
    System.out.println("Merged String: " + mergedString);
    List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
    List<Integer> integers = Arrays.asList(1, 2, 13, 4, 15, 6, 17, 8, 19);

    List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
    System.out.println("Squares List: " + squaresList);
    System.out.println("List: " + integers);

    IntSummaryStatistics stats = integers.stream().mapToInt((x) -> x).summaryStatistics();

    System.out.println("Highest number in List : " + stats.getMax());
    System.out.println("Lowest number in List : " + stats.getMin());
    System.out.println("Sum of all numbers : " + stats.getSum());
    System.out.println("Average of all numbers : " + stats.getAverage());
    System.out.println("Random Numbers: ");

    Random random = new Random();
    random.ints().limit(10).sorted().forEach(System.out::println);

    // parallel processing
    count = strings.parallelStream().filter(string -> string.isEmpty()).count();
    System.out.println("Empty Strings: " + count);
  }

  public static void example2() {
    List<Book> library = new ArrayList<>();
    library.add(new Book("Book Two"));
    library.add(new Book("Book one"));
    List<Book> myList = new ArrayList<>();
    library.stream().forEach(e -> myList.add(e));
    System.out.println(library);
  }

  public static void example3() {
    IAddable<String> stringAdder = (String s1, String s2) -> s1 + s2;
    System.out.println("Concatenated Result: " + stringAdder.add("a", "b"));
  }

  public static void example4() {
    Stream.of("Edgecomb", "Fan", "Felig", "Flores", "Gold", "Goncalves Dos Santos",
        "Hakakian", "Hechtman")
      .map(s -> s.toUpperCase())
      .filter(s -> (s.length() > 5))
      .sorted()
      .forEach(s -> System.out.println(" " + s));

  }

  public static void example5() {
    System.out.println(
        Arrays.asList(12, 100, 41, 9, -5, 3001)
        .stream()
        .reduce(Integer.MAX_VALUE, (accum, val) -> (val < accum) ? val : accum));
  }
  
  public static void example6() {
    int[] nums = {3, -4, 8, 4, -2, 17, 9, -10, 14, 6, -12};
    int sum = Arrays.stream(nums)
        .filter(w -> {System.out.print(" " + w); return true;})
        .map(n -> Math.abs(n))
        .filter(w -> {System.out.print(" " + w); return true;})
        .filter(n -> n % 2 == 0)
        .distinct()
        .sum();
    System.out.println(sum);
  }
  
  public static void example7() {
    int[] nums = {3, -4, 8, 4, -2, 17, 9, -10, 14, 6, -12};
    int[] sublist = Arrays.stream(nums)
        .map(n -> Math.abs(n))
        .filter(n -> n % 2 == 0)
        .distinct()
        .toArray();
    System.out.println(Arrays.toString(sublist));
  }
  
  public static void example8() {
    List<String>myList = Arrays.asList("Dahlia", "Alexios","Austin","Rachel","Ian","Sylvia","Jeremy");
    int sum = myList
        .stream()
        .mapToInt(String::length)
        .sum();
    System.out.println(sum);
   
  }
  
  public static void example9() {
      int sum = Stream.of("Dahlia", "Alexios","Austin","Rachel","Ian","Sylvia","Jeremy")
          .mapToInt(String::length)
          .sum();
      System.out.println(sum);
  }
  
  public static void example10() {
    Stream.of("a1", "a2", "b1", "b2", "c")
       .filter(s -> {
           System.out.println("filter: " + s);
           return true;
        })
      .forEach(s -> System.out.println("forEach: " + s));
  }
  
  public static void example11() {
    System.out.println(">>> Example11");
    Stream.of("Dahlia", "Alexios","Austin","Rachel","Ian","Sylvia","Jeremy")
        .filter (s -> { System.out.println("Filter: " + s); return s.startsWith("A");})
        .map    (s -> { System.out.println("Map: " + s);    return s.toUpperCase();})
        .forEach(s -> System.out.println("Foreach: " + s));
}

  public static void example12() {
    System.out.println(">>> Example12");
    Stream.of("Dahlia", "Alexios","Austin","Rachel","Ian","Sylvia","Jeremy")
        .map    (s -> { System.out.println("Map: " + s);    return s.toUpperCase();})
        .filter (s -> { System.out.println("Filter: " + s); return s.startsWith("A");})
        .forEach(s -> System.out.println("Foreach: " + s));
  }
  
  public static void example13() {
    System.out.println("Filter, Map, Foreach: ");
    Stream.of("Dahlia", "Alexios","Austin","Rachel","Ian","Sylvia","Jeremy")
      .filter (s -> s.startsWith("A"))
      .map    (s -> s.toUpperCase())
      .forEach(s -> System.out.print(" " + s));
    System.out.println("\n\nMap, Filter, Foreach: ");
    Stream.of("Dahlia", "Alexios","Austin","Rachel","Ian","Sylvia","Jeremy")
    .map    (s -> s.toUpperCase())
    .filter (s -> s.startsWith("A"))
    .forEach(s -> System.out.print(" " + s));
  }
  
  private static void calcCell(int[][] arr2d, BinaryOperator<Integer> cellSetter) {
    for (int i = 0; i < arr2d.length; i++) {
      for (int j = 0; j < arr2d[i].length; j++) {
        arr2d[i][j] =  cellSetter.apply(i, j);
      }
    }
  }
  
  private static void visitCell(int[][] arr2d, IntConsumer cellVisitor) {
    for (int i = 0; i < arr2d.length; i++) {
      for (int j = 0; j < arr2d[i].length; j++) {
        cellVisitor.accept(arr2d[i][j]);
      }
    }
  }
  
  public static void example14() {
    int[][] values = new int[10][10];
    calcCell(values, (x,y) -> x*100+y);
    visitCell(values, i -> System.out.println(i));
  }
}