package guru.springframework;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

//import guru.springframework.converters.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.domain.UnitOfMeasure;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

	public static final String DESCRIPTION = "description";
	public static final Long LONG_VALUE = new Long(1L);
	
//	UnitOfMeasureCommandToUnitOfMeasure converter;
	
	@Before
	public void setUp()throws Exception{
//		converter = new UnitOfMeasureCommandToUnitOfMeasure();
	}
	
	@Test
	public void testNullParamenter() throws Exception{
//		assertNull(converter.convert(null)));
	}
	
	@Test
	public void testEmptyObject() throws Exception{
//		assertNotNull(converter.convert(new UnitOfMeasureCommand()));
	}
	
	@Test
	public void testConvert() throws Exception{
		//given
//		UnitOfMeasureCommandToUnitOfMeasure command = new UnitOfMeasureCommand();
//		command.setId(LONG_VALUE);
//		command.setDescription(DESCRIPTION);
		
		//when
//		UnitOfMeasure uom = converter.convert(command);
		
		//then
//		assertNotNull(uom);
//		assertEquals(LONG_VALUE, uom.getId());
//		assertEquals(DESCRIPTION, uom.getDescription());
	}

}
