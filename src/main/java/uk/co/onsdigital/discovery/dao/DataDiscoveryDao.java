package uk.co.onsdigital.discovery.dao;

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
import uk.co.onsdigital.discovery.utils.TimeHelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Data Access Object (DAO) for all entities in the data discovery domain model. NB: can be split later if individual
 * DAOs become more compelling.
 * <p>
 * Method naming conventions:
 * <dl>
 *     <dt>{@code getXXX}</dt><dd>Returns a <em>reference</em> to the given object (via
 * {@link EntityManager#getReference(Class, Object)}).</dd>
 *     <dt>{@code findXXX}</dt><dd>Performs a find/query for the given object.</dd>
 *     <dt>{@code createXXX}</dt><dd>Creates and persists the given object.</dd>
 *     <dt>{@code find/getOrCreateYYY}</dt><dd>Tries to find/get the given object, and if not found then creates and
 *      persists it.</dd>
 * </dl>
 */
@SuppressWarnings("WeakerAccess")
public class DataDiscoveryDao {

    private final EntityManager em;

    /**
     * Construct the DAO with the given JPA entity manager.
     *
     * @param em the {@link EntityManager} to use for persistence.
     */
    public DataDiscoveryDao(EntityManager em) {
        this.em = requireNonNull(em);
    }

    /**
     * Get a reference to the given concept system, or create and persist it if it does not exist.
     *
     * @param conceptSystemName the name of the concept system to get or create.
     * @return the named concept system entity.
     */
    public ConceptSystem getOrCreateConceptSystem(String conceptSystemName) {
        try {
            return em.getReference(ConceptSystem.class, conceptSystemName);
        } catch (EntityNotFoundException ex) {
            final ConceptSystem conceptSystem = new ConceptSystem(conceptSystemName);
            em.persist(conceptSystem);
            return conceptSystem;
        }
    }

    /**
     * Lookup the given concept in the database, or create and persist it if it does not exist.
     *
     * @param conceptName the name of the concept system that the category is part of.
     * @param categoryName the name of the category.
     * @return the named category.
     */
    public Category findOrCreateCategory(String conceptName, String categoryName) {
        try {
            return em.createNamedQuery("Category.findByNameAndConceptSystem", Category.class)
                    .setParameter("conceptSystem", conceptName)
                    .setParameter("categoryName", categoryName)
                    .getSingleResult();
        } catch (NoResultException ex) {
            final Category category = new Category(categoryName);
            final ConceptSystem conceptSystem = getOrCreateConceptSystem(conceptName);
            category.setConceptSystemBean(conceptSystem);
            em.persist(category);
            return category;
        }
    }

    /**
     * Get a reference to, or create and persist, the given unit type.
     *
     * @param unitTypeEng the unit type, such as "person".
     * @return the given unit type entity.
     */
    public UnitType getOrCreateUnitType(String unitTypeEng) {
        try {
            return em.getReference(UnitType.class, unitTypeEng);
        } catch (EntityNotFoundException ex) {
            final UnitType unitType = new UnitType(unitTypeEng);
            em.persist(unitType);
            return unitType;
        }
    }

    /**
     * Get a reference to, or create and persist, the given value domain.
     *
     * @param valueDomainName the value domain, such as "integer".
     * @return the given value domain entity.
     */
    public ValueDomain getOrCreateValueDomain(String valueDomainName) {
        try {
            return em.getReference(ValueDomain.class, valueDomainName);
        } catch (EntityNotFoundException ex) {
            ValueDomain valueDomain = new ValueDomain(valueDomainName);
            em.persist(valueDomain);
            return valueDomain;
        }
    }

    /**
     * Find or create and persist the given variable.
     *
     * @param variableName the name of the variable.
     * @param unitType the unit type.
     * @param valueDomain the value domain.
     * @param categories the categories.
     * @return the created variable entity.
     */
    public Variable findOrCreateVariable(String variableName, UnitType unitType, ValueDomain valueDomain, List<Category> categories) {
        try {
            return em.createNamedQuery("Variable.findByName", Variable.class)
                    .setParameter("name", variableName).getSingleResult();
        } catch (NoResultException ex) {
            Variable variable = new Variable(variableName);
            variable.setUnitTypeBean(unitType);
            variable.setValueDomainBean(valueDomain);
            variable.setCategories(categories);
            em.persist(variable);  // todo fix cascade
            return variable;
        }
    }

    /**
     * Looks up the given geographic area by ext code.
     *
     * @param extCode the ext code of the geographic area.
     * @return the given geographic(al) area entity.
     * @throws NoResultException if there is no such geographical area defined.
     */
    public GeographicArea findGeographicAreaByExtCode(String extCode) {
        return em.createNamedQuery("GeographicArea.findByExtCode", GeographicArea.class)
                .setParameter("extCode", extCode)
                .getSingleResult();
    }

    /**
     * Get a reference to, or create and persist, the given time period type, such as "YEAR", "MONTH" or "QUARTER".
     * @param timeType the type of time period.
     * @return the time type entity.
     */
    public TimeType getOrCreateTimeType(String timeType) {
        try {
            return em.getReference(TimeType.class, timeType);
        } catch (EntityNotFoundException ex) {
            TimeType type = new TimeType();
            type.setTimeType(timeType);
            em.persist(type);
            return type;
        }
    }

    /**
     * Finds or creates and persists the given time period.
     *
     * @param timePeriodName the name of the time period, such as "2014".
     * @param timeType the type of time period.
     * @return the given time period entity.
     */
    public TimePeriod findOrCreateTimePeriod(String timePeriodName, TimeType timeType) {
        try {
            // TODO what about multiple returns?  is it possible? doesn't appear to be a constraint on name.
            // For now, we just let the NonUniqueResultException propagate to abort the transaction
            return em.createNamedQuery("TimePeriod.findByName", TimePeriod.class)
                    .setParameter("name", timePeriodName).getSingleResult();
        } catch (NoResultException ex) {
            TimeHelper timeHelper = new TimeHelper();
            TimePeriod timePeriod = new TimePeriod();
            timePeriod.setName(timePeriodName);
            timePeriod.setStartDate(timeHelper.getStartDate(timePeriodName));
            timePeriod.setEndDate(timeHelper.getEndDate(timePeriodName));
            timePeriod.setTimeTypeBean(timeType);

            em.persist(timePeriod);

            return timePeriod;
        }
    }

    /**
     * Finds or creates and persists the given population.
     *
     * @param geographicArea the geographical area of the population.
     * @param timePeriod the period in time of the population.
     * @return the given population entity.
     */
    public Population findOrCreatePopulation(GeographicArea geographicArea, TimePeriod timePeriod) {
        PopulationPK populationPK = new PopulationPK();
        populationPK.setGeographicAreaId(geographicArea.getGeographicAreaId());
        populationPK.setTimePeriodId(timePeriod.getTimePeriodId());
        Population population = em.find(Population.class, populationPK);
        if (population == null) {
            population = new Population();
            population.setGeographicArea(geographicArea);
            population.setTimePeriod(timePeriod);
            population.setGeographicAreaExtCode(geographicArea.getExtCode());
            em.persist(population);
        }

        return population;
    }

    /**
     * Create and persist a data point.
     *
     * @param dataSet the dataset that this data point belongs to.
     * @param dataMarking the data marking.
     * @param population the population.
     * @param variable the variable.
     * @param value the observation value.
     * @return the created data point.
     */
    public DimensionalDataPoint createDataPoint(DimensionalDataSet dataSet, String dataMarking, Population population,
                                                Variable variable, BigDecimal value) {
        DimensionalDataPoint dataPoint = new DimensionalDataPoint();
        dataPoint.setDimensionalDataSet(dataSet);
        dataPoint.setDataMarking(dataMarking);
        dataPoint.setVariable(variable);
        dataPoint.setPopulation(population);
        dataPoint.setValue(value);

        em.persist(dataPoint);
        return dataPoint;
    }
}
