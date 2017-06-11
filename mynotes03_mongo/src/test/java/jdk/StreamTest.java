package jdk;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import form.Tag;

public class StreamTest {
	@Test
	public void lambdaConstructionTest(){
		String[] tagStrArr = new String[]{"food","cloth","house","drive"};
		Stream<Tag> tagStrm = Arrays.asList(tagStrArr).parallelStream().map(Tag::new);
		
		//tagStrm.forEach(System.out::println);
		Tag[] tagArr = tagStrm.toArray(Tag[]::new);
		Arrays.parallelSetAll(tagArr, x -> {tagArr[x].setId(""+x); return tagArr[x];});
		List<Tag> tagList = Arrays.asList(tagArr);
		tagList.forEach(System.out::println);
	}
	
	@Test
	public void readBook(){
		try {
			URL gwtFileURL = StreamTest.class.getResource("gwt.txt");
			URI gwtURI = gwtFileURL.toURI();
			//System.out.println(gwtURI.toString());
			
			String content = new String(Files.readAllBytes(Paths.get(gwtURI)));
			List<String> words = Arrays.asList(content.split("\\PL+"));
			//words.forEach(System.out::println);
			
			//long count = words.parallelStream().filter(w->w.length()>8).count();
			//System.out.println(count);
			
			String connectStr = String.join(", ", words);
			System.out.println(connectStr);
			
		} catch (URISyntaxException|IOException e) {
			e.printStackTrace();
		} 
		
	}
	@Test
	public void readBook2(){
		
		URL gwtFileURL = StreamTest.class.getResource("gwt.txt");
		URI gwtURI = null;
		try {
			gwtURI = gwtFileURL.toURI();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		try(Stream<String> lines = Files.lines(Paths.get(gwtURI))){
			lines.forEach(System.out::println);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
	}
	@Test
	public void unlimtStreamTest(){
		Stream<BigInteger> ints = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));
		Stream<BigInteger> oddnums = ints.limit(10).peek(item->System.out.println("Debug for: "+item.intValue())).filter(i->i.mod(BigInteger.valueOf(2l)).equals(BigInteger.ZERO));
		System.out.println("---------------------------------");
		//BigInteger[] oddArr = oddnums.toArray(BigInteger[]::new);
		String oddStr = oddnums.map(BigInteger::toString).collect(Collectors.joining(", "));
		System.out.println("---------------------------------");
		System.out.println(oddStr);
		
		Stream<BigInteger> ints2 = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));
		IntSummaryStatistics summary = ints2.limit(10).collect(Collectors.summarizingInt(BigInteger::intValue));
		long sumVal = summary.getSum();
		System.out.println("---------------------------------");
		System.out.println(sumVal);
		//Stream<BigInteger> fibonacci = Stream.iterate(BigInteger.valueOf(2l), n -> n.add(BigInteger.ONE));
		
	}
	@Test
	public void reduction(){
		URL gwtFileURL = StreamTest.class.getResource("gwt.txt");
		URI gwtURI = null;
		try {
			gwtURI = gwtFileURL.toURI();
			String content = new String(Files.readAllBytes(Paths.get(gwtURI)));
			List<String> words = Arrays.asList(content.split("\\PL+"));
			Optional<String> startWithQ = words.parallelStream().filter(s-> s!=null && s.startsWith("Q")).findAny();
			System.out.println(startWithQ);
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void optionalFlatMap(){
		Optional<Double> sq = inverse(4.0).flatMap(StreamTest::squareRoot);
		System.out.println(sq);
		
		
	}
	
	public static Optional<Double> inverse(Double x){
		return x==0?Optional.empty():Optional.of(1/x);
	}
	public static Optional<Double> squareRoot(Double x){
		return x<0?Optional.empty():Optional.of(Math.sqrt(x));
	}
	
	@Test
	public void map1Test(){
		Stream<Locale> locales = Stream.of(Locale.getAvailableLocales()).filter(l->!l.getDisplayCountry().isEmpty());
		Map<String, String> langMap = locales.collect(Collectors.toMap(
				Locale::getDisplayLanguage, 
				Locale::getDisplayLanguage,
				(e,n)->e));
		langMap.entrySet().forEach(e -> System.out.println(String.join(",", e.getKey(), e.getValue())));
				
	}
	@Test
	public void map2Test(){
		Stream<Locale> locales = Stream.of(Locale.getAvailableLocales())
				.filter(l->!l.getDisplayCountry().isEmpty());
		
		Map<String, Set<String>> langMap = locales.collect(Collectors.toMap(
				Locale::getDisplayCountry, 
				l->Collections.singleton(l.getDisplayLanguage()),
				(e,n)->{
					Set<String> union = new HashSet<>(e);
					union.addAll(n);
					return union;
				}));
		
		Stream<String> showS = langMap.entrySet().stream().map(e->{return e.getKey()+": "+String.join(", ", e.getValue());});
		showS.forEach(System.out::println);
				
	}
	
	@Test
	public void map2GroupByTest(){
		Stream<Locale> locales = Stream.of(Locale.getAvailableLocales())
				.filter(l->!l.getDisplayCountry().isEmpty());
		
		Map<String, List<Locale>> countryLocales= locales.collect(Collectors.groupingBy(Locale::getCountry));
		Stream<String> showS = countryLocales.entrySet().stream().map(
				e->{return e.getKey()+": "+String.join(", ", e.getValue().stream().map(Locale::getLanguage).toArray(String[]::new));});
		showS.forEach(System.out::println);
				
	}
}
