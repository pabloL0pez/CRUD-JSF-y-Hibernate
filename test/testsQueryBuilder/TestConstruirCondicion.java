package testsQueryBuilder;

import org.junit.Assert;
import org.junit.Test;

import model.Filtro;
import model.QueryBuilder;

public class TestConstruirCondicion {
	
	@Test
	public void testConstruirCondicionTodosLosCampos() {
		Filtro filtro = new Filtro(2, "The Game 2", "Guerra", "PC", 1000, "exactly");
		String condicion = QueryBuilder.pruebaConstruirCondicion(filtro);
		Assert.assertEquals(" WHERE     clave=2 AND nombre=\'The Game 2\' AND genero=\'Guerra\' AND plataforma=\'PC\'"
				+  " AND precio=1000.0"
				, condicion);
	}
	
	@Test
	public void testConstruirCondicionVariosCampos() {
		Filtro filtro = new Filtro(0, null, "Guerra", "PC", 0);
		String condicion = QueryBuilder.pruebaConstruirCondicion(filtro);
		Assert.assertEquals(" WHERE     genero=\'Guerra\' AND plataforma=\'PC\'", condicion);
	}
	
	@Test
	public void testConstruirCondicionUnCampoEnElMedio() {
		Filtro filtro = new Filtro(0, null, "Guerra", null, 0);
		String condicion = QueryBuilder.pruebaConstruirCondicion(filtro);
		Assert.assertEquals(" WHERE     genero=\'Guerra\'", condicion);
	}
	
	@Test
	public void testConstruirCondicionSinCampos() {
		Filtro filtro = new Filtro(0, null, null, null, 0);
		String condicion = QueryBuilder.pruebaConstruirCondicion(filtro);
		Assert.assertEquals(" WHERE", condicion);
	}
	
	@Test
	public void testConstruirCondicionConRangoDePreciosMayorQue() {
		Filtro filtro = new Filtro(0, null, null, null, 500, "higher_than");
		String condicion = QueryBuilder.pruebaConstruirCondicion(filtro);
		Assert.assertEquals(" WHERE     precio>=500.0", condicion);
	}
	
	@Test
	public void testConstruirCondicionConRangoDePreciosMenorQue() {
		Filtro filtro = new Filtro(0, null, null, null, 500, "lower_than");
		String condicion = QueryBuilder.pruebaConstruirCondicion(filtro);
		Assert.assertEquals(" WHERE     precio<=500.0", condicion);
	}
}
