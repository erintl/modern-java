package com.modernjava.chapter8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.checkerframework.checker.units.qual.K;

public class WorkingWithCollections {
  
  public static void main(String[] args) {
    workingWithLists();
    workingWithMaps();
    computingOnMaps();
    removingFromMaps();
    replacingInMaps();
    mergingMaps();
  }

  public static void workingWithLists() {
    System.out.println("------ Working with Lists ------");

    System.out.println("--> Transforming list items with a stream");
    List<String> referenceCodes = Arrays.asList("a12", "c14", "b13");
    referenceCodes.stream()
      .map(code -> Character.toUpperCase(code.charAt(0)) + code.substring(1))
      .collect(Collectors.toList())
      .forEach(System.out::println);
    System.out.println("... but the original List remains unchanged: " + referenceCodes);

    System.out.println("--> Mutating a List with a ListIterator");
    for (ListIterator<String> iterator = referenceCodes.listIterator(); iterator.hasNext();) {
      String code = iterator.next();
      iterator.set(Character.toUpperCase(code.charAt(0)) + code.substring(1));
    }
    System.out.println("This time it's been changed: " + referenceCodes);

    System.out.println("--> Mutating a List with replaceAll()");
    referenceCodes = Arrays.asList("a12", "c14", "b13");
    System.out.println("Back to the original codes: " + referenceCodes);
    referenceCodes.replaceAll(code -> Character.toUpperCase(code.charAt(0)) + code.substring(1));
    System.out.println("Changed by replaceAll(): " + referenceCodes);
  }

  public static void workingWithMaps() {
    System.out.println("------ Working with Maps ------");

    System.out.println("--> Iterating a map with a for loop");
    Map<String, Integer> ageOfFriends = Map.of("Raphael", 30, "Olivia", 25, "Thibaut", 26);
    for (Map.Entry<String, Integer> entry : ageOfFriends.entrySet()) {
      String friend = entry.getKey();
      Integer age = entry.getValue();
      System.out.printf("%s is %d years old%n", friend, age);
    }

    System.out.println("--> Iterating a Map with forEach()");
    ageOfFriends.forEach((friend, age) -> System.out.printf("%s is %d years old%n", friend, age));

    System.out.println("--> Iterating a map sorted by keys through a Stream");
    Map<String, String> favoriteMovies = Map.ofEntries(
      Map.entry("Raphael", "Star Wars"),
      Map.entry("Cristina", "Matrix"),
      Map.entry("Olivia", "James Bond"));
    favoriteMovies.entrySet().stream()
      .sorted(Entry.comparingByKey())
      .forEachOrdered(System.out::println);

    System.out.println("--> Using getOrDefault()");
    System.out.println(favoriteMovies.getOrDefault("Olivia", "Jaws"));
    System.out.println(favoriteMovies.getOrDefault("Mark", "Jaws"));
  }

  public static void computingOnMaps() {
    System.out.println("------ Computing on Maps ------");

    System.out.println("--> Adding a friend and a movie in a verbose way");
    Map<String, List<String>> friendsToMovies = new HashMap<>();
    String friendName = "Raphael";
    List<String> movies = friendsToMovies.get(friendName);
    if (movies == null) {
      movies = new ArrayList<>();
      friendsToMovies.put(friendName, movies);
    }
    movies.add("Star Wars");
    System.out.println(friendsToMovies);

    System.out.println("--> Adding a friend and a movie using computeIfAbsent()");
    friendsToMovies.clear();
    friendsToMovies.computeIfAbsent("Raphael", name -> new ArrayList<>()).add("Star Wars");
    System.out.println(friendsToMovies);
  }

  public static void removingFromMaps() {
    System.out.println("------ Removing from Maps ------");

    // Mutable Map is required for this example
    Map<String, String> favoriteMovies = new HashMap<>();
    favoriteMovies.put("Raphael", "Jack Reacher 2");
    favoriteMovies.put("Christina", "Matrix");
    favoriteMovies.put("Olivia", "James Bond");
    String key = "Raphael";
    String value = "Jack Reacher 2";

    System.out.println("--> Removing an unwanted entry the cumbersome way");
    boolean result = remove(favoriteMovies, key, value);
    System.out.printf("%s [%b]%n", favoriteMovies, result);

    // Replace deleted map entry
    favoriteMovies.putIfAbsent("Raphael", "Jack Reacher 2");

    System.out.println("--> Removing an unwanted entry the easy way");
    favoriteMovies.remove(key, value);
    System.out.println(favoriteMovies);
  }

  private static <K, V> boolean remove(Map<K, V> targetMap, K key, V value) {
    if (targetMap.containsKey(key) && Objects.equals(targetMap.get(key), value)) {
      targetMap.remove(key);
      return true;
    }
    return false;
  }

  public static void replacingInMaps() {
    System.out.println("------ Replacing in Maps ------");
    Map<String, String> favoriteMovies = new HashMap<>();
    favoriteMovies.put("Raphael", "Star Wars");
    favoriteMovies.put("Olivia", "james bond");

    System.out.println("--> Replacing values in a map with replaceAll()");
    favoriteMovies.replaceAll((key, value) -> value.toUpperCase());
    System.out.println(favoriteMovies);

    favoriteMovies.clear();
    favoriteMovies.put("Raphael", "Star Wars");
    favoriteMovies.put("Olivia", "james bond");

    System.out.println("Replacing a value in a map with the overloaded replace()");
    favoriteMovies.replace("Olivia", "james bond", "Austin Powers");
    System.out.println(favoriteMovies);
  }

  public static void mergingMaps() {
    Map<String, String> family = Map.ofEntries(
      Map.entry("Teo", "Star Wars"),
      Map.entry("Cristina", "James Bond"));
    Map<String, String> friends = Map.ofEntries(Map.entry("Raphael", "Star Wars"));

    System.out.println("--> Merging the old way");
    Map<String, String> everyone = new HashMap<>(family);
    everyone.putAll(friends);
    System.out.println(everyone);

    Map<String, String> friends2 = Map.ofEntries(
      Map.entry("Raphael", "Star Wars"),
      Map.entry("Cristina", "Matrix")
    );
    System.out.println("--> Merging maps using merge()");
    Map<String, String> everyone2 = new HashMap<>(family);
    friends2.forEach((k, v) -> everyone2.merge(k, v, (movie1, movie2) -> movie1 + " & " + movie2));
    System.out.println(everyone2); 

    System.out.println("--> The old way of doing initialization");
    Map<String, Long> movieCounts = new HashMap<>();
    String movieName = "James Bond";
    Long count = movieCounts.get("James Bond");
    if (count == null) {
      movieCounts.put(movieName, 1L);
    } else {
      movieCounts.put(movieName, count + 1L);
    }
    System.out.println(movieCounts);

    System.out.println("--> Initializing using merge()");
    movieCounts.clear();
    movieCounts.merge("James Bond", 1L, (k, v) -> v + 1L);
    System.out.println(movieCounts);
  }
}
