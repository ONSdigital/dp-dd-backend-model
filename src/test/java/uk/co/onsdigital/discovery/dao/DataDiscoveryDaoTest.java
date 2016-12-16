package uk.co.onsdigital.discovery.dao;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import uk.co.onsdigital.discovery.model.Category;
import uk.co.onsdigital.discovery.model.ConceptSystem;
import uk.co.onsdigital.discovery.model.DimensionalDataPoint;
import uk.co.onsdigital.discovery.model.DimensionalDataSet;
import uk.co.onsdigital.discovery.model.GeographicArea;
import uk.co.onsdigital.discovery.model.Population;
import uk.co.onsdigital.discovery.model.PopulationPK;
import uk.co.onsdigital.discovery.model.TimePeriod;
import uk.co.onsdigital.discovery.model.TimeType;
import uk.co.onsdigital.discovery.model.UnitType;
import uk.co.onsdigital.discovery.model.ValueDomain;
import uk.co.onsdigital.discovery.model.Variable;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

public class DataDiscoveryDaoTest {

    @Mock
    private EntityManager mockEntityManager;

    @Mock
    private TypedQuery<Category> mockCategoryQuery;

    @Mock
    private TypedQuery<GeographicArea> mockGeographicAreaQuery;

    @Mock
    private TypedQuery<TimePeriod> mockTimePeriodQuery;

    @Mock
    private TypedQuery<Variable> mockVariableQuery;

    private DataDiscoveryDao dao;

    @BeforeMethod
    public void createDao() {
        MockitoAnnotations.initMocks(this);
        this.dao = new DataDiscoveryDao(mockEntityManager);
    }

    @Test
    public void shouldReturnConceptSystemIfFound() throws Exception {
        String conceptSystemName = "testConceptSystem";
        ConceptSystem conceptSystem = new ConceptSystem(conceptSystemName);
        when(mockEntityManager.getReference(ConceptSystem.class, conceptSystemName)).thenReturn(conceptSystem);

        ConceptSystem result = dao.getOrCreateConceptSystem(conceptSystemName);

        assertThat(result).isSameAs(conceptSystem);
        verify(mockEntityManager, never()).persist(anyObject());
    }

    @Test
    public void shouldCreateConceptSystemIfNotFound() throws Exception {
        String conceptSystemName = "testConceptSystem";
        when(mockEntityManager.getReference(ConceptSystem.class, conceptSystemName)).thenThrow(new EntityNotFoundException());

        ConceptSystem result = dao.getOrCreateConceptSystem(conceptSystemName);

        assertThat(result).isNotNull().hasFieldOrPropertyWithValue("conceptSystem", conceptSystemName);
        verify(mockEntityManager).persist(result);
    }

    @Test
    public void shouldReturnCategoryIfFound() throws Exception {
        String conceptSystem = "testConceptSystem";
        String categoryName = "testCategory";
        Category category = new Category(categoryName);
        when(mockEntityManager.createNamedQuery("Category.findByNameAndConceptSystem", Category.class)).thenReturn(mockCategoryQuery);
        when(mockCategoryQuery.setParameter("conceptSystem", conceptSystem)).thenReturn(mockCategoryQuery);
        when(mockCategoryQuery.setParameter("categoryName", categoryName)).thenReturn(mockCategoryQuery);
        when(mockCategoryQuery.getSingleResult()).thenReturn(category);

        Category result = dao.findOrCreateCategory(conceptSystem, categoryName);

        assertThat(result).isSameAs(category);
        verify(mockEntityManager, never()).persist(anyObject());
    }

    @Test
    public void shouldCreateCategoryIfNotFound() throws Exception {
        String conceptSystem = "testConceptSystem";
        String categoryName = "testCategory";
        when(mockEntityManager.createNamedQuery("Category.findByNameAndConceptSystem", Category.class)).thenReturn(mockCategoryQuery);
        when(mockCategoryQuery.setParameter("conceptSystem", conceptSystem)).thenReturn(mockCategoryQuery);
        when(mockCategoryQuery.setParameter("categoryName", categoryName)).thenReturn(mockCategoryQuery);
        when(mockCategoryQuery.getSingleResult()).thenThrow(new NoResultException());

        Category result = dao.findOrCreateCategory(conceptSystem, categoryName);

        assertThat(result).isNotNull().hasFieldOrPropertyWithValue("name", categoryName);
        verify(mockEntityManager).persist(result);
    }

    @Test
    public void shouldReturnUnitTypeIfFound() throws Exception {
        String unitTypeName = "testUnitType";
        UnitType unitType = new UnitType(unitTypeName);
        when(mockEntityManager.getReference(UnitType.class, unitTypeName)).thenReturn(unitType);

        UnitType result = dao.getOrCreateUnitType(unitTypeName);

        assertThat(result).isSameAs(unitType);
        verify(mockEntityManager, never()).persist(anyObject());
    }

    @Test
    public void shouldCreateUnitTypeIfNotFound() throws Exception {
        String unitTypeName = "testUnitType";
        when(mockEntityManager.getReference(UnitType.class, unitTypeName)).thenThrow(new EntityNotFoundException());

        UnitType result = dao.getOrCreateUnitType(unitTypeName);

        assertThat(result).isNotNull().hasFieldOrPropertyWithValue("unitType", unitTypeName);
        verify(mockEntityManager).persist(result);
    }

    @Test
    public void shouldReturnValueDomainIfFound() throws Exception {
        String valueDomainName = "testValueDomain";
        ValueDomain valueDomain = new ValueDomain(valueDomainName);
        when(mockEntityManager.getReference(ValueDomain.class, valueDomainName)).thenReturn(valueDomain);

        ValueDomain result = dao.getOrCreateValueDomain(valueDomainName);

        assertThat(result).isSameAs(valueDomain);
        verify(mockEntityManager, never()).persist(anyObject());
    }

    @Test
    public void shouldCreateValueDomainIfNotFound() throws Exception {
        String valueDomainName = "testValueDomain";
        when(mockEntityManager.getReference(ValueDomain.class, valueDomainName)).thenThrow(new EntityNotFoundException());

        ValueDomain result = dao.getOrCreateValueDomain(valueDomainName);

        assertThat(result).isNotNull().hasFieldOrPropertyWithValue("valueDomain", valueDomainName);
        verify(mockEntityManager).persist(result);
    }

    @Test
    public void shouldReturnVariableIfFound() throws Exception {
        String variableName = "testVariable";
        Variable variable = new Variable();
        when(mockEntityManager.createNamedQuery("Variable.findByName", Variable.class)).thenReturn(mockVariableQuery);
        when(mockVariableQuery.setParameter("name", variableName)).thenReturn(mockVariableQuery);
        when(mockVariableQuery.getSingleResult()).thenReturn(variable);

        Variable result = dao.findOrCreateVariable(variableName, null, null, null);

        assertThat(result).isSameAs(variable);
        verify(mockEntityManager, never()).persist(anyObject());
    }

    @Test
    public void shouldCreateVariableIfNotFound() throws Exception {
        String variableName = "testVariable";
        UnitType unitType = new UnitType("testUnitType");
        ValueDomain valueDomain = new ValueDomain("testValueDomain");
        List<Category> categories = Arrays.asList(new Category("one"), new Category("two"));
        when(mockEntityManager.createNamedQuery("Variable.findByName", Variable.class)).thenReturn(mockVariableQuery);
        when(mockVariableQuery.setParameter("name", variableName)).thenReturn(mockVariableQuery);
        when(mockVariableQuery.getSingleResult()).thenThrow(new NoResultException());

        Variable result = dao.findOrCreateVariable(variableName, unitType, valueDomain, categories);

        assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("name", variableName)
                .hasFieldOrPropertyWithValue("unitTypeBean", unitType)
                .hasFieldOrPropertyWithValue("valueDomainBean", valueDomain)
                .hasFieldOrPropertyWithValue("categories", categories);
        verify(mockEntityManager).persist(result);
    }

    @Test
    public void shouldReturnGeographicAreaIfFoundByExtCode() throws Exception {
        String extCode = "testCode";
        GeographicArea geographicArea = new GeographicArea();
        geographicArea.setExtCode(extCode);
        when(mockEntityManager.createNamedQuery("GeographicArea.findByExtCode", GeographicArea.class)).thenReturn(mockGeographicAreaQuery);
        when(mockGeographicAreaQuery.setParameter("extCode", extCode)).thenReturn(mockGeographicAreaQuery);
        when(mockGeographicAreaQuery.getSingleResult()).thenReturn(geographicArea);

        GeographicArea result = dao.findGeographicAreaByExtCode(extCode);
        assertThat(result).isSameAs(geographicArea);
        verify(mockEntityManager, never()).persist(anyObject());
    }

    @Test
    public void shouldGetTimeTypeIfFound() throws Exception {
        String timeTypeName = "QUARTER";
        TimeType timeType = new TimeType();
        when(mockEntityManager.getReference(TimeType.class, timeTypeName)).thenReturn(timeType);

        TimeType result = dao.getOrCreateTimeType(timeTypeName);

        assertThat(result).isSameAs(timeType);
        verify(mockEntityManager, never()).persist(anyObject());
    }

    @Test
    public void shouldCreateTimeTypeIfNotFound() throws Exception {
        String timeTypeName = "QUARTER";
        when(mockEntityManager.getReference(TimeType.class, timeTypeName)).thenThrow(new EntityNotFoundException());

        final TimeType result = dao.getOrCreateTimeType(timeTypeName);
        assertThat(result).isNotNull().hasFieldOrPropertyWithValue("timeType", timeTypeName);
        verify(mockEntityManager).persist(result);
    }

    @Test
    public void shouldReturnTimePeriodIfFound() throws Exception {
        String timePeriodName = "2014";
        TimeType timeType = new TimeType();
        TimePeriod timePeriod = new TimePeriod();
        when(mockEntityManager.createNamedQuery("TimePeriod.findByName", TimePeriod.class)).thenReturn(mockTimePeriodQuery);
        when(mockTimePeriodQuery.setParameter("name", timePeriodName)).thenReturn(mockTimePeriodQuery);
        when(mockTimePeriodQuery.getSingleResult()).thenReturn(timePeriod);

        TimePeriod result = dao.findOrCreateTimePeriod(timePeriodName, timeType);

        assertThat(result).isSameAs(timePeriod);
    }

    @Test
    public void shouldCreateTimePeriodIfNotFound() throws Exception {
        String timePeriodName = "2014";
        TimeType timeType = new TimeType();
        timeType.setTimeType("YEAR");
        when(mockEntityManager.createNamedQuery("TimePeriod.findByName", TimePeriod.class)).thenReturn(mockTimePeriodQuery);
        when(mockTimePeriodQuery.setParameter("name", timePeriodName)).thenReturn(mockTimePeriodQuery);
        when(mockTimePeriodQuery.getSingleResult()).thenThrow(new NoResultException());

        TimePeriod result = dao.findOrCreateTimePeriod(timePeriodName, timeType);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date expectedStartDate = sdf.parse("2014-01-01");
        Date expectedEndDate = sdf.parse("2014-12-31");

        assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("name", timePeriodName)
                .hasFieldOrPropertyWithValue("timeTypeBean", timeType)
                .hasFieldOrPropertyWithValue("startDate", expectedStartDate)
                .hasFieldOrPropertyWithValue("endDate", expectedEndDate);
        verify(mockEntityManager).persist(result);
    }

    @Test
    public void shouldReturnPopulationIfFound() throws Exception {
        Long areaId = 42L;
        GeographicArea geographicArea = new GeographicArea();
        geographicArea.setGeographicAreaId(areaId);
        Long timePeriodId = 43L;
        TimePeriod timePeriod = new TimePeriod();
        timePeriod.setTimePeriodId(timePeriodId);
        Population population = new Population();
        when(mockEntityManager.find(Population.class, new PopulationPK(areaId, timePeriodId))).thenReturn(population);

        Population result = dao.findOrCreatePopulation(geographicArea, timePeriod);

        assertThat(result).isSameAs(population);
        verify(mockEntityManager, never()).persist(anyObject());
    }

    @Test
    public void shouldCreatePopulationIfNotFound() throws Exception {
        Long areaId = 42L;
        GeographicArea geographicArea = new GeographicArea();
        geographicArea.setGeographicAreaId(areaId);
        geographicArea.setExtCode("testExtCode");
        Long timePeriodId = 43L;
        TimePeriod timePeriod = new TimePeriod();
        timePeriod.setTimePeriodId(timePeriodId);
        when(mockEntityManager.find(Population.class, new PopulationPK(areaId, timePeriodId))).thenReturn(null);

        Population result = dao.findOrCreatePopulation(geographicArea, timePeriod);

        assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("geographicAreaExtCode", geographicArea.getExtCode())
                .hasFieldOrPropertyWithValue("geographicArea", geographicArea)
                .hasFieldOrPropertyWithValue("timePeriod", timePeriod);
        verify(mockEntityManager).persist(result);
    }

    @Test
    public void shouldCreateDataPoint() throws Exception {
        DimensionalDataSet dataSet = new DimensionalDataSet();
        String dataMarking = "testMarking";
        Population population = new Population();
        Variable variable = new Variable();
        BigDecimal value = new BigDecimal(2.34);

        DimensionalDataPoint dataPoint = dao.createDataPoint(dataSet, dataMarking, population, variable, value);

        assertThat(dataPoint).isNotNull()
                .hasFieldOrPropertyWithValue("dataMarking", dataMarking)
                .hasFieldOrPropertyWithValue("dimensionalDataSet", dataSet)
                .hasFieldOrPropertyWithValue("population", population)
                .hasFieldOrPropertyWithValue("variable", variable)
                .hasFieldOrPropertyWithValue("value", value);
    }
}