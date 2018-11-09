import static org.mockito.Mockito.*;
import java.util.LinkedList;
import java.util.List;
import org.junit.*;
import org.mockito.*;


public class CalculadoraTeste {
	
	@Test
	public void teste01() {
		
		Calculadora mockedCalc = mock(Calculadora.class);
		
		// Usando o mock
		mockedCalc.setDescricao("Calculadora");
		mockedCalc.getDescricao();
		
		// Verificando
		verify(mockedCalc).setDescricao("Calculadora");
		verify(mockedCalc).getDescricao();
	}
	
	@Test
	public void teste02() {
		
		Calculadora mockedCalc = mock(Calculadora.class);
		when(mockedCalc.adicao(3.0,5.0)).thenReturn(8.0);
		
		Assert.assertEquals(8.0, mockedCalc.adicao(3.0, 5.0), 0.0);
		Assert.assertEquals(11.0, mockedCalc.adicao(6.0, 5.0), 0.0);
	}
	
	@Test
	public void teste03() {
		
		Calculadora mockedCalc = mock(Calculadora.class);
		when(mockedCalc.adicao(3.0,1.0)).thenReturn(4.0);
		when(mockedCalc.adicao(3.0,3.0)).thenReturn(6.0);
		when(mockedCalc.multiplicacao(2.0,4.0)).thenReturn(8.0);
		
		Assert.assertEquals(8.0, mockedCalc.multiplicacao(2.0, mockedCalc.adicao(3.0,1.0)), 0.0);
		
		verify(mockedCalc,times(1)).adicao(anyDouble(), anyDouble());
		verify(mockedCalc,times(2)).multiplicacao(anyDouble(), anyDouble());
	}
	
	@Test
	public void teste04() {
		MockitoAnnotations.initMocks(this);
		List<String> list = new LinkedList<String>();
		List<String> spy = spy(list);

		//optionally, you can stub out some methods:
		when(spy.size()).thenReturn(100);

		//using the spy calls *real* methods
		spy.add("one");
		spy.add("two");
		
		//prints "one" - the first element of a list
		System.out.println(spy.get(0));

		//size() method was stubbed - 100 is printed
		System.out.println(spy.size());

		//optionally, you can verify
		verify(spy).add("one");
		verify(spy).add("three");
	}

	@Test
	public void teste05() {
		MockitoAnnotations.initMocks(this);
		List<String> mockedList= mock(List.class);
		//using mock
		mockedList.add("once");

		mockedList.add("twice");
		mockedList.add("twice");
		
		mockedList.add("three times");
		mockedList.add("three times");
		mockedList.add("three times");
		
		//following two verifications work exactly the same - times(1) is used by default
		verify(mockedList).add("once");
		verify(mockedList, times(1)).add("once");
		
		//exact number of invocations verification
		verify(mockedList, times(2)).add("twice");
		verify(mockedList, times(3)).add("three times");
		
		//verification using never(). never() is an alias to times(0)
		verify(mockedList, never()).add("never happened");
		
		//verification using atLeast()/atMost()
		verify(mockedList, atLeastOnce()).add("three times");
		verify(mockedList, atLeast(2)).add("four times");
		verify(mockedList, atMost(5)).add("three times");
	}
}



